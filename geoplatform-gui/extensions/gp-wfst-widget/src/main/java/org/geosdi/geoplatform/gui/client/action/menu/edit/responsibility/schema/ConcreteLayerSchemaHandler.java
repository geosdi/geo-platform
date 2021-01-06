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
package org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema;

import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.client.action.menu.strategy.IActionStrategy;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetMessages;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

import javax.inject.Inject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ConcreteLayerSchemaHandler extends LayerSchemaParserHandler {

    private final IActionStrategy actionStrategy;
    private final GPEventBus bus;
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;

    @Inject
    public ConcreteLayerSchemaHandler(IActionStrategy actionStrategy, GPEventBus theBus) {
        this.actionStrategy = actionStrategy;
        this.bus = theBus;
    }

    @Override
    public void layerSchemaParser(LayerSchemaDTO schemaDTO, GPLayerBean layer) {
        if (schemaDTO != null) {
            this.showFeatureWidget(schemaDTO, layer);
        } else {
            super.forwardLayerSchema(schemaDTO, layer);
        }
    }

    private void showFeatureWidget(LayerSchemaDTO schemaDTO, GPLayerBean layer) {
        String geometryType = schemaDTO.getGeometry().getType();
        layer.setLayerType(
                GPLayerType.valueOf(geometryType.toUpperCase()));

        LayoutManager.getInstance().getStatusMap().setStatus(
                WFSTWidgetMessages.INSTANCE.layerWFSOfGeometryTypeMessage(
                        layer.getName(), geometryType),
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());

        this.layerSchemaBinder.bind(layer, schemaDTO);
        this.actionStrategy.showWidget();
    }
}
