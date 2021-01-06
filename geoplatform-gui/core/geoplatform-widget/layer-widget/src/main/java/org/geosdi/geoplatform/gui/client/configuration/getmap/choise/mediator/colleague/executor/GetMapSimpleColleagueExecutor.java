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
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.widget.simple.SimpleUrlTextFields;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapHideWidgetEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.form.LoadWmsGetMapFromUrlWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddLayersFromWmsGetMapEvent;
import org.geosdi.geoplatform.gui.configuration.choice.mediator.colleague.executor.ChoiseColleagueExecutor;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Singleton
public class GetMapSimpleColleagueExecutor implements ChoiseColleagueExecutor {

    @Inject
    private WmsGetMapHideWidgetEvent hideEvent;
    @Inject
    private AddLayersFromWmsGetMapEvent event;
    @Inject
    private SimpleUrlTextFields simpleUrlTextFields;

    public GetMapSimpleColleagueExecutor() {
    }

    @Override
    public <E> void executeColleague(E param) {
        this.event.setLayers(this.createRasterList());

        LayerHandlerManager.fireEvent(event);

        LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(hideEvent);

        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.
                LoadWmsGetMapFromUrlWidget_statusAddedWMSSuccessText(),
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
    }

    private List<GPBeanTreeModel> createRasterList() {
        List<GPBeanTreeModel> rasterList = Lists.<GPBeanTreeModel>newArrayList();
        rasterList.add(mapRaster());
        return rasterList;
    }

    private RasterTreeNode mapRaster() {
        RasterTreeNode raster = new RasterTreeNode();

        raster.setName(simpleUrlTextFields.getLayerName());
        raster.setTitle(simpleUrlTextFields.getLayerName());
        raster.setLabel(raster.getTitle());
        raster.setLayerType(GPLayerType.WMS);
        raster.setDataSource(simpleUrlTextFields.getLayerDatasource());
        raster.setCrs(GPCoordinateReferenceSystem.WGS_84.getCode());
        raster.setBbox(new BBoxClientInfo(-179, -89, 179, 89));
//        raster.setStyles(this.mapStyles());
        return raster;
    }

}
