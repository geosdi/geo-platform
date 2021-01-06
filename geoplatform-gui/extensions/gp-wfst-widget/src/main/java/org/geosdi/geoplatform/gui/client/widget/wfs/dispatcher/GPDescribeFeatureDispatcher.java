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
package org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher;

import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.LayerSchemaHandlerManager;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.DescribeFeatureTypeRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.DescribeFeatureTypeResponse;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetMessages;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;

import javax.inject.Inject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDescribeFeatureDispatcher implements DescribeFeatureDispatcher {

    private final LayerSchemaHandlerManager layerSchemaManager;
    private final DescribeFeatureTypeRequest describeFeatureRequest = GWT.<DescribeFeatureTypeRequest>create(DescribeFeatureTypeRequest.class);

    /**
     * @param theLayerSchemaManager
     */
    @Inject
    public GPDescribeFeatureDispatcher(LayerSchemaHandlerManager theLayerSchemaManager) {
        this.layerSchemaManager = theLayerSchemaManager;
    }

    @Override
    public void dispatchDescribeFeatureRequest(final GPLayerBean layer) {
        this.describeFeatureRequest.setServerUrl(layer.getDataSource());
        this.describeFeatureRequest.setTypeName(layer.getName());

        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<DescribeFeatureTypeResponse>() {

            private static final long serialVersionUID = 6130617748457405063L;

            {
                super.setCommandRequest(describeFeatureRequest);
            }

            @Override
            public void onCommandSuccess(
                    DescribeFeatureTypeResponse response) {
                layerSchemaManager.forwardLayerSchema(response.getResult(), layer);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                String errorMessage = WFSTWidgetConstants.INSTANCE.
                        GPDescribeFeatureDispatcher_errorDescribeFeatureTypeRequestText();

                GeoPlatformMessage.errorMessage(
                        WFSTWidgetConstants.INSTANCE.
                                GPDescribeFeatureDispatcher_errorDescribeFeatureTypeTitleText(),
                        errorMessage + " - " + exception.getMessage());

                LayoutManager.getInstance().getStatusMap().setStatus(
                        WFSTWidgetMessages.INSTANCE.errorFeatureTypeRequestForLayerMessage(
                                errorMessage, layer.getName()),
                        SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            }

        });
    }

}
