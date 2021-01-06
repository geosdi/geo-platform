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
package org.geosdi.geoplatform.gui.client.action.baselayer;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import org.geosdi.geoplatform.gui.client.command.SaveBaseLayerRequest;
import org.geosdi.geoplatform.gui.client.command.GPMapModuleResponse;
import org.geosdi.geoplatform.gui.client.i18n.MapModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.baselayer.BaseLayerWidget;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformSecureAction;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SaveBaseLayerAction extends GeoPlatformSecureAction<ComponentEvent> {

    private final BaseLayerWidget widget;
    private final SaveBaseLayerRequest saveBaseLayerRequest = new SaveBaseLayerRequest();

    public SaveBaseLayerAction(GPTrustedLevel trustedLevel,
            BaseLayerWidget widget) {
        super(trustedLevel);
        this.widget = widget;
    }

    @Override
    public void componentSelected(ComponentEvent e) {
        IGPAccountDetail accountDetail = Registry.get(
                UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        String baseLayer = accountDetail.getBaseLayer();
        saveBaseLayerRequest.setBaseLayer(baseLayer);

        GPClientCommandExecutor.executeCommand(new GPClientCommand<GPMapModuleResponse>() {

                    private static final long serialVersionUID = 97756360922051084L;

                    {
                        super.setCommandRequest(saveBaseLayerRequest);
                    }

                    @Override
                    public void onCommandSuccess(GPMapModuleResponse response) {
                        widget.hide();
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                MapModuleConstants.INSTANCE.SaveBaseLayerAction_statusSaveSuccesfullText(),
                                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                    }

                    @Override
                    public void onCommandFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage(
                                WindowsConstants.INSTANCE.errorSavingTitleText(),
                                WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                MapModuleConstants.INSTANCE.SaveBaseLayerAction_statusErrorSavingText(),
                                SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                        logger.warning(
                                "Error saving the new base layer: " + caught.toString()
                                + " data: " + caught.getMessage());
                    }
                });

    }
}
