package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimeRangePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class RangeViewStrategy extends IStrategyView.AbstractPanelStrategy {

    @Override
    public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        parseExtentValue(treePanel);
        return new TimeRangePanel(treePanel);
    }

    @Override
    protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
        this.valuesMap.put(TypeValueEnum.RANGE, Lists.newArrayList(((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split(",")));
//        this.valuesMap.put(TypeValueEnum.RANGE, ((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split(","));
    }
}
