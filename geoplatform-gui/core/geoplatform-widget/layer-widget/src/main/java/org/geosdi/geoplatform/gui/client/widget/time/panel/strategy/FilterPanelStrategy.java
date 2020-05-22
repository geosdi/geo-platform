package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy;

import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimeFilterPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class FilterPanelStrategy extends IStrategyPanel.AbstractPanelStrategy {

    @Override
    public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        parseExtentValue(treePanel);
        return new TimeFilterPanel(treePanel);
    }

    @Override
    protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
        this.valuesMap.put(TypeValueEnum.RANGE, ((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split(","));
    }
}
