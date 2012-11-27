/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.wfs.feature.handler;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.event.EventObject;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.util.Attributes;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureSelectHandler extends AbastractFeatureHandler {
    
    public FeatureSelectHandler(Vector theVectorLayer,
            GPEventBus bus) {
        super(theVectorLayer, bus);
    }
    
    @Override
    public void onHandle(EventObject eventObject) {
        System.out.println("FeatureSelectHandler @@@@@@@@@@@@@@@@");
        
        VectorFeature vectorFeature = super.getFeatureFromEventObject(
                eventObject);
        
        vectorFeature.toState(VectorFeature.State.Unknown);
        
        vectorLayer.addFeature(vectorFeature);
        
        Attributes attributes = vectorFeature.getAttributes();
        List<String> attributeNames = attributes.getAttributeNames();
        
        Map<String, String> attributeMap = Maps.<String, String>newHashMapWithExpectedSize(
                attributeNames.size());
        for (String name : attributeNames) {
            String value = attributes.getAttributeAsString(name);
            attributeMap.put(name, value);
        }
        
        this.attributeValuesEvent.setAttributeValues(attributeMap);
        this.attributeValuesEvent.setFeature(vectorFeature);
        super.bus.fireEvent(this.attributeValuesEvent);
    }
}
