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
package org.geosdi.geoplatform.gui.client.widget.map;

import org.geosdi.geoplatform.gui.client.widget.map.control.*;
import org.geosdi.geoplatform.gui.client.widget.map.control.crud.GenericFeatureOperation;
import org.geosdi.geoplatform.gui.client.widget.map.control.history.NavigationHistoryControl;
import org.geosdi.geoplatform.gui.client.widget.map.style.VectorFeatureStyle;
import org.geosdi.geoplatform.gui.client.widget.viewport.ViewportUtility;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.ModifyFeature;
import org.gwtopenmaps.openlayers.client.control.SelectFeature;
import org.gwtopenmaps.openlayers.client.feature.Feature;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.MultiPolygon;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;

import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.GOOGLE_MERCATOR;
import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.WGS_84;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MapControlManager {

    private final Map map;
    private Vector vector;
    private final VectorFeatureStyle style;
    private DrawPolygonControl drawFeature;
    private DrawRegularPolygonControl drawCircle;
    private DrawPointFeature drawPointFeature;
    private DrawLineFeature drawLineFeature;
    private ModifyFeatureControl modifyFeature;
    private GenericFeatureOperation featureOperation;
    private NavigationHistoryControl navigationHistory;

    /**
     * @param map
     */
    public MapControlManager(Map map) {
        this.map = map;
        this.style = new VectorFeatureStyle();
        this.initVectorLayer();
    }

    /**
     * Create a vector layer to add to the map which defines a set of controls
     */
    private void initVectorLayer() {
        VectorOptions vectorOption = new VectorOptions();
        vectorOption.setStyle(this.style.getVectorStyle());
        vectorOption.setDisplayInLayerSwitcher(false);
        this.vector = new Vector("GeoPlatform Vector Layer", vectorOption);
        this.map.addLayer(vector);

        this.initControl();

        this.addMapControl();
    }

    /**
     * Initialize Control on Vector Layer
     */
    private void initControl() {
        this.drawFeature = new DrawPolygonControl(vector);
        this.drawCircle = new DrawRegularPolygonControl(vector);
        this.drawPointFeature = new DrawPointFeature(vector);
        this.drawLineFeature = new DrawLineFeature(vector);
        this.modifyFeature = new ModifyFeatureControl(vector);
        this.featureOperation = new GenericFeatureOperation(vector);
        this.navigationHistory = new NavigationHistoryControl();
    }

    /**
     * Add Control to the Map
     */
    private void addMapControl() {
        this.map.addControl(this.drawFeature.getControl());
        this.map.addControl(this.drawCircle.getControl());
        this.map.addControl(this.drawLineFeature.getControl());
        this.map.addControl(this.drawPointFeature.getControl());
        this.map.addControl(this.modifyFeature.getControl());
        this.map.addControl(this.featureOperation.getControl());
        this.map.addControl(this.navigationHistory.getControl());
    }

    /**
     * Draw Feature on the Map
     *
     * @param wkt
     */
    public void drawFeatureOnMap(String wkt) {
        MultiPolygon geom = MultiPolygon.narrowToMultiPolygon(Geometry.fromWKT(wkt).getJSObject());
        geom.transform(new Projection(WGS_84.getCode()), new Projection(GOOGLE_MERCATOR.getCode()));

        VectorFeature vectorFeature = new VectorFeature(geom);
        this.vector.addFeature(vectorFeature);
        this.map.zoomToExtent(geom.getBounds());

        Projection projection = new Projection(WGS_84.getCode());
        Projection mapProjection = new Projection(map.getProjection());
        Bounds mapBounds = this.map.getExtent().transform(mapProjection, projection);
        BBoxClientInfo bbox = ViewportUtility.generateBBOXFromBounds(mapBounds);
    }

    /**
     * @param feature
     */
    public void drawFeature(VectorFeature feature) {
        if (vector.getFeatureById(feature.getFeatureId()) == null) {
            this.vector.addFeature(feature);
        }
        this.map.zoomToExtent(feature.getGeometry().getBounds());
    }

    /**
     * Erase all Features and the corrispective popups added to the Vector Layer
     */
    public void eraseFeatures() {
        Feature[] features = this.vector.getFeatures();
        if (GPSharedUtils.isNotEmpty(features)) {
            for (Feature feature : this.vector.getFeatures()) {
                if (feature.getPopup() != null) {
                    feature.resetPopup();
                }
            }
        }
        this.vector.destroyFeatures();
    }

    /**
     * Erase Single Feature from the Map
     *
     * @param vf
     */
    public void eraseFeature(VectorFeature vf) {
        this.vector.removeFeature(vf);
        this.vector.redraw();
    }

    /**
     * activate draw feature control on the map
     */
    public void activateDrawFeature() {
        this.drawFeature.activateControl();
    }

    /**
     * deactivate draw feature control on the map
     */
    public void deactivateDrawFeature() {
        this.drawFeature.deactivateControl();
    }

    /**
     * activate draw feature control on the map
     */
    public void activateDrawCircleFeature() {
        this.drawCircle.activateControl();
    }

    /**
     * deactivate draw feature control on the map
     */
    public void deactivateDrawCircleFeature() {
        this.drawCircle.deactivateControl();
    }

    /**
     * @return DrawFeature
     */
    public DrawFeature getDrawFeatureControl() {
        return this.drawFeature.getControl();
    }

    public DrawFeature getDrawCircleFeatureControl() {
        return this.drawCircle.getControl();
    }

    public DrawFeature getDrawPointFeaureControl() {
        return this.drawPointFeature.getControl();
    }

    /**
     * @return ModifyFeature
     */
    public ModifyFeature getModifyFeatureControl() {
        return this.modifyFeature.getControl();
    }

    /**
     * @return SelectFeature
     */
    public SelectFeature getSelectFeatureControl() {
        return this.featureOperation.getControl();
    }

    /**
     * @return the featureOperation
     */
    public GenericFeatureOperation getFeatureOperation() {
        return featureOperation;
    }

    /**
     * @return the navigationHistory
     */
    public NavigationHistoryControl getNavigationHistory() {
        return navigationHistory;
    }

    /**
     * @return the drawLineFeature
     */
    public DrawLineFeature getDrawLineFeature() {
        return drawLineFeature;
    }

    /**
     * Redraw the Vector Layer
     */
    public void redrawVectorLayer() {
        this.vector.redraw();
    }

    public void activateDrawPointFeature() {
        this.drawPointFeature.activateControl();
    }

    public void deactivateDrawPointFeature() {
        this.drawPointFeature.deactivateControl();
    }

    public void activateFeatureOperation() {
        this.featureOperation.activateControl();
    }

    public void deactivateFeatureOperation() {
        this.featureOperation.deactivateControl();
    }

    public boolean isFeatureOperationEnable() {
        return this.featureOperation.isEnabled();
    }

    public void activateModifyFeature() {
        this.modifyFeature.activateControl();
    }

    public void deactivateModifyFeature() {
        this.modifyFeature.deactivateControl();
    }

    public boolean isModifyFeatureEnable() {
        return this.modifyFeature.isEnabled();
    }

    public int getFeaturesNumber() {
        return this.vector.getNumberOfFeatures();
    }

    public void clearNavigationHistory() {
        this.navigationHistory.clearHistory();
    }

    public void activateNavigationHistory() {
        this.navigationHistory.activateControl();
    }

    public void deactivateNavigationHistory() {
        this.navigationHistory.deactivateControl();
    }

    public void activateDrawLineFeature() {
        this.drawLineFeature.activateControl();
    }

    public void deactivateDrawLineFeature() {
        this.drawLineFeature.deactivateControl();
    }

}
