package org.geosdi.geoplatform.gui.client.model.visitor.counter;

import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPLayerTreeCounter extends IVisitor {

    /**
     * @return {@link Integer}
     */
    Integer getLayerTreeElementsCount();

    void resetCounter();
}
