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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.command.memento.check.SaveCheckFolderAndTreeRequest;
import org.geosdi.geoplatform.gui.client.command.memento.check.SaveCheckFolderAndTreeResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

import java.util.logging.Logger;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPCheckListener implements Listener<TreePanelEvent<GPBeanTreeModel>>, ISave<MementoSaveCheck> {

    private static final Logger logger = Logger.getLogger("GPCheckListener");
    //
    private VisitorDisplayHide visitorDisplay;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
    private final SaveCheckFolderAndTreeRequest saveCheckRequest = new SaveCheckFolderAndTreeRequest();

    public GPCheckListener(VisitorDisplayHide visitor) {
        this.visitorDisplay = visitor;
    }

    @Override
    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
        logger.fine("####################Events.CheckChange from: " + be.getItem().getLabel());
        boolean isCacheable = this.visitorDisplay.isCacheableCheck();
        GPBeanTreeModel element = be.getItem();
        element.accept(this.visitorDisplay);
        if (isCacheable && !(element instanceof GPRootTreeNode)) {
            be.getItem().setChecked(!element.isChecked());
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(element);
            be.getItem().setChecked(!element.isChecked());
            mementoSave.putOriginalPropertiesInCache(memento);
        }
    }

    @Override
    public void executeSave(final MementoSaveCheck memento) {
        memento.convertMementoToWs();

        this.saveCheckRequest.setMemento(memento);

        if (memento.getRefBaseElement() instanceof FolderTreeNode) {
            GPClientCommandExecutor.executeCommand(
                    new GPClientCommand<SaveCheckFolderAndTreeResponse>() {

                        private static final long serialVersionUID = 7946923494302192588L;

                        {
                            super.setCommandRequest(saveCheckRequest);
                        }

                        @Override
                        public void onCommandSuccess(
                                SaveCheckFolderAndTreeResponse response) {
                            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                            mementoSave.remove(memento);
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    LayerModuleConstants.INSTANCE.GPCheckListener_statusSaveCheckSuccessText(),
                                    EnumSearchStatus.STATUS_SEARCH.toString());
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
                                        LayerModuleConstants.INSTANCE.GPCheckListener_saveCheckErrorTitleText(),
                                        LayerModuleConstants.INSTANCE.GPCheckListener_saveCheckErrorBodyText());
                            }
                        }

                    });
        }
    }

}