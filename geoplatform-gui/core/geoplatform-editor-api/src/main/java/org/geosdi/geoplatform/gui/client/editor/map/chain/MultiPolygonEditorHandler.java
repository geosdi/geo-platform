/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.editor.map.chain;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import org.geosdi.geoplatform.gui.client.editor.map.control.ModifyEditorFeature;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.MultiPolygon;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class MultiPolygonEditorHandler extends GeometryEditorHandler {
    
    public MultiPolygonEditorHandler(ModifyEditorFeature theModifyEditorControl) {
        super(theModifyEditorControl);
    }
    
    @Override
    public void geometryRequest(VectorFeature feature, Vector vector) {
        if (feature.getGeometry().getClassName().equals(
                Geometry.MULTI_POLYGON_CLASS_NAME)) {
            
            if (!checkModifications(feature)) {
                showConfirmMessage(feature, vector);
            }
            
        } else {
            forwardGeometryRequest(feature, vector);
        }
    }
    
    @Override
    protected boolean checkModifications(VectorFeature feature) {
        MultiPolygon oldMultiPolygon = MultiPolygon.narrowToMultiPolygon(
                modifyEditorControl.getSelectedFeature().getGeometry().getJSObject());
        
        MultiPolygon multiPolyon = MultiPolygon.narrowToMultiPolygon(
                feature.getGeometry().getJSObject());
        
        return multiPolyon.equals(oldMultiPolygon);
    }
    
    @Override
    protected void showConfirmMessage(final VectorFeature feature,
            final Vector vector) {
        final VectorFeature selectedFeature = getSelectedFeature();
        
        GeoPlatformMessage.confirmMessage(
                "Multi Polygon Feature Status",
                "The Geometry Multi Polygon Feature is changed. "
                + "Do you want to apply the changes?",
                new Listener<MessageBoxEvent>() {
            
            @Override
            public void handleEvent(MessageBoxEvent be) {
                if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                    manageUpdatedFeature(feature, selectedFeature);
                } else {
                    vector.removeFeature(feature);
                    vector.addFeature(selectedFeature);
                }
            }
            
        });
    }
    
}
