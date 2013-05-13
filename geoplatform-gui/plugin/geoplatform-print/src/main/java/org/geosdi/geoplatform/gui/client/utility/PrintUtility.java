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
package org.geosdi.geoplatform.gui.client.utility;

import com.extjs.gxt.ui.client.widget.Info;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.PrintTemplate;
import org.geosdi.geoplatform.gui.client.model.Scale;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.OpenLayers;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.StyleMap;
import org.gwtopenmaps.openlayers.client.control.DragFeature;
import org.gwtopenmaps.openlayers.client.control.DragFeature.DragFeatureListener;
import org.gwtopenmaps.openlayers.client.control.DragFeatureOptions;
import org.gwtopenmaps.openlayers.client.control.SelectFeature;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class PrintUtility {

    public static List<DPI> getDPI() {
        List<DPI> dpi = new ArrayList<DPI>();

        dpi.add(new DPI("72"));
        dpi.add(new DPI("127"));
        dpi.add(new DPI("190"));
        dpi.add(new DPI("254"));
        dpi.add(new DPI("300"));
        dpi.add(new DPI("600"));

        return dpi;
    }

    public static List<PrintTemplate> getTemplate() {
        List<PrintTemplate> templates = new ArrayList<PrintTemplate>();

        templates.add(new PrintTemplate("A4 Portrait"));
        templates.add(new PrintTemplate("A3 Portrait"));
        templates.add(new PrintTemplate("A2 Portrait"));
        templates.add(new PrintTemplate("A1 Portrait"));
        templates.add(new PrintTemplate("A0 Portrait"));
        templates.add(new PrintTemplate("A4 Landscape"));
        templates.add(new PrintTemplate("A3 Landscape"));
        templates.add(new PrintTemplate("A2 Landscape"));
        templates.add(new PrintTemplate("A1 Landscape"));
        templates.add(new PrintTemplate("A0 Landscape"));
        return templates;
    }

    public static List<Scale> getScale() {
        List<Scale> scales = new ArrayList<Scale>();

        scales.add(new Scale("1:1.000"));
        scales.add(new Scale("1:1.600"));
        scales.add(new Scale("1:2.000"));
        scales.add(new Scale("1:5.000"));
        scales.add(new Scale("1:10.000"));
        scales.add(new Scale("1:20.000"));
        scales.add(new Scale("1:50.000"));
        scales.add(new Scale("1:100.000"));
        scales.add(new Scale("1:200.000"));
        scales.add(new Scale("1:500.000"));
        scales.add(new Scale("1:1.000.000"));
        scales.add(new Scale("1:2.000.000"));
        scales.add(new Scale("1:4.000.000"));

        return scales;

    }

    public static Vector createRectangle(LonLat center, float scale, Map map, double sizeFactor, boolean portrait) {
        
        Bounds extent = getExtent(center, scale, map, sizeFactor, portrait);
        Geometry rect = extent.toGeometry();
        VectorFeature features = new VectorFeature(rect);
        Vector vectorLayer = new Vector("VectorPrintExtent");
        vectorLayer.addFeature(features);

        //Define a style for the vectors
        Style style = new Style();
        style.setFillColor("white");
        style.setStrokeColor("violet");
        style.setStrokeWidth(1);
        style.setStrokeDashstyle("dash");

        style.setFillOpacity(0.4);

 
        StyleMap styleMap = new StyleMap(style);

        vectorLayer.setStyleMap(styleMap);
        
        DragFeature dragFeature = createDragFeature(vectorLayer);
        map.addControl(dragFeature);
        dragFeature.activate();
        
        
        
        return vectorLayer;
    }
    
    
    public static DragFeature enableDragPrintArea(Map map, Vector vectorLayer){
        DragFeature dragFeature = createDragFeature(vectorLayer);
        map.addControl(dragFeature);
        //dragFeature.activate();
        
        return dragFeature;
    }
    
    public static void disableDragPrintArea(Map map, DragFeature dragFeature){
        map.removeControl(dragFeature);
    }

    public static Bounds getExtent(LonLat center, float scale, Map map, double sizeFactor, boolean portrait) {
        double unitRatio = OpenLayers.getInchesPerUnitMeter();
        Size size = map.getSize();
        double w, h;
        if (!portrait) {
            w = size.getWidth() * sizeFactor / 72.0 / unitRatio * scale / 2.0;
            h = size.getHeight() * sizeFactor / 72.0 / unitRatio * scale / 2.0;
        } else {
            h = size.getWidth() * sizeFactor / 72.0 / unitRatio * scale / 2.0;
            w = size.getHeight() * sizeFactor / 72.0 / unitRatio * scale / 2.0;
        }

        Bounds bbox = new Bounds(center.lon() - w, center.lat() - h, center.lon() + w, center.lat() + h);
        return bbox;


    }

    private static DragFeature createDragFeature(Vector layer) {
        DragFeatureOptions dragFeatureOptions = new DragFeatureOptions();
        dragFeatureOptions.onDrag(createDragFeatureListener("onDrag"));
        dragFeatureOptions.onStart(createDragFeatureListener("onStart"));
        dragFeatureOptions.onComplete(createDragFeatureListener("onComplete"));

        DragFeature dragFeature = new DragFeature(layer, dragFeatureOptions);
        return dragFeature;
    }

    private static DragFeatureListener createDragFeatureListener(final String type) {
        return new DragFeatureListener() {
            public void onDragEvent(VectorFeature vectorFeature,
                    Pixel pixel) {
                Info.display("test", pixel.toString());
            }
        };
    }
}
