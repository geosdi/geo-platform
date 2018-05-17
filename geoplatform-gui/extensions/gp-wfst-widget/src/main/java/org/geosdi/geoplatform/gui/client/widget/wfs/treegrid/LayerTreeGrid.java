package org.geosdi.geoplatform.gui.client.widget.wfs.treegrid;

import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.model.wfs.WfsLayerTreeBeanModel;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.LayerSelectionHandler;
import org.geosdi.geoplatform.gui.client.widget.tree.grid.GeoPlatformTreeGridWidget;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.grid.GPTreeGridBeanModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class LayerTreeGrid extends GeoPlatformTreeGridWidget<WfsLayerTreeBeanModel> implements LayerSelectionHandler {

    private final GPEventBus bus;
    private List<WfsLayerTreeBeanModel> layerBeans;

    public LayerTreeGrid(GPEventBus bus) {
        this.bus = bus;
        this.bus.addHandler(LayerSelectionHandler.TYPE, this);
    }

    @Override
    public List<ColumnConfig> createColumnModel() {
        List<ColumnConfig> configs = Lists.newArrayList();

        ColumnConfig gpColumnRender = new ColumnConfig(
                GPTreeGridBeanModel.GPKeyTreeGridModel.LABEL_NODE_VALUE.toString(),
                "Layers", 190);

        gpColumnRender.setRenderer(new TreeGridCellRenderer<WfsLayerTreeBeanModel>() {

            @Override
            public Object render(WfsLayerTreeBeanModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<WfsLayerTreeBeanModel> store, Grid<WfsLayerTreeBeanModel> grid) {
                return model.getWidget();
            }
        });
        gpColumnRender.setHeaderHtml("Title");
        configs.add(gpColumnRender);
        return configs;
    }

    @Override
    public void setTreeGridProperties() {
//        this.tree.setAutoHeight(Boolean.TRUE);
//        this.tree.setAutoWidth(Boolean.TRUE);
        this.tree.setAutoExpandColumn(GPTreeGridBeanModel.GPKeyTreeGridModel.LABEL_NODE_VALUE.toString());

//        this.tree.setIconProvider(new ModelIconProvider<WfsLayerTreeBeanModel>() {
//            @Override
//            public AbstractImagePrototype getIcon(WfsLayerTreeBeanModel model) {
//                return model.getIcon();
//            }
//        });

    }

    @Override
    protected void setStoreProperties() {
        this.store.setKeyProvider(new ModelKeyProvider<WfsLayerTreeBeanModel>() {
            @Override
            public String getKey(WfsLayerTreeBeanModel model) {
//                return model.getUUID();
                return null;
            }
        });
    }

    @Override
    public void populateListView(List<WfsLayerTreeBeanModel> layerBeans) {
        this.layerBeans = layerBeans;
    }

    public void buildTree(){
        this.store.removeAll();
        this.store.add(this.layerBeans, Boolean.FALSE);
    }

}
