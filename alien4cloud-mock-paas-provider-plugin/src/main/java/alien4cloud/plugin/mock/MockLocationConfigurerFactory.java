package alien4cloud.plugin.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import alien4cloud.model.deployment.matching.MatchingConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import alien4cloud.model.orchestrators.locations.LocationResourceTemplate;
import alien4cloud.orchestrators.plugin.ILocationConfiguratorPlugin;
import alien4cloud.orchestrators.plugin.ILocationResourceAccessor;
import alien4cloud.orchestrators.plugin.model.PluginArchive;
import alien4cloud.plugin.PluginManager;
import alien4cloud.plugin.model.ManagedPlugin;
import alien4cloud.tosca.ArchiveParser;

/**
 * Component that creates location configurer for a mock openstack cloud.
 */
@Component
public class MockLocationConfigurerFactory {
    @Inject
    private ArchiveParser archiveParser;
    @Inject
    private PluginManager pluginManager;
    @Inject
    private ManagedPlugin selfContext;
    @Inject
    private ApplicationContext applicationContext;

    public ILocationConfiguratorPlugin newInstance(String locationType) {
        if (MockOrchestratorFactory.OPENSTACK.equals(locationType)) {
            MockOpenStackLocationConfigurer configurer = applicationContext.getBean(MockOpenStackLocationConfigurer.class);
            return configurer;
        }
        if (MockOrchestratorFactory.BROOKLYN.equals(locationType)) {
            return applicationContext.getBean(BrooklynLocationConfigurer.class);
        }
        return new ILocationConfiguratorPlugin() {
            @Override
            public List<PluginArchive> pluginArchives() {
                return new ArrayList<>();
            }

            @Override
            public List<String> getResourcesTypes() {
                return new ArrayList<>();
            }

            @Override
            public Map<String, MatchingConfiguration> getMatchingConfigurations() {
                return new HashMap<>();
            }

            @Override
            public List<LocationResourceTemplate> instances(ILocationResourceAccessor resourceAccessor) {
                return null;
            }


        };
    }
}