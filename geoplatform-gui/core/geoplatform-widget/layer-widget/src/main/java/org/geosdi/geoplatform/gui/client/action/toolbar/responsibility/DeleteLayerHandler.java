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
package org.geosdi.geoplatform.gui.client.action.toolbar.responsibility;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.command.memento.toolbar.DeleteTreeElementRequest;
import org.geosdi.geoplatform.gui.client.command.memento.toolbar.DeleteTreeElementResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DeleteLayerHandler extends DeleteRequestHandler {

    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
    private final DeleteTreeElementRequest deleteLayerRequest = new DeleteTreeElementRequest();

    public DeleteLayerHandler(TreePanel theTree) {
        super(theTree);
        this.deleteLayerRequest.setElementType(GPTreeCompositeType.LEAF);
    }

    @Override
    public void deleteRequest(final GPBeanTreeModel model) {
        if (model instanceof GPLayerTreeModel) {
            this.processRequest(model);
        } else {
            super.forwardDeleteRequest(model);
        }
    }

    @Override
    public void processRequest(GPBeanTreeModel model) {
        super.delete(model);
    }

    @Override
    public void displayMessage() {
        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.DeleteLayerHandler_statusLayerDeletedText(),
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void executeSave(final MementoSaveRemove memento) {
        //Warning: this conversion remove the associated mementoLayerOriginalProperties also,
        // in this way it is possible to preserv the safety of saving operations.
        memento.convertMementoToWs();

        this.deleteLayerRequest.setMemento(memento);

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<DeleteTreeElementResponse>() {

            private static final long serialVersionUID = -3466943239598533624L;

            {
                super.setCommandRequest(deleteLayerRequest);
            }

            @Override
            public void onCommandSuccess(DeleteTreeElementResponse response) {
                IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                mementoSave.remove(memento);
                LayoutManager.getInstance().getStatusMap().setStatus(
                        LayerModuleConstants.INSTANCE.DeleteLayerHandler_statusSaveDeleteSuccessText(),
                        EnumSearchStatus.STATUS_SEARCH.toString());
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                if (exception.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                } else {
                    LayerHandlerManager.fireEvent(
                            new DisplayLayersProgressBarEvent(
                            false));
                    GeoPlatformMessage.errorMessage(
                            LayerModuleConstants.INSTANCE.
                            errorSaveDeleteOperationTitleText(),
                            LayerModuleConstants.INSTANCE.DeleteLayerHandler_errorSaveDeleteBodyText());
                }
            }

        });
    }

}
