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
package org.geosdi.geoplatform.gui.client.command.memento.dnd.chain;

import org.geosdi.geoplatform.gui.client.command.memento.dnd.SaveDragAndDropResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SaveFolderHandler extends SaveTreeElementHandler {

    public SaveFolderHandler() {
        super.setSuccessor(new SaveLayerHandler());
        request.setElementType(GPTreeCompositeType.COMPOSITE);
    }

    @Override
    public void saveTreeElement(MementoSaveDragDrop memento) {
        if (memento.getRefBaseElement() instanceof AbstractFolderTreeNode) {
            doSave(memento);
        } else {
            super.forwardSaveTreeElement(memento);
        }
    }

    @Override
    protected void doSave(final MementoSaveDragDrop memento) {
        memento.convertMementoToWs();
        request.setMemento(memento);

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<SaveDragAndDropResponse>() {

            private static final long serialVersionUID = 4252849345878558685L;

            {
                super.setCommandRequest(request);
            }

            @Override
            public void onCommandSuccess(SaveDragAndDropResponse response) {
                IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                mementoSave.remove(memento);
                LayoutManager.getInstance().getStatusMap().setStatus(
                        MementoPersistenceConstants.INSTANCE.GPDNDListener_statusSaveFolderDDSuccessText(),
                        SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                if (exception.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(
                            peekCacheEvent));
                } else {
                    LayerHandlerManager.fireEvent(
                            new DisplayLayersProgressBarEvent(false));
                    GeoPlatformMessage.errorMessage(
                            MementoPersistenceConstants.INSTANCE.GPDNDListener_saveFolderDDErrorTitleText(),
                            MementoPersistenceConstants.INSTANCE.GPDNDListener_saveFolderDDErrorBodyText());
                }
            }

        });
    }

}
