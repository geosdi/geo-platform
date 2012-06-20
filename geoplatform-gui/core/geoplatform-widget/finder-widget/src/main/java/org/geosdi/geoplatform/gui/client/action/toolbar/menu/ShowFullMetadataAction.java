/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action.toolbar.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.google.gwt.user.client.Window;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.client.model.FullRecord;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class ShowFullMetadataAction extends MenuAction { // TODO extends MenuBaseAction if must be added an icon

    private CheckBoxSelectionModel<FullRecord> selectionModel;

    public ShowFullMetadataAction(CheckBoxSelectionModel<FullRecord> selectionModel) {
        super("ShowFullMetadata");
        this.selectionModel = selectionModel;
    }

    @Override
    public void componentSelected(MenuEvent e) {
        FullRecord record = selectionModel.getSelectedItem();
        System.out.println("\n\n\n*** Record for GetRecordById request ***\n" + record); // TODO logger

        /**
         * TODO
         * Think a manner for view Full Metadata also for a record that can't be selectable.
         * 
         * Example: search "web map" on geoSDI catalog and try to read 
         * full metadata with context menu
         */
        if (record != null) {
            String url = this.createRequestURL(record);

            Window.open(url, "Full Metadata", "");
        }
    }

    private String createRequestURL(FullRecord record) {
        StringBuilder str = new StringBuilder();

        str.append(record.getCatalogURL()).append("?");
        str.append("Request").append("=").append("GetRecordById").append("&");
        str.append("Service").append("=").append("CSW").append("&");
        str.append("Version").append("=").append("2.0.2").append("&");
        str.append("ElementSetName").append("=").append("full").append("&");
        str.append("OutputSchema").append("=").append("http://www.isotc211.org/2005/gmd").append("&");
        str.append("Id").append("=").append(record.getIdentifier());

        return str.toString();
    }
}
