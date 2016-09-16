package org.geosdi.geoplatform.experimental.el.index.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;

import java.util.Objects;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPBaseIndexSettings implements GPBaseIndexCreator.GPIndexSettings {

    private final String indexName;
    private final String indexType;

    @JsonCreator
    public GPBaseIndexSettings(@JsonProperty(value = "indexName") String theIndexName,
            @JsonProperty(value = "indexType") String theIndexType) {
        this.indexName = theIndexName;
        this.indexType = theIndexType;
    }

    @Override
    public String getIndexName() {
        return this.indexName;
    }

    @Override
    public String getIndexType() {
        return this.indexType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPBaseIndexSettings that = (GPBaseIndexSettings) o;
        return Objects.equals(indexName, that.indexName) &&
                Objects.equals(indexType, that.indexType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexName, indexType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {"
                + "indexName = " + indexName
                + ", indexType = " + indexType + '}';
    }
}
