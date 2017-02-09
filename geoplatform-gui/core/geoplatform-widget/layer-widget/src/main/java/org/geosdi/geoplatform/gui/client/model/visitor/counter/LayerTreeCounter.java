package org.geosdi.geoplatform.gui.client.model.visitor.counter;

import com.extjs.gxt.ui.client.data.ModelData;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LayerTreeCounter implements GPLayerTreeCounter {

    private Integer treeCounter = new Integer(0);

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        this.treeCounter += root.getChildCount();
        for (ModelData child : root.getChildren()) {
            ((GPBeanTreeModel) child).accept(this);
        }
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        this.treeCounter += folder.getChildCount();
        for (ModelData child : folder.getChildren()) {
            ((GPBeanTreeModel) child).accept(this);
        }
    }

    @Override
    public void visitVector(GPVectorBean vector) {
    }

    @Override
    public void visitRaster(GPRasterBean raster) {
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getLayerTreeElementsCount() {
        return this.treeCounter;
    }

    @Override
    public void resetCounter() {
        this.treeCounter = 0;
    }
}
