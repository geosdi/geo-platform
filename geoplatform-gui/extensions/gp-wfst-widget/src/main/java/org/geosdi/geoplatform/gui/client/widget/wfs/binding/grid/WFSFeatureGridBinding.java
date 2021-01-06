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
package org.geosdi.geoplatform.gui.client.widget.wfs.binding.grid;

import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.client.model.binder.IFeatureIdBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureAttributeValuesDetail;
import org.geosdi.geoplatform.gui.client.widget.wfs.binding.WFSFeatureBinding;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.WFSToggleButton;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import javax.inject.Inject;

import static org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureSelectListener.enableToggleState;

/**
 * <p>This class is responsible to show {@link VectorFeature} Feature on the Map when
 * a user click on row of {@link com.extjs.gxt.ui.client.widget.grid.EditorGrid<FeatureAttributeValuesDetail>}
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSFeatureGridBinding implements WFSFeatureBinding {

    private final GPEventBus bus;
    private final HandlerRegistration handlerRegistration;
    @Inject
    private Vector vectorLayer;
    @Inject
    private IFeatureIdBinder fidBinder;
    @Inject
    private MapWidget mapWidget;

    @Inject
    public WFSFeatureGridBinding(GPEventBus theBus) {
        this.bus = theBus;

        this.handlerRegistration = addFeatureBindingHandler();
    }

    @Override
    public HandlerRegistration addFeatureBindingHandler() {
        return this.bus.addHandler(TYPE, this);
    }

    @Override
    public void bindVectoreFeature(VectorFeature vectorFeature) {
        assert (vectorFeature != null) : "The VectorFeature to bind must not be null.";

        vectorFeature.toState(VectorFeature.State.Unknown);

        if (vectorLayer.getNumberOfFeatures() > 0) {
            vectorLayer.removeAllFeatures();
        }

        vectorLayer.addFeature(vectorFeature);
        this.fidBinder.setFID(vectorFeature.getFID());
        this.mapWidget.getMap().zoomToExtent(vectorFeature.getGeometry().getBounds());

        WFSToggleButton.fireEnableToggleStateEvent(enableToggleState);
    }
}
