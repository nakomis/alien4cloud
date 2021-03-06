// Manage global and common REST responses error, such as: resources usages (when performing a deletion)
define(function (require) {
  'use strict';

  var modules = require('modules');
  var _ = require('lodash');

  modules.get('alien4cloud', ['toaster', 'pascalprecht.translate'])
    .factory('globalRestErrorHandler',
    ['toaster', '$translate',
    function (toaster, $translate) {

      //error handlers registration
      var handlers = {};


      /**
      ** Handler for RESOURCE USAGE (code 508) error
      **/
      // Prepare toaster html for toaster message
      function buildResourceUsageList(response) {
        var baseResponse = $translate('ERRORS.' + response.error.code+'.MESSAGE');
        var usageList = baseResponse + ' : <ul>';
        response.data.forEach(function(usage) {
          usageList += '<li>';
          usageList += usage.resourceType + ' : ' + usage.resourceName;
          usageList += '</li>';
        });
        return usageList;
      }

      var handleResouceUsage = function(response){
        var toasterHtml = buildResourceUsageList(response);
        // toaster message
        toaster.pop('error', $translate('ERRORS.' + response.error.code + '.TITLE'), toasterHtml, 4000, 'trustedHtml', null);
      };
      //register
      handlers[508] = handleResouceUsage;


      /**
      **
      ** Dispatcher to handlers
      **/
      function handle(response){
        if(_.definedPath(response, 'error.code')) {
          if(_.definedPath(handlers, response.error.code)){
            handlers[response.error.code](response);
          }
        }
      }

      return {
        handle: handle
      };
    }
  ]);
});
