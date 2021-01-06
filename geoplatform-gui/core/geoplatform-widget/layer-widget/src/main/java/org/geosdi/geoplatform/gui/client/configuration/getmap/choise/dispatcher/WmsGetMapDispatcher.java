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
package org.geosdi.geoplatform.gui.client.configuration.getmap.choise.dispatcher;

import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.command.getmap.CheckWmsGetMapUrlRequest;
import org.geosdi.geoplatform.gui.client.command.getmap.CheckWmsGetMapUrlResponse;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapIncorrectStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapCorrectStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapExecuteEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.form.LoadWmsGetMapFromUrlWidget;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WmsGetMapDispatcher implements IWmsGetMapDispatcher {

    @Inject
    private WmsGetMapCorrectStatusEvent correctStatusEvent;
    @Inject
    private WmsGetMapExecuteEvent executeEvent;
    @Inject
    private WmsGetMapIncorrectStatusEvent incorrectStatusEvent;
    private final CheckWmsGetMapUrlRequest request = new CheckWmsGetMapUrlRequest();

    @Override
    public void verifyUrl(final boolean runExecute, String urlEncoding) {
        this.request.setUrlEncoding(urlEncoding);

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<CheckWmsGetMapUrlResponse>() {

                    private static final long serialVersionUID = -4256238007836463709L;

                    {
                        super.setCommandRequest(request);
                    }

                    @Override
                    public void onCommandSuccess(
                            CheckWmsGetMapUrlResponse response) {
                        System.out.println("ECCOLA : " + response);
                        if (response.getResult()) {
                            LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                                    correctStatusEvent);

                            if (runExecute) { // Iff the enter key is pressed
                                LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                                        executeEvent);
                            }
                        } else {
                            LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                                    incorrectStatusEvent);

                        }
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                                incorrectStatusEvent);

                        GeoPlatformMessage.errorMessage(
                                LayerModuleConstants.INSTANCE.errorCheckingURLTitleText(),
                                WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                LayerModuleConstants.INSTANCE.statusErrorCheckingURLText(),
                                SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    }

                });
    }

}
