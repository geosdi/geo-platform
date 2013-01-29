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
package org.geosdi.geoplatform.gui.client.icons;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
@SuppressWarnings("deprecation")
public interface LayerIcons extends ImageBundle {

    @Resource("layer_folder.png")
    AbstractImagePrototype layerFolder();

    @Resource("line.png")
    AbstractImagePrototype line();

    @Resource("point.png")
    AbstractImagePrototype point();

    @Resource("copy.png")
    AbstractImagePrototype copy();

    @Resource("paste.png")
    AbstractImagePrototype paste();

    @Resource("shape.png")
    AbstractImagePrototype shape();

    @Resource("gp.png")
    AbstractImagePrototype geoPlatform();

    @Resource("addFolder.png")
    AbstractImagePrototype addFolder();

    @Resource("addRaster.png")
    AbstractImagePrototype addRasterLayer();

    @Resource("addVector.png")
    AbstractImagePrototype addVectorLayer();

    @Resource("zoom.png")
    AbstractImagePrototype zoomToMaxExtend();

    @Resource("kml.png")
    AbstractImagePrototype exportToKML();

    @Resource("tag.png")
    AbstractImagePrototype exportToGML();

    @Resource("CSV.png")
    AbstractImagePrototype exportToCSV();

    @Resource("json.png")
    AbstractImagePrototype exportToJSON();

    @Resource("rss.png")
    AbstractImagePrototype exportToRSS();

    @Resource("pdf.png")
    AbstractImagePrototype exportToPDF();

    @Resource("raster.png")
    AbstractImagePrototype exportToTIFF();

    @Resource("shape-zip.png")
    AbstractImagePrototype exportToShpZip();

    @Resource("layer-properties.png")
    AbstractImagePrototype layerProperties();

    @Resource("parse-url.png")
    AbstractImagePrototype loadWmsGetMapFromUrl();

    @Resource("google_earth.png")
    AbstractImagePrototype loadKmlFromURL();

    @Resource("editFolder.png")
    AbstractImagePrototype editFolder();

    @Resource("GPProject.png")
    AbstractImagePrototype gpProject();

    @Resource("addLayers.png")
    AbstractImagePrototype addLayers();

    @Resource("map_add.png")
    AbstractImagePrototype mappAdd();

    @Resource("project_add.png")
    AbstractImagePrototype projectAdd();

    @Resource("project_delete.png")
    AbstractImagePrototype projectDelete();

    @Resource("arrow_refresh.png")
    AbstractImagePrototype arrowRefresh();

    @Resource("filter.png")
    AbstractImagePrototype cqlFilter();

    @Resource("filter_delete.png")
    AbstractImagePrototype cqlFilterDelete();

    @Resource("filter_layer.png")
    AbstractImagePrototype cqlFilterLayerIcon();

    @Resource("time_filter.png")
    AbstractImagePrototype timeFilterLayerIcon();

    @Resource("refresh_layer_cql.png")
    AbstractImagePrototype refreshCqlLayerIcon();

    @Resource("refresh_layer.png")
    AbstractImagePrototype refreshLayerIcon();
}
