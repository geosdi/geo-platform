/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.impl.map.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import org.geosdi.geoplatform.gui.model.GPRasterBean;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class StyleLayerMapEvent extends GwtEvent<LayerMapChangedHandler> {
    
     private GPRasterBean layerBean;
     private String style;

    /**
     * @return the layerBean
     */
    public GPRasterBean getLayerBean() {
        return layerBean;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public Type<LayerMapChangedHandler> getAssociatedType() {
       return LayerMapChangedHandler.TYPE;
    }

    @Override
    protected void dispatch(LayerMapChangedHandler handler) {
        handler.onChangeStyle(layerBean, style);
    }

    /**
     * @param layerBean the layerBean to set
     */
    public void setLayerBean(GPRasterBean layerBean) {
        this.layerBean = layerBean;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }
    
}
