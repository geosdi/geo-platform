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
package org.geosdi.geoplatform.gui.client.configuration.getmap.choise.mediator.colleague.executor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.encoder.GetMapUrlEncoder;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapHideWidgetEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.form.LoadWmsGetMapFromUrlWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddLayersFromWmsGetMapEvent;
import org.geosdi.geoplatform.gui.configuration.choice.mediator.colleague.executor.ChoiseColleagueExecutor;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.regex.GetMap;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */


@Singleton
public class GetMapKvpColleagueExecutor implements ChoiseColleagueExecutor {

    @Inject
    private WmsGetMapHideWidgetEvent hideEvent;
    @Inject
    private GetMapUrlEncoder urlEncoder;
    @Inject
    private AddLayersFromWmsGetMapEvent event;
    private final Map<String, String> fieldMap = Maps.<String, String>newHashMap();

    public GetMapKvpColleagueExecutor() {
    }

    @Override
    public <E> void executeColleague(E param) {
        this.retrieveDataFromQueryString();
        this.event.setLayers(this.createRasterList());

        LayerHandlerManager.fireEvent(event);

        LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(hideEvent);

        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.
                LoadWmsGetMapFromUrlWidget_statusAddedWMSSuccessText(),
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
    }

    private void retrieveDataFromQueryString() {
        String urlEncoding = this.urlEncoder.getUrlEncoding();
        String queryString = urlEncoding.substring(urlEncoding.indexOf("?") + 1);
//        System.out.println("*** Query String: " + queryString + "\n");

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int ind = pair.indexOf("=");
            System.out.println("# " + pair);

            String field = pair.substring(0, ind);
            String fieldValue = pair.substring(ind + 1);

            fieldMap.put(field.toUpperCase(), fieldValue);
        }
    }

    private List<GPBeanTreeModel> createRasterList() {
        List<GPBeanTreeModel> rasterList = Lists.<GPBeanTreeModel>newArrayList();

        String layersValue = fieldMap.get(GetMap.LAYERS.toString());
        if (layersValue.contains(",")) { // More than one raster
            String[] rasters = layersValue.split(",");
            for (String raster : rasters) {
                rasterList.add(this.mapRaster(raster));
            }
        } else { // A single raster
            rasterList.add(this.mapRaster(layersValue));
        }

        return rasterList;
    }

    private RasterTreeNode mapRaster(String rasterString) {
        RasterTreeNode raster = new RasterTreeNode();

        raster.setName(rasterString);
        raster.setTitle(this.mapTitle(rasterString));
        raster.setLabel(raster.getTitle());
        raster.setLayerType(GPLayerType.WMS);
        raster.setDataSource(this.mapDataSource());
        raster.setCrs(fieldMap.get(GetMap.SRS.toString()));
        raster.setBbox(this.mapBbox());
        raster.setStyles(this.mapStyles());

//        System.out.println("\n*** Raster to ADD:\n" + raster + "\n***");
        return raster;
    }

    private String mapTitle(String rasterString) {
        if (rasterString.indexOf(":") != -1) { // workspace:name
            return rasterString.substring(rasterString.indexOf(":") + 1);
        }
        return rasterString;
    }

    private String mapDataSource() {
        String urlEncoding = this.urlEncoder.getUrlEncoding();
        return urlEncoding.substring(0, urlEncoding.indexOf("?"));
    }

    private BBoxClientInfo mapBbox() {
        String[] coordinates = fieldMap.get(GetMap.BBOX.toString()).split(",");

        BBoxClientInfo bbox = new BBoxClientInfo();
        bbox.setLowerLeftX(Double.parseDouble(coordinates[0]));
        bbox.setLowerLeftY(Double.parseDouble(coordinates[1]));
        bbox.setUpperRightX(Double.parseDouble(coordinates[2]));
        bbox.setUpperRightY(Double.parseDouble(coordinates[3]));
        return bbox;
    }

    private ArrayList<GPStyleStringBeanModel> mapStyles() {
        ArrayList<GPStyleStringBeanModel> styleList = Lists.<GPStyleStringBeanModel>newArrayList();

        String stylesValue = fieldMap.get(GetMap.STYLES.toString());
        if (stylesValue != null && stylesValue.length() > 0) {
            String[] styles = stylesValue.split(",");
            for (String string : styles) {
//                System.out.println("### Style:" + string);
                GPStyleStringBeanModel style = new GPStyleStringBeanModel();
                style.setStyleString(string);
                styleList.add(style);
            }
        }

        return styleList;
    }

}
