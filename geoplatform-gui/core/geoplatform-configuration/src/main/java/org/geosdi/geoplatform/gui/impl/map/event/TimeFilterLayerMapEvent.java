/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.impl.map.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class TimeFilterLayerMapEvent extends GwtEvent<LayerMapChangedHandler> {

    private GPLayerTreeModel layerBean;

    /**
     * @return the layerBean
     */
    public GPLayerTreeModel getLayerBean() {
        return layerBean;
    }

    @Override
    public Type<LayerMapChangedHandler> getAssociatedType() {
        return LayerMapChangedHandler.TYPE;
    }

    @Override
    protected void dispatch(LayerMapChangedHandler handler) {
        handler.onChangeTimeFilter(layerBean);
    }

    /**
     * @param layerBean the layerBean to set
     */
    public void setLayerBean(GPLayerTreeModel layerBean) {
        this.layerBean = layerBean;
    }
}
