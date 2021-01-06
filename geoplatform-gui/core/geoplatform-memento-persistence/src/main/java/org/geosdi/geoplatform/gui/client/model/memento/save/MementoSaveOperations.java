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
import org.geosdi.geoplatform.gui.client.command.memento.basic.SaveAddedLayersAndTreeModificationsRequest;
import org.geosdi.geoplatform.gui.client.command.memento.basic.SaveAddedLayersAndTreeModificationsResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
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

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoSaveOperations {

    private static PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    public static void mementoSaveAddedLayer(final MementoSaveAddedLayers memento, final String messageOk, final String messageFail) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();
        final SaveAddedLayersAndTreeModificationsRequest saveAddedLayersAndTreeModificationsRequest = GWT.<SaveAddedLayersAndTreeModificationsRequest>create(SaveAddedLayersAndTreeModificationsRequest.class);
        saveAddedLayersAndTreeModificationsRequest.setMementoSaveAddedLayers(memento);
        ClientCommandDispatcher.getInstance()
                .execute(new GPClientCommand<SaveAddedLayersAndTreeModificationsResponse>() {
                    private static final long serialVersionUID = 2964764575887864168L;

                    {
                        super.setCommandRequest(saveAddedLayersAndTreeModificationsRequest);
                    }

                    @Override
                    public void onCommandSuccess(SaveAddedLayersAndTreeModificationsResponse response) {
                        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                        mementoSave.remove(memento);
                        LayoutManager.getInstance().getStatusMap()
                                .setStatus(messageOk, EnumSearchStatus.STATUS_SEARCH.toString());
                        List<AbstractMementoLayer> listMementoLayers = memento.getAddedLayers();
                        for (int i = 0; i < listMementoLayers.size(); i++) {
                            listMementoLayers.get(i).getRefBaseElement().setId(response.getResult().get(i));
                        }
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        if (exception.getCause() instanceof GPSessionTimeout) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                        } else {
                            LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                            //                            setStatus(EnumSaveStatus.STATUS_SAVE_ERROR.getValue(),
                            //                                    EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR.getValue());
                            GeoPlatformMessage.errorMessage(MementoPersistenceConstants.INSTANCE
                                    .MementoSaveOperations_errorSavingfLayersTitleText(), messageFail);
                        }
                    }
                });
    }
}