/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher;

import com.google.gwt.event.shared.HandlerRegistration;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.UpdateFeatureGeometryRequest;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import static org.geosdi.geoplatform.gui.client.puregwt.map.dispatcher.FeatureDispatcherHandler.TYPE;
import org.geosdi.geoplatform.gui.client.puregwt.map.initializer.IFeatureMapInitializerHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.WFSDispatcherProgressBar;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractFeatureDispatcher implements FeatureDispatcher {
    
    private static final GPEventBus dispatcherBus = new GPEventBusImpl();
    //
    @Inject
    protected GPEventBus bus;
    @Inject
    protected ILayerSchemaBinder layerSchemaBinder;
    @Inject
    protected WFSDispatcherProgressBar progressBar;
    @Inject
    protected Vector vector;
    @Inject
    protected UpdateFeatureGeometryRequest updateGeometryRequest;
    private final FeatureStatusBarEvent loadingEvent;
    private final FeatureStatusBarEvent successEvent;
    private final FeatureStatusBarEvent statusNotOk;
    private final FeatureStatusBarEvent failedEvent;
    
    public AbstractFeatureDispatcher(FeatureStatusBarEvent theLoadingEvent,
            FeatureStatusBarEvent theSuccessEvent,
            FeatureStatusBarEvent theStatusNotOk,
            FeatureStatusBarEvent theFailedEvent) {
        this.loadingEvent = theLoadingEvent;
        this.successEvent = theSuccessEvent;
        this.statusNotOk = theStatusNotOk;
        this.failedEvent = theFailedEvent;
    }
    
    @Override
    public final HandlerRegistration addFeatureDispatcherHandler() {
        return dispatcherBus.addHandler(TYPE, this);
    }
    
    public static void fireFeatureDispatcherEvent(FeatureDispatcherEvent event) {
        dispatcherBus.fireEvent(event);
    }
    
    protected void manageCommandSuccess(Boolean result,
            VectorFeature modifiedFeature, VectorFeature oldFeature) {
        progressBar.hide();
        if (result) {
            fireEvents();
        } else {
            vector.removeFeature(modifiedFeature);
            vector.addFeature(oldFeature);
            bus.fireEvent(statusNotOk);
        }
    }
    
    protected void manageCommandFailure(VectorFeature modifiedFeature,
            VectorFeature oldFeature, Throwable exception) {
        String errorMessage = "Error on WFS GetFeature request";
        GeoPlatformMessage.errorMessage(
                "GetFeture Service Error",
                errorMessage + " - " + exception.getMessage());
        
        progressBar.hide();
        vector.removeFeature(modifiedFeature);
        vector.addFeature(oldFeature);
        
        bus.fireEvent(failedEvent);
    }
    
    protected void setUpRequest(VectorFeature modifiedFeature,
            String wktGeometry) {
        bus.fireEvent(loadingEvent);
        
        updateGeometryRequest.setFid(modifiedFeature.getFID());
        updateGeometryRequest.setWktGeometry(wktGeometry);
        updateGeometryRequest.setServerUrl(
                layerSchemaBinder.getLayerSchemaDTO().getScope());
        updateGeometryRequest.setTypeName(
                layerSchemaBinder.getLayerSchemaDTO().getTypeName());
        updateGeometryRequest.setGeometryAttributeName(
                layerSchemaBinder.getLayerSchemaDTO().getGeometry().getName());
    }
    
    private void fireEvents() {
        bus.fireEvent(successEvent);
        bus.fireEvent(IFeatureMapInitializerHandler.REDRAW_EVENT);
        GPHandlerManager.fireEvent(
                this.layerSchemaBinder.getReloadLayerMapEvent());
    }
    
}
