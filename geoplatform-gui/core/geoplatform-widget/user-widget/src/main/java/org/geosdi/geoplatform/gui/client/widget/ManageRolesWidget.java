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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.GuiComponentDetail;
import org.geosdi.geoplatform.gui.client.model.GuiComponentDetailKeyValue;
import org.geosdi.geoplatform.gui.client.model.GuiPermission;
import org.geosdi.geoplatform.gui.client.widget.grid.renderer.GPGridCellRenderer;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformAction;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class ManageRolesWidget extends GeoPlatformWindow {

    private ContentPanel mainPanel;
    private Menu rolesMenu;
    private Button resetButton;
    private Button saveButton;
    private List<String> roles;
    private String role;
    //
    private ListStore<GuiComponentDetail> store;
    private EditorGrid<GuiComponentDetail> grid;
    private SimpleComboBox<GuiPermission> permissionComboBox;
    //
//    private AbstractImagePrototype defaultImage = IconHelper.createPath("geoportal/gp-images/error.png");
    private AbstractImagePrototype defaultImage = IconHelper.createPath(""); // Empty icon

    public ManageRolesWidget() {
        super(true);
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public void initSize() {
        super.setSize(600, 500);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("GeoPlatform Roles Management");
        super.setResizable(false);
        super.setLayout(new FlowLayout());
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
    }

    @Override
    public void addComponent() {
        this.createMainPanel();
        this.createToolbar();
        this.createStore();
        this.createEditorGrid();
        this.createButtons();
    }

    private void createMainPanel() {
        mainPanel = new ContentPanel();
        mainPanel.setHeading("Role permissions");
        mainPanel.setIcon(BasicWidgetResources.ICONS.role());
        mainPanel.setFrame(true);
        mainPanel.setSize(585, 430);
        mainPanel.setLayout(new FitLayout());

        super.add(mainPanel);
    }

    private void createToolbar() {
        ToolBar toolbar = new ToolBar();

        SplitButton rolesButton = new SplitButton("Roles");
        rolesButton.setArrowAlign(ButtonArrowAlign.BOTTOM);
        rolesButton.setMenu(this.createRolesMenu());
        toolbar.add(rolesButton);

        toolbar.add(new SeparatorToolItem());

        Button newRoleButton = new Button("New Role", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // TODO
            }
        });
        newRoleButton.disable(); //
        toolbar.add(newRoleButton);

        mainPanel.setTopComponent(toolbar);
    }

    private void createStore() {
        store = new ListStore<GuiComponentDetail>();
        store.addStoreListener(new StoreListener<GuiComponentDetail>() {

            @Override
            public void storeClear(StoreEvent<GuiComponentDetail> se) {
                disableButtons();
            }

            @Override
            public void storeUpdate(StoreEvent<GuiComponentDetail> se) {
                resetButton.enable();
                saveButton.enable();
            }
        });
    }

    private void createEditorGrid() {
        grid = new EditorGrid<GuiComponentDetail>(store, this.prepareColumnModel());
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setAutoExpandColumn(GuiComponentDetailKeyValue.COMPONENT_ID.toString());
        grid.mask("Select a role...");

        mainPanel.add(grid);
    }

    private void createButtons() {
        super.setButtonAlign(Style.HorizontalAlignment.RIGHT);

        resetButton = new Button("Reset", BasicWidgetResources.ICONS.delete(),
                                 new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                grid.stopEditing(true);
                store.rejectChanges();
                disableButtons();
            }
        });
        super.addButton(resetButton);

        saveButton = new Button("Save", BasicWidgetResources.ICONS.done(),
                                new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                save();
            }
        });
        super.addButton(saveButton);

        this.disableButtons();

        Button close = new Button("Close", BasicWidgetResources.ICONS.cancel(),
                                  new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        super.addButton(close);
    }

    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig iconColumn = new ColumnConfig();
        iconColumn.setId("icon");
        iconColumn.setHeader("Icon");
        iconColumn.setWidth(35);
        iconColumn.setFixed(true);
        iconColumn.setResizable(false);
        iconColumn.setSortable(false);
        iconColumn.setRenderer(new GPGridCellRenderer<GuiComponentDetail>() {

            @Override
            public Object render(GuiComponentDetail model, String property,
                                 ColumnData config, int rowIndex, int colIndex,
                                 ListStore<GuiComponentDetail> store,
                                 Grid<GuiComponentDetail> grid) {
                Button button = new Button();
                button.setIcon(model.getImage());
                button.setAutoWidth(true);
                return button;
            }
        });
        configs.add(iconColumn);

        ColumnConfig idColumn = new ColumnConfig();
        idColumn.setId(GuiComponentDetailKeyValue.COMPONENT_ID.toString());
        idColumn.setHeader("Component ID");
        idColumn.setFixed(true);
        configs.add(idColumn);

        permissionComboBox = new SimpleComboBox<GuiPermission>();
        permissionComboBox.setEditable(false);
        permissionComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        permissionComboBox.add(GuiPermission.getAllPermissions());
        CellEditor comboEditor = new CellEditor(permissionComboBox) {

            @Override
            public Object preProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return permissionComboBox.findModel((GuiPermission) value);
            }

            @Override
            public Object postProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return ((SimpleComboValue<GuiPermission>) value).getValue();
            }
        };
        GridCellRenderer<GuiComponentDetail> rendererPermission = new GridCellRenderer<GuiComponentDetail>() {

            @Override
            public Object render(GuiComponentDetail model, String property,
                                 ColumnData config, int rowIndex, int colIndex,
                                 ListStore<GuiComponentDetail> store,
                                 Grid<GuiComponentDetail> grid) {
                GuiPermission permission = model.getPermission();
                String style;
                if (permission == GuiPermission.WRITE) {
                    style = "green";
                } else if (permission == GuiPermission.READ) {
                    style = "gold";
                } else {
                    style = "red";
                }
                return "<span style='color:" + style + "'>" + permission + "</span>";
            }
        };
        ColumnConfig permissionColumn = new ColumnConfig();
        permissionColumn.setId(GuiComponentDetailKeyValue.PERMISSION.toString());
        permissionColumn.setHeader("Permission");
        permissionColumn.setWidth(150);
        permissionColumn.setFixed(true);
        permissionColumn.setEditor(comboEditor);
        permissionColumn.setRenderer(rendererPermission);
        configs.add(permissionColumn);

        return new ColumnModel(configs);
    }

    private Menu createRolesMenu() {
        this.rolesMenu = new Menu();
        for (String role : this.roles) {
            this.addRoleMenuItem(role);
        }
        return this.rolesMenu;
    }

    private void addRoleMenuItem(String roleItem) {
        MenuItem item = new MenuItem(roleItem);
        item.setId(roleItem);
        item.addSelectionListener(new SelectionListener() {

            @Override
            public void componentSelected(ComponentEvent ce) {
                role = ce.getTarget().getId();
                if (grid.isMasked()) {
                    grid.unmask();
                }
                mask("Retrieve permission of \"" + role + "\" role");
                mainPanel.setHeading("Permissions of \"" + role + "\" role");

                UserRemoteImpl.Util.getInstance().getRolePermission(role,
                                                                    new AsyncCallback<HashMap<String, Boolean>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                    }

                    @Override
                    public void onSuccess(HashMap<String, Boolean> result) {
                        grid.stopEditing(true);
                        store.removeAll();
                        unmask();

                        for (Entry<String, Boolean> entry : result.entrySet()) {
                            GuiComponentDetail gc = new GuiComponentDetail();
                            gc.setComponentId(entry.getKey());
                            gc.setPermission(GuiPermission.fromBoolean(entry.getValue()));

//                            gc.setDescription(action.get....()); // TODO Manage description

                            // TODO Manage icon
                            GeoPlatformAction action = MenuActionRegistar.get(entry.getKey());
                            if (action != null) {
                                if (action instanceof MenuBaseAction) {
                                    AbstractImagePrototype image = ((MenuBaseAction) action).getImage();
                                    if (image != null) {
                                        gc.setImage(image);
                                    }
                                }
//                            }else{
//                                action = ToolbarActionRegistar.get(entry.getKey(), null)
                            }
                            if (gc.getImage() == null) {
                                gc.setImage(defaultImage);
                            }

                            store.add(gc);
                        }

                        store.sort(GuiComponentDetailKeyValue.COMPONENT_ID.toString(),
                                   Style.SortDir.ASC);
                    }
                });
            }
        });
        this.rolesMenu.add(item);
    }

    private void save() {
        grid.stopEditing(true);
        List<Record> modifiedElements = store.getModifiedRecords();
//        if (!modifiedElements.isEmpty()) {
        HashMap<String, Boolean> permissionMap = new HashMap<String, Boolean>(modifiedElements.size());

        for (Record record : modifiedElements) {
            String componentId = record.get(GuiComponentDetailKeyValue.COMPONENT_ID.toString()).toString();
            GuiPermission permission = (GuiPermission) record.get(GuiComponentDetailKeyValue.PERMISSION.toString());
            System.out.println("### MOD " + componentId + "to " + permission);
            if (permission == GuiPermission.WRITE) {
                permissionMap.put(componentId, true);
            } else if (permission == GuiPermission.READ) {
                permissionMap.put(componentId, false);
            } else {
                permissionMap.put(componentId, null);
            }
        }

        mask("Saving Permission for \"" + role + "\" role");
        this.updatePermission(permissionMap);
//        }
    }

    private void updatePermission(HashMap<String, Boolean> permissionMap) {
        UserRemoteImpl.Util.getInstance().updateRolePermission(role, permissionMap,
                                                               new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                GeoPlatformMessage.errorMessage("Error", caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                store.commitChanges();
                disableButtons();
                unmask();
            }
        });
    }

    @Override
    public void hide() {
        if (store.getModifiedRecords().isEmpty()) {
            super.hide();
        } else {
            GeoPlatformMessage.alertMessage("Warning",
                                            "There are unsaved permission, save or reset before exit");
        }
    }

    private void disableButtons() {
        resetButton.disable();
        saveButton.disable();
    }
}
