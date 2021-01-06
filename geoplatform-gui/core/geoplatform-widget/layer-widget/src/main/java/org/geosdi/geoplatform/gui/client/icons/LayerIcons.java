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
package org.geosdi.geoplatform.gui.client.icons;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface LayerIcons extends ClientBundle {

    @Source("forward.png")
    ImageResource forwardTimeDimension();

    @Source("backward.png")
    ImageResource backwardTimeDimension();

    @Source("play_td.png")
    ImageResource playTimeDimension();

    @Source("pause_td.png")
    ImageResource pauseTimeDimension();

    @Source("play_reverse.png")
    ImageResource playReverseTimeDimension();

    @Source("skip_forward.png")
    ImageResource forwardTime();

    @Source("skip_backward.png")
    ImageResource backwardTime();

    @Source("play.png")
    ImageResource playTime();

    @Source("pause.png")
    ImageResource pauseTime();

    @Source("shuffle_time.png")
    ImageResource shuffleTime();

    @Source("layer_folder.png")
    ImageResource layerFolder();

    @Source("line.png")
    ImageResource line();

    @Source("point.png")
    ImageResource point();

    @Source("copy.png")
    ImageResource copy();

    @Source("paste.png")
    ImageResource paste();

    @Source("shape.png")
    ImageResource shape();

    @Source("gp.png")
    ImageResource geoPlatform();

    @Source("addFolder.png")
    ImageResource addFolder();

    @Source("addRaster.png")
    ImageResource addRasterLayer();

    @Source("addVector.png")
    ImageResource addVectorLayer();

    @Source("zoom.png")
    ImageResource zoomToMaxExtend();

    @Source("kml.png")
    ImageResource exportToKML();

    @Source("tag.png")
    ImageResource exportToGML();

    @Source("CSV.png")
    ImageResource exportToCSV();

    @Source("json.png")
    ImageResource exportToJSON();

    @Source("rss.png")
    ImageResource exportToRSS();

    @Source("pdf.png")
    ImageResource exportToPDF();

    @Source("raster.png")
    ImageResource exportToTIFF();

    @Source("shape-zip.png")
    ImageResource exportToShpZip();

    @Source("layer-properties.png")
    ImageResource layerProperties();

    @Source("parse-url.png")
    ImageResource loadWmsGetMapFromUrl();

    @Source("google_earth.png")
    ImageResource loadKmlFromURL();

    @Source("editFolder.png")
    ImageResource editFolder();

    @Source("GPProject.png")
    ImageResource gpProject();

    @Source("addLayers.png")
    ImageResource addLayers();

    @Source("map_add.png")
    ImageResource mappAdd();

    @Source("project_add.png")
    ImageResource projectAdd();

    @Source("project_delete.png")
    ImageResource projectDelete();

    @Source("arrow_refresh.png")
    ImageResource arrowRefresh();

    @Source("filter.png")
    ImageResource cqlFilter();

    @Source("filter_delete.png")
    ImageResource cqlFilterDelete();

    @Source("filter_layer.png")
    ImageResource cqlFilterLayerIcon();

    @Source("time_filter.png")
    ImageResource timeFilterLayerIcon();

    @Source("refresh_layer_cql.png")
    ImageResource refreshCqlLayerIcon();

    @Source("refresh_layer.png")
    ImageResource refreshLayerIcon();
}
