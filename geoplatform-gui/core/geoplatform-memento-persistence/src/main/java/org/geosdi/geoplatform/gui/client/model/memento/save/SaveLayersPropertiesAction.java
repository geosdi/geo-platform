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
package org.geosdi.geoplatform.gui.client.model.memento.save;

import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.command.memento.basic.SaveLayerPropertiesRequest;
import org.geosdi.geoplatform.gui.client.command.memento.basic.SaveLayerPropertiesResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SaveLayersPropertiesAction implements ISave<MementoLayerOriginalProperties> {

    private static PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    @Override
    public void executeSave(final MementoLayerOriginalProperties memento) {
        //Warning: the conversion update the memento fields on the last refBean properties
        memento.convertMementoToWs();
        final SaveLayerPropertiesRequest saveLayerPropertiesRequest = GWT.<SaveLayerPropertiesRequest>create(SaveLayerPropertiesRequest.class);
        saveLayerPropertiesRequest.setMementoLayerOriginalProperties(memento);
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<SaveLayerPropertiesResponse>() {
            private static final long serialVersionUID = 7052499099859652678L;

            {
                super.setCommandRequest(saveLayerPropertiesRequest);
            }

            @Override
            public void onCommandSuccess(SaveLayerPropertiesResponse response) {
                IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                mementoSave.remove(memento);
                LayoutManager.getInstance().getStatusMap().setStatus(MementoPersistenceConstants.INSTANCE.SaveLayersPropertiesAction_statusSaveLayerSuccessText(),
                        EnumSearchStatus.STATUS_SEARCH.toString());
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                if (exception.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                } else {
                    LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                    GeoPlatformMessage.errorMessage(MementoPersistenceConstants.INSTANCE.
                                    SaveLayersPropertiesAction_errorSaveLayerPropertiesTitleText(),
                            MementoPersistenceConstants.INSTANCE.
                                    SaveLayersPropertiesAction_errorSaveLayerPropertiesBodyText());
                }
            }
        });
//        GPLayerSaveCache.getInstance().remove(memento);
//        LayerHandlerManager.fireEvent(peekCacheEvent);
    }
}
