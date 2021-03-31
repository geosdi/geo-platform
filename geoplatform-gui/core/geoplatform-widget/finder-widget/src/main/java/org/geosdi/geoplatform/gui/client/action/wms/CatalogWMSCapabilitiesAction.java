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
package org.geosdi.geoplatform.gui.client.action.wms;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderMessages;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.components.wms.CatalogWMSCapabilitiesWidget;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.command.capabilities.basic.BasicCapabilitiesRequest;
import org.geosdi.geoplatform.gui.command.capabilities.basic.BasicCapabilitiesResponse;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.oauth2.OAuth2HandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPOAuth2GEBLoginEvent;
import org.geosdi.geoplatform.gui.utility.oauth2.EnumOAuth2;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogWMSCapabilitiesAction extends SelectionListener<ButtonEvent> {

    private final CatalogWMSCapabilitiesWidget wmsCapabilitiesWidget;

    /**
     * @param tree
     * @param bus
     */
    public CatalogWMSCapabilitiesAction(TreePanel tree, GPEventBus bus) {
        this.wmsCapabilitiesWidget = new CatalogWMSCapabilitiesWidget(tree, bus);
    }

    @Override
    public void componentSelected(ButtonEvent ce) {
        final String serverURL = this.wmsCapabilitiesWidget.getWMSGetCapabilitiesURL();
        if (serverURL == null) {
            GeoPlatformMessage.errorMessage("Error", "The WMS GetCapabilities URL is null.");
            return;
        }
        LayoutManager.getInstance().getStatusMap().setBusy(WindowsConstants.INSTANCE.loadingLayersText());
        this.wmsCapabilitiesWidget.show();

        final BasicCapabilitiesRequest capabilitiesRequest = GWT.create(BasicCapabilitiesRequest.class);
        capabilitiesRequest.setServerUrl(serverURL);

        GPClientCommandExecutor.executeCommand(new GPClientCommand<BasicCapabilitiesResponse>() {

            private static final long serialVersionUID = 3945178498166588993L;

            {
                super.setCommandRequest(capabilitiesRequest);
            }

            @Override
            public void onCommandSuccess(BasicCapabilitiesResponse response) {
                wmsCapabilitiesWidget.unMaskGrid();
                wmsCapabilitiesWidget.fillStore(response.getResult());
                LayoutManager.getInstance().getStatusMap().setStatus(
                        CatalogFinderConstants.INSTANCE
                        .CatalogWMSCapabilitiesWidget_statusLayerLoadedCorrectlyText(),
                        SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                wmsCapabilitiesWidget.unMaskGrid();
                LayoutManager.getInstance().getStatusMap().clearStatus(
                        "");

                if (serverURL.contains(EnumOAuth2.GEB_STRING.getValue())) {
                    GeoPlatformMessage.infoMessage(
                            CatalogFinderConstants.INSTANCE.
                            googleSignOnRequiredTitleText(),
                            CatalogFinderConstants.INSTANCE.
                            googleSignOnRequiredBodyText());
                    OAuth2HandlerManager.fireEvent(
                            new GPOAuth2GEBLoginEvent(
                                    EnumOAuth2.LOAD_CAPABILITIES.getValue()));
                } else {
                    GeoPlatformMessage.errorMessage(
                            "Catalog Finder",
                            exception.getMessage());
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            CatalogFinderMessages.INSTANCE
                            .CatalogWMSCapabilities_serverErrorMessage(
                                    exception.getMessage()),
                            SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR
                            .toString());
                }
            }

        });
    }

}
