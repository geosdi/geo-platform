package org.geosdi.geoplatform.gui.model.tree;

import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCompositeTreeModelConverter<T extends IGPFolderElements> extends IGPNode {

    /**
     * @param elements
     */
    void modelConverter(List<T> elements);
}
