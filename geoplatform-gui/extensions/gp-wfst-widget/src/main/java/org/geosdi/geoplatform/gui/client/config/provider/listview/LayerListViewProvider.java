package org.geosdi.geoplatform.gui.client.config.provider.listview;

import org.geosdi.geoplatform.gui.client.widget.wfs.treegrid.LayerTreeGrid;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class LayerListViewProvider implements Provider<LayerTreeGrid> {

    @Inject
    private GPEventBus eventBus;

    @Override
    public LayerTreeGrid get() {
        LayerTreeGrid listView = new LayerTreeGrid(this.eventBus);
        return listView;
    }
}
