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
package org.geosdi.geoplatform.gui.client.widget.map.control;

import org.geosdi.geoplatform.gui.client.widget.map.responsibility.GeometryRequestHandler;
import org.geosdi.geoplatform.gui.client.widget.map.responsibility.GeometryRequestManager;
import org.geosdi.geoplatform.gui.client.widget.map.responsibility.LineRequestHandler;
import org.geosdi.geoplatform.gui.client.widget.map.responsibility.PointRequestHandler;
import org.geosdi.geoplatform.gui.client.widget.map.responsibility.PolygonRequestHandler;
import org.geosdi.geoplatform.gui.impl.map.control.GPVectorMapControl;
import org.gwtopenmaps.openlayers.client.control.ModifyFeature;
import org.gwtopenmaps.openlayers.client.event.VectorAfterFeatureModifiedListener;
import org.gwtopenmaps.openlayers.client.event.VectorBeforeFeatureModifiedListener;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ModifyFeatureControl extends GPVectorMapControl {

    private ModifyFeature control;
    private VectorFeature selectedFeature;
    private GeometryRequestManager requestManager;
    private GeometryRequestHandler pointHandler;
    private GeometryRequestHandler lineHandler;
    private GeometryRequestHandler polygonHandler;

    public ModifyFeatureControl(Vector vector) {
        super(vector, false);
        this.createResponsibilityComponent();
    }

    private void createResponsibilityComponent() {
        this.requestManager = new GeometryRequestManager(vector);
        this.pointHandler = new PointRequestHandler(this);
        this.lineHandler = new LineRequestHandler(this);
        this.polygonHandler = new PolygonRequestHandler(this);
        this.pointHandler.setSuperiorRequestHandler(lineHandler);
        this.lineHandler.setSuperiorRequestHandler(polygonHandler);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.map.control.MapControl#createControl()
     */
    @Override
    public void createControl() {
        if (!initialized) {
            this.control = new ModifyFeature(vector);

            vector.addVectorBeforeFeatureModifiedListener(
                    new VectorBeforeFeatureModifiedListener() {
                @Override
                public void onBeforeFeatureModified(
                        BeforeFeatureModifiedEvent eventObject) {
                    selectedFeature = eventObject.getVectorFeature().clone();
                }
            });

            vector.addVectorAfterFeatureModifiedListener(
                    new VectorAfterFeatureModifiedListener() {
                @Override
                public void onAfterFeatureModified(
                        AfterFeatureModifiedEvent eventObject) {

                    VectorFeature feature = eventObject.getVectorFeature();

                    requestManager.forwardRequest(pointHandler, feature);

                }
            });

            this.initialized = true;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.map.control.MapControl#activateControl()
     */
    @Override
    public void activateControl() {
        this.control.activate();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.map.control.MapControl#deactivateControl()
     */
    @Override
    public void deactivateControl() {
        this.control.deactivate();
    }

    @Override
    public boolean isEnabled() {
        return this.control.isActive();
    }

    @Override
    public ModifyFeature getControl() {
        return (this.control != null) ? this.control
                : this.initializeMapControl();
    }

    /**
     * @return the oldFeature
     */
    public VectorFeature getSelectedFeature() {
        return selectedFeature;
    }

    @Override
    protected ModifyFeature initializeMapControl() {
        this.createControl();

        return this.control;
    }
}
