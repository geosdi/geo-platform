/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action.viewport;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.service.MapRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformSecureAction;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SaveViewportAction extends GeoPlatformSecureAction<ButtonEvent> {

    private ListStore<GPClientViewport> store;

    public SaveViewportAction(GPTrustedLevel trustedLevel, ListStore<GPClientViewport> store) {
        super(trustedLevel);
        this.store = store;
    }

    private boolean isStoreModelCorrect() {
        boolean check = true;
        BBoxClientInfo bbox;
        for (GPClientViewport viewport : store.getModels()) {
            bbox = viewport.getBbox();
            System.out.println("BBOXXX: " + bbox.getLowerLeftX());
            if (bbox == null || bbox.getLowerLeftX() == 0.0d || bbox.getLowerLeftY() == 0.0d
                    || bbox.getUpperRightX() == 0.0d || bbox.getUpperRightY() == 0.0d
                    || viewport.getName() == null || viewport.getName().isEmpty()
                    || viewport.getZoomLevel() < 0) {
                check = false;
                break;
            }
        }
        return check;
    }

    @Override
    public void componentSelected(ButtonEvent ce) {
        if (isStoreModelCorrect()) {
            MapRemote.Util.getInstance().replaceViewportList(store.getModels(), new AsyncCallback<Object>() {
                @Override
                public void onFailure(Throwable caught) {
                    GeoPlatformMessage.errorMessage("Error saving",
                            "An error occurred while making the requested connection.\n"
                            + "Verify network connections and try again."
                            + "\nIf the problem persists contact your system administrator.");
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Error saving the viewport list.",
                            SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    System.out.println("Error saving the viewport list: " + caught.toString()
                            + " data: " + caught.getMessage());
                }

                @Override
                public void onSuccess(Object result) {
                    store.commitChanges();
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Succesfully saved the viewport list.",
                            SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                }
            });
        } else {
            GeoPlatformMessage.errorMessage("Viewport Incorrect",
                    "Before to save it is necessary to correct or fill "
                    + "the: Name, zoom or BBOX values");
        }
    }
}
