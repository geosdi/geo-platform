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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.configurator.gui.GuiComponentIDs;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.GuiComponentDetail;
import org.geosdi.geoplatform.gui.client.model.GuiComponentDetailKeyValue;
import org.geosdi.geoplatform.gui.client.model.GuiPermission;
import org.geosdi.geoplatform.gui.client.widget.grid.renderer.GPGridCellRenderer;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class ManageRolesWidget extends GeoPlatformWindow {

    private ListStore<GuiComponentDetail> store = new ListStore<GuiComponentDetail>();
    private EditorGrid<GuiComponentDetail> grid;
    private SimpleComboBox<GuiPermission> permissionComboBox;

    public ManageRolesWidget() {
        super(true);
    }

    @Override
    public void initSize() {
        super.setSize(600, 500);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("Manage Roles");
        super.setResizable(false);
        super.setLayout(new FlowLayout(5));
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
    }

    @Override
    public void addComponent() {
        ContentPanel mainPanel = this.createMainPanel();
        mainPanel.setTopComponent(this.createToolbar());

        grid = new EditorGrid<GuiComponentDetail>(store, this.prepareColumnModel());
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setAutoExpandColumn(GuiComponentDetailKeyValue.COMPONENT_ID.toString());
        mainPanel.add(grid);

        this.addButtons();
    }

    private ContentPanel createMainPanel() {
        ContentPanel mainPanel = new ContentPanel();
        mainPanel.setHeading("Role permissions");
        mainPanel.setIcon(BasicWidgetResources.ICONS.role());
        mainPanel.setFrame(true);
        mainPanel.setSize(580, 480);
        mainPanel.setLayout(new FitLayout());

        super.add(mainPanel);

        return mainPanel;
    }

    private ToolBar createToolbar() {
        ToolBar toolbar = new ToolBar();

        // TODO DEL (for test purpose)
        Button add = new Button("Add mock GCs");
        add.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
//                for (String id : GuiComponentIDs.LIST_ALL) { //
                for (String id : new String[]{GuiComponentIDs.MANAGE_USERS,
                            GuiComponentIDs.MANAGE_ROLES}) { //
                    System.out.println("*** ID " + id);
                    MenuBaseAction action = (MenuBaseAction) MenuActionRegistar.get(id); //

                    GuiComponentDetail gc = new GuiComponentDetail();
                    gc.setComponentId(action.getId());
                    gc.setImage(action.getImage());
                    gc.setDescription(action.getTitle()); // TODO Manage description
                    gc.setPermission(GuiPermission.WRITE);

                    grid.stopEditing();

//                    store.add(gc);
                    store.insert(gc, 0);

                    grid.startEditing(store.indexOf(gc), 0);
                }

                ce.getButton().disable();
            }
        });
        toolbar.add(add);

        return toolbar;
    }

    private void addButtons() {
        super.setButtonAlign(Style.HorizontalAlignment.RIGHT);

        Button reset = new Button("Reset", BasicWidgetResources.ICONS.delete(),
                                  new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                store.rejectChanges();
            }
        });
        super.addButton(reset);

        Button save = new Button("Save", BasicWidgetResources.ICONS.done(),
                                 new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                store.commitChanges();
            }
        });
        super.addButton(save);

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
//                button.setToolTip(model.getDescription()); // TODO Manage description
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
//        permissionComboBox.setToolTip("Permission of the role");
//        permissionComboBox.setEditable(false);
//        permissionComboBox.setTypeAhead(true);
        permissionComboBox.setForceSelection(true);
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

        ColumnConfig permissionColumn = new ColumnConfig();
        permissionColumn.setId(GuiComponentDetailKeyValue.PERMISSION.toString());
        permissionColumn.setHeader("Permission");
        permissionColumn.setWidth(150);
        permissionColumn.setEditor(comboEditor);
        configs.add(permissionColumn);

        return new ColumnModel(configs);
    }
}
