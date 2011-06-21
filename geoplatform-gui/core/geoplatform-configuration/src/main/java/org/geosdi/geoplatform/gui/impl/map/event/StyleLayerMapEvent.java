/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.impl.map.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import org.geosdi.geoplatform.gui.model.GPLayerBean;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class StyleLayerMapEvent extends GwtEvent<LayerMapChangedHandler> {
    
     private GPLayerBean layerBean;
     private String style;

    public StyleLayerMapEvent(GPLayerBean theLayerBean, String newStyle) {
        this.layerBean = theLayerBean;
        this.style = newStyle;
    }

    /**
     * @return the layerBean
     */
    public GPLayerBean getLayerBean() {
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
    
    
    
}
