/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveCheck;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPCheckListener implements Listener<TreePanelEvent<GPBeanTreeModel>>, ISave<MementoSaveCheck> {

    private VisitorDisplayHide visitorDisplay;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    public GPCheckListener(VisitorDisplayHide visitor) {
        this.visitorDisplay = visitor;
    }

    @Override
    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
        //System.out.println("Events.CheckChange from: " + be.getItem().getLabel());
        boolean isCacheable = this.visitorDisplay.isCacheableCheck();
        this.visitorDisplay.setIsParentsElementModified(false);
        be.getItem().accept(this.visitorDisplay);
        if (isCacheable && !(be.getItem() instanceof GPRootTreeNode)) {
            IMemento<ISave> precedingMemento = GPLayerSaveCache.getInstance().peekLast();
            if (precedingMemento != null && precedingMemento instanceof MementoSaveCheck
                    && ((MementoSaveCheck) precedingMemento).getRefBaseElement().equals(be.getItem())
                    && !((MementoSaveCheck) precedingMemento).isParentsElementModified()) {
                GPLayerSaveCache.getInstance().remove(precedingMemento);
            } else {
                MementoSaveCheck mementoCheck = new MementoSaveCheck(this);
                mementoCheck.setRefBaseElement(be.getItem());
                mementoCheck.setChecked(be.getItem().isChecked());
                mementoCheck.setIsParentsElementModified(this.visitorDisplay.isParentsElementModified());
                GPLayerSaveCache.getInstance().add(mementoCheck);
            }
        }
    }

    @Override
    public void executeSave(final MementoSaveCheck memento) {
        memento.convertMementoToWs();
        if (memento.getRefBaseElement() instanceof FolderTreeNode) {
            LayerRemote.Util.getInstance().saveCheckStatusFolderAndTreeModifications(memento, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    if (caught.getCause() instanceof GPSessionTimeout) {
                        GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                    } else {
                        LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                        GeoPlatformMessage.errorMessage("Save Check Operation on Folder Error",
                                "Problems on saving the new tree state after checking folder");
                    }
                }

                @Override
                public void onSuccess(Boolean result) {
                    GPLayerSaveCache.getInstance().remove(memento);
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Save Check Folder Operation completed successfully.",
                            EnumSearchStatus.STATUS_SEARCH.toString());
                    LayerHandlerManager.fireEvent(peekCacheEvent);
                }
            });
        } else if (memento.getRefBaseElement() instanceof GPLayerBean) {
            LayerRemote.Util.getInstance().saveCheckStatusLayerAndTreeModifications(memento, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    if (caught.getCause() instanceof GPSessionTimeout) {
                        GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                    } else {
                        LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                        GeoPlatformMessage.errorMessage("Save Check Operation on Layer Error",
                                "Problems on saving the new tree state after checking layer");
                    }
                }

                @Override
                public void onSuccess(Boolean result) {
                    GPLayerSaveCache.getInstance().remove(memento);
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Save Check Layer Operation completed successfully.",
                            EnumSearchStatus.STATUS_SEARCH.toString());
                    LayerHandlerManager.fireEvent(peekCacheEvent);
                }
            });
        }
    }
}