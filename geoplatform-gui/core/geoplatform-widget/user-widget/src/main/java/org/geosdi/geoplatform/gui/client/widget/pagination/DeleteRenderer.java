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
package org.geosdi.geoplatform.gui.client.widget.pagination;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class DeleteRenderer implements GridCellRenderer<GPUserManageDetail> {

//public class DeleteRenderer implements GridCellRenderer<BeanModel> {
//
    private static final DeleteRenderer instance = new DeleteRenderer();

    public static DeleteRenderer getInstance() {
        return instance;
    }
//
//    private boolean init;
//    

    @Override
    public Object render(final GPUserManageDetail model, String property, ColumnData config, final int rowIndex,
            final int colIndex, ListStore<GPUserManageDetail> store, Grid<GPUserManageDetail> grid) {

//        if (!init) {
//            init = true;
//            grid.addListener(Events.ColumnResize, new Listener<GridEvent<GPUserManageDetail>>() {
//
//                @Override
//                public void handleEvent(GridEvent<GPUserManageDetail> be) {
//                    for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
//                        Widget w = be.getGrid().getView().getWidget(i, be.getColIndex());
//                        if (w != null && w instanceof BoxComponent) {
//                            ((BoxComponent) w).setWidth(be.getWidth() - 10);
//                        }
//                    }
//                }
//            });
//        }

        Button button = new Button((String) model.get(property), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {

                GeoPlatformMessage.confirmMessage("Delete User",
                        "Are you sure you want to delete the User \"" + model.getUsername() + "\" ?",
                        new Listener<MessageBoxEvent>() {

                            @Override
                            public void handleEvent(MessageBoxEvent be) {
                                if (be.getButtonClicked().getText().toLowerCase().contains("s")) {
                                    UserRemoteImpl.Util.getInstance().deleteUser(model.getId(),
                                            new AsyncCallback<Boolean>() {

                                                @Override
                                                public void onFailure(Throwable caught) {
                                                    GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                                                }

                                                @Override
                                                public void onSuccess(Boolean result) {
                                                    // TODO Delete to store...
                                                    Info.display("User deleted", "<ul><li>" + model.getUsername() + "</li></ul>");
                                                }
                                            });

                                }
                            }
                        });
            }
        });
        button.setToolTip("Delete User");
        button.setIcon(BasicWidgetResources.ICONS.cancel());
        button.setAutoWidth(true);

        return button;
    }
//
//    @Override
//    public Object render(BeanModel model, String property, ColumnData config,
//            int rowIndex, int colIndex, ListStore<BeanModel> store, Grid<BeanModel> grid) {
//        return "<image class=\"delete cursor-pointer\" src=\"geoportal/gp-images/error.png\" "
//                + "alt=\"delete\" title=\"Delete this User\" />";
//    }
//    
//}    
}
