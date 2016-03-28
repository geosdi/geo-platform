package org.geosdi.geoplatform.experimental.el.index.settings;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPBaseIndexSettings implements GPIndexCreator.GPIndexSettings {

    private final String indexName;
    private final String indexType;

    public GPBaseIndexSettings(String theIndexName, String theIndexType) {
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
    public String toString() {
        return getClass().getSimpleName() + " {"
                + "indexName = " + indexName
                + ", indexType = " + indexType + '}';
    }
}
