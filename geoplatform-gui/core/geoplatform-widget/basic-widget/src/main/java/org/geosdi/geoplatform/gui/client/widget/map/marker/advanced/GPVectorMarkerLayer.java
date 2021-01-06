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
package org.geosdi.geoplatform.gui.client.widget.map.marker.advanced;

import org.geosdi.geoplatform.gui.client.widget.map.marker.GPGenericMarkerLayer;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.DragFeature;
import org.gwtopenmaps.openlayers.client.control.DragFeature.DragFeatureListener;
import org.gwtopenmaps.openlayers.client.control.DragFeatureOptions;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPVectorMarkerLayer extends GPGenericMarkerLayer {

    protected VectorFeature feature;
    protected Style style;
    protected DragFeature dragControl;

    public GPVectorMarkerLayer(Map theMap, String theMarkerLayerName) {
        super(theMap, theMarkerLayerName);
        this.createControl();
    }

    @Override
    public void buildIconMarker() {
        this.style = new Style();
        style.setFillOpacity(1);
        setIconStyle();
        style.setFontColor("blue");
        style.setFontSize("13px");
        style.setFontWeight("bold");
        style.setLabelAlign("cb");
        style.setGraphicSize(25, 41);
        style.setGraphicOffset(0, -41);
    }

    protected void drawFeature(LonLat lonlat) {
        if (feature != null) {
            ((Vector) markerLayer).removeFeature(feature);
        }

        Point p = new Point(lonlat.lon(), lonlat.lat());
        feature = new VectorFeature(p);
        feature.setStyle(style);
        ((Vector) markerLayer).addFeature(feature);

        ((Vector) markerLayer).redraw();
    }

    /**
     * Remove VectoreFeature as a Marker from Markers Vector Layer
     *
     */
    @Override
    public void removeMarker() {
        if (this.feature != null) {
            ((Vector) this.markerLayer).removeFeature(feature);
        }   
    }

    /**
     * Activate Drag Control on VectoreFeature as a Marker
     *
     */
    public void addControl() {
        map.addControl(dragControl);
        dragControl.activate();
    }

    /**
     * Deactivate Drag Control on VectoreFeature as a Marker
     *
     */
    public void removeControl() {
        this.dragControl.deactivate();
        map.removeControl(dragControl);
    }

    public abstract void setIconStyle();

    private void createControl() {
        DragFeatureOptions dragFeatureOptions = new DragFeatureOptions();

        dragFeatureOptions.onComplete(new DragFeatureListener() {

            @Override
            public void onDragEvent(VectorFeature vectorFeature, Pixel pixel) {
                LonLat ll = map.getLonLatFromPixel(pixel);

                featureDragged(ll);
            }

        });

        this.dragControl = new DragFeature((Vector) markerLayer,
                dragFeatureOptions);
    }

    public abstract void featureDragged(LonLat ll);

    /**
     * @return the feature
     */
    public VectorFeature getFeature() {
        return feature;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    public boolean isDragControlActive() {
        return this.dragControl.isActive();
    }

}
