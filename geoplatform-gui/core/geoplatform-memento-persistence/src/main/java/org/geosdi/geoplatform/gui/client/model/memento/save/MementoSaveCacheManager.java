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

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPSaveCacheHandler;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.savecache.SaveCacheHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoSaveCacheManager implements GPSaveCacheHandler {

    private static PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    public MementoSaveCacheManager() {
        GPHandlerManager.addHandler(TYPE, this);
    }

    @Override
    public void processCache(final GwtEvent event) {
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        if (mementoSave.isEmpty()) {
            SaveCacheHandlerManager.fireEvent(event);
        } else {
            LayoutManager.getInstance().getStatusMap().setStatus(
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_statusUnsavedOperationsText(),
                    EnumSearchStatus.STATUS_NO_SEARCH.toString());
            LayoutManager.getInstance().getViewport().mask(
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationsText(),
                    SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            GeoPlatformMessage.confirmMessage(
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationsText(),
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationMessageText(),
                    new Listener<MessageBoxEvent>() {
                @Override
                public void handleEvent(MessageBoxEvent be) {
                    if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                        peekCacheEvent.setEventAfterAllSaveOperations(event);
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    } else {
                        SaveCacheHandlerManager.fireEvent(event);
                    }
                    LayoutManager.getInstance().getViewport().unmask();
                }
            });
        }
    }
}
