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
package org.geosdi.geoplatform.gui.client.widget.map.legend;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.widget.map.legend.scale.GPLegendScaleAdapter;
import org.geosdi.geoplatform.gui.client.widget.map.legend.scale.IGPLegendScaleAdapter;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.utility.GSAuthKeyManager;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPLegendWidget {

    public static final String GET_LEGEND_REQUEST = "?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&LAYER=";
    //
    private final IGPLegendScaleAdapter legendScaleAdapter = new GPLegendScaleAdapter();
    protected ContentPanel legendsStore;

    /**
     * @Constructor
     */
    public GPLegendWidget() {
        this.legendsStore = new ContentPanel();
        this.legendsStore.setHeaderVisible(false);
        this.legendsStore.setBodyBorder(false);
        this.legendsStore.setBorders(false);
        //this.legendsStore.setLayout(new BorderLayout());
    }

    /**
     * Add Legend Item in the Legend Store
     *
     * @param layerBean
     */
    public void addLegend(GPLayerBean layerBean) {
        if (this.legendsStore.getItemByItemId(layerBean.getUUID()) == null) {
            ContentPanel cp = new ContentPanel();
            cp.setHeadingText(layerBean.getLabel());
            cp.setId(layerBean.getUUID());
            cp.setHeaderVisible(false);
            cp.setBorders(false);
            cp.setBodyBorder(false);
            cp.add(new Html("<h3>" + layerBean.getLabel() + "</h3>"));
            Image image;
            String dataSource;
            if (layerBean.getDataSource().contains("gwc/service/wms")) {
                dataSource = layerBean.getDataSource().replaceAll("gwc/service/wms", "wms");
            } else if (!(layerBean.getDataSource().startsWith("http://ows"))
                    && (layerBean.getDataSource().contains("/ows"))) {
                dataSource = layerBean.getDataSource().replaceAll("/ows", "/wms");
            } else {
                dataSource = layerBean.getDataSource().replaceAll("/wfs", "/wms");
            }
            StringBuilder imageURL = new StringBuilder();
            imageURL.append(dataSource)
                    .append(!dataSource.contains("?") ? GET_LEGEND_REQUEST : GET_LEGEND_REQUEST.replaceAll("\\?", "&"))
                    .append(layerBean.getName())
                    .append(this.legendScaleAdapter.adaptScale(layerBean))
                    .append("&service=WMS");
            if (layerBean.getStyles() != null && layerBean.getStyles().size() > 0) {
                imageURL.append("&STYLE=").append(layerBean.getStyles().get(0).getStyleString());
            }
            String authkeyTuple = GSAuthKeyManager.getAuthKeyTuple();
            if (!authkeyTuple.equals("")) {
                imageURL.append('&').append(authkeyTuple);
            }
            imageURL.append("&LEGEND_OPTIONS=forceRule:True;forceLabels:on");
            image = new Image(imageURL.toString());
            cp.add(image);
            this.legendsStore.add(cp);
            this.legendsStore.layout();
        }
    }

    /**
     * Remove Legend Item from Legend Store
     *
     * @param layerBean
     */
    public void hideLegendItem(GPLayerBean layerBean) {
        if (this.legendsStore.getItemByItemId(layerBean.getUUID()) != null) {
            this.legendsStore.remove(this.legendsStore.getItemByItemId(layerBean.getUUID()));
            this.legendsStore.layout();
        }
    }

    public void reloadLegend(GPLayerBean layerBean) {
        //This check verify if the legend is already visible on map otherwise 
        //it is unnecessary to reload the legend
        if (this.legendsStore.getItemByItemId(layerBean.getUUID()) != null) {
            this.hideLegendItem(layerBean);
            this.addLegend(layerBean);
        }
    }

    public void resetLegendWidget() {
        this.legendsStore.removeAll();
    }

    /**
     * @return the legendsStore
     */
    public ContentPanel getLegendsStore() {
        return legendsStore;
    }
}
