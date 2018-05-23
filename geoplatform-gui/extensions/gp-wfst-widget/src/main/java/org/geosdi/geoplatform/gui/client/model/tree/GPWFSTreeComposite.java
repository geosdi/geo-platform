package org.geosdi.geoplatform.gui.client.model.tree;

import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSTreeComposite {

    /**
     * @param visitor
     */
    void accept(GPWFSCompositeVisitor visitor);
}
