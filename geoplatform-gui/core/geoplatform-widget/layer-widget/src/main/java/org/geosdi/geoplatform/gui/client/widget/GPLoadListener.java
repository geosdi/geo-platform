/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.Timer;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPLoadListener extends LoadListener {

    private LayerAsyncTreeWidget layer;
    private final static int WAITING_TIME = 7000;
    private Timer timer;
    private MessageBox messageBox = new MessageBox();
    private boolean isBusy = false;
    private String tmpStatus;
    private Listener<MessageBoxEvent> listener = new Listener<MessageBoxEvent>() {

        @Override
        public void handleEvent(MessageBoxEvent be) {
            if (be.getButtonClicked().getItemId().equalsIgnoreCase(Dialog.NO) || be.isCancelled()) {
                //ToDo: Stop remote loading and retry the operation
            } else if (isBusy) {
                timer.schedule(WAITING_TIME);
            }
        }
    };

    /**
     * @Constructor
     *
     * @param layer
     */
    public GPLoadListener(LayerAsyncTreeWidget layer) {
        this.layer = layer;
    }

    @Override
    public void loaderBeforeLoad(LoadEvent le) {
        this.isBusy = true;
        this.timer = new Timer() {

            @Override
            public void run() {
                messageBox = GeoPlatformMessage.confirmMessage("Network busy",
                        "Do you want to retry the load operation?", listener);
            }
        };

        timer.schedule(WAITING_TIME);
        this.tmpStatus = LayoutManager.get().getStatusMap().getText();
        LayoutManager.get().getStatusMap().setBusy(
                "Loading tree elements: please, wait untill contents fully loads.");
        //GeoPlatformMessage.infoMessage("Loading...", "Please wait untill contents fully loads.");
    }

    public void stopBeforeLoad() {
        this.isBusy = false;
        this.timer.cancel();
        LayoutManager.get().getStatusMap().setStatus(this.tmpStatus, null);
    }

    @Override
    public void loaderLoad(LoadEvent le) {
        this.resetLoaderListener();
        this.layer.getSelectedFolder().setChildren((List) le.getData());
        this.setElementsParent((List) le.getData(),
                this.layer.getSelectedFolder());
        LayoutManager.get().getStatusMap().setStatus(
                "Tree elements loaded successfully.", null);
        //GeoPlatformMessage.infoMessage("Load completed", "Operation completed successfully.");
    }

    private void setElementsParent(List<GPBeanTreeModel> elementList,
            GPBeanTreeModel parent) {
        for (Iterator<GPBeanTreeModel> it = elementList.iterator(); it.hasNext();) {
            it.next().setParent(parent);
        }
    }

    @Override
    public void loaderLoadException(LoadEvent le) {
        this.resetLoaderListener();
        GeoPlatformMessage.errorMessage("Error loading",
                "An error occurred while making the requested connection.\n"
                + "Verify network connections and try again.\nIf the problem persists contact your system administrator.");
        LayoutManager.get().getStatusMap().setStatus(
                "Error loading tree elements.", null);
        System.out.println("Errore avvenuto nel loader del tree: " + le.exception
                + " data: " + le.getData());
    }

    private void resetLoaderListener() {
        this.isBusy = false;
        this.timer.cancel();
        if (this.messageBox != null && this.messageBox.isVisible()) {
            this.messageBox.getDialog().getButtonById(Dialog.NO).fireEvent(
                    Events.Select);
        }
    }
}
