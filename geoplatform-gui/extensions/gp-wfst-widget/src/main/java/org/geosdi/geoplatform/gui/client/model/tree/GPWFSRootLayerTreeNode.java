package org.geosdi.geoplatform.gui.client.model.tree;

import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSRootLayerTreeNode extends GPWFSTreeComposite {

    /**
     * @param theTwin
     */
    void bind(GPBeanTreeModel theTwin);

    /**
     * @param childres
     */
    void addChildrens(List<GPLayerBean> childres);

    /**
     * @param <Child>
     * @return {@link List<Child>}
     */
    <Child extends GPWFSLayerTreeNode> List<Child> getChildrens();
}