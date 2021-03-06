package alien4cloud.model.components;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import org.elasticsearch.annotation.StringField;
import org.elasticsearch.mapping.IndexType;

/**
 * Defines a dependency on a CloudServiceArchive.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CSARDependency {
    @NonNull
    @StringField(indexType = IndexType.not_analyzed)
    private String name;

    @NonNull
    @StringField(indexType = IndexType.not_analyzed)
    private String version;
}
