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
package org.geosdi.geoplatform.gui.client.widget.wfs.map.control.getfeature;

import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.InjectGetFeatureModelEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.GetFeatureControlBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureSelectListener;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureUnSelectListener;
import org.geosdi.geoplatform.gui.impl.map.control.GPVectorMapControl;
import org.geosdi.geoplatform.gui.impl.map.control.feature.GetFeatureModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;
import org.gwtopenmaps.openlayers.client.control.GetFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import javax.inject.Inject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WFSGetFeatureControl extends GPVectorMapControl implements
        IWFSGetFeatureControl {

    private static final GPEventBus getFeatureBus = new GPEventBusImpl();
    //
    @Inject
    private GetFeatureControlBuilder featureControlBuilder;
    @Inject
    private FeatureSelectListener selectFeature;
    @Inject
    private FeatureUnSelectListener unSelectFeature;
    private GetFeature wfsGetFeature;
    private GetFeatureModel featureModel;

    @Inject
    public WFSGetFeatureControl(Vector vector) {
        super(vector, true);

        addGetFeatureHandler();
    }

    public static void fireInjectGetFeatureModelEvent(
            InjectGetFeatureModelEvent event) {
        getFeatureBus.fireEvent(event);
    }

    @Override
    protected GetFeature initializeMapControl() {
        this.createControl();

        return this.wfsGetFeature;
    }

    @Override
    public GetFeature getControl() {
        return (wfsGetFeature != null) ? wfsGetFeature : initializeMapControl();
    }

    @Override
    public void createControl() {
        if (!initialized) {
            if (featureModel == null) {
                throw new IllegalStateException(
                        "The Feature Model can't be null "
                                + "to initialize WFSGetFeatureControl.");
            }

            this.wfsGetFeature = this.featureControlBuilder.buildControl(
                    featureModel);

            this.wfsGetFeature.addFeatureSelectedListener(selectFeature);
            this.wfsGetFeature.addFeatureUnselectedListener(unSelectFeature);

            initialized = true;
        }
    }

    @Override
    public void activateControl() {
        this.wfsGetFeature.activate();
    }

    @Override
    public void deactivateControl() {
        this.wfsGetFeature.deactivate();
    }

    @Override
    public boolean isEnabled() {
        return this.wfsGetFeature.isActive();
    }

    @Override
    public void resetControl() {
        if (this.wfsGetFeature != null) {
            deactivateControl();
            this.initialized = false;
            this.wfsGetFeature.destroy();
            this.wfsGetFeature = null;
        }
    }

    @Override
    public HandlerRegistration addGetFeatureHandler() {
        return getFeatureBus.addHandler(TYPE, this);
    }

    @Override
    public void injectGetFeatureModel(GetFeatureModel theFeatureModel) {
        this.featureModel = theFeatureModel;
    }

}
