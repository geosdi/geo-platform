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
package org.geosdi.geoplatform.gui.client.widget.viewport;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.geosdi.geoplatform.gui.action.button.GPSecureButton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.action.viewport.SaveViewportAction;
import org.geosdi.geoplatform.gui.client.i18n.MapModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.fieldset.GPFieldSet;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.enumeration.ViewportEnum;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.gwtopenmaps.openlayers.client.Map;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ViewportGridFieldSet extends GPFieldSet {

    private ListStore<GPClientViewport> store = new ListStore<GPClientViewport>();
    private Map map;
    private EditorGrid<GPClientViewport> viewportGrid;
    private Button deleteViewportButton;
    private Button gotoViewportButton;
    private GPSecureButton saveButton;
    private Button setDefaultViewportButton;
    private StoreFilterField<GPClientViewport> viewportFilter;

    public ViewportGridFieldSet(Map map) {
        this.map = map;
        this.subclassCallToInit();
    }

    @Override
    public void addComponents() {
        store.addStoreListener(new StoreListener<GPClientViewport>() {
            @Override
            public void storeUpdate(StoreEvent<GPClientViewport> se) {
                super.storeUpdate(se);
                ViewportGridFieldSet.this.saveButton.enable();
            }
        });
        this.add(createViewportFilter());
        this.add(this.generateGrid(), new FormData("100%"));
        ButtonBar buttonBar = new ButtonBar();

        Button addEntryButton = new Button(MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_buttonAddViewportText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()), 
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        ViewportGridFieldSet.this.store.add(ViewportUtility.generateViewportFromMap(map));
                        viewportGrid.startEditing(store.getCount() - 1, 1);
                        ViewportGridFieldSet.this.saveButton.enable();
                    }
                });
        buttonBar.add(addEntryButton);

        this.deleteViewportButton = new Button(MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_buttonDeleteViewportText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.delete()), 
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        final List<GPClientViewport> viewportList = viewportGrid.getSelectionModel().getSelectedItems();
                        if (viewportList != null) {
                            GeoPlatformMessage.confirmMessage(MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_confirmDeleteViewportTitleText(),
                                    MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_confirmDeleteViewportBodyText(),
                                    new Listener<MessageBoxEvent>() {
                                        @Override
                                        public void handleEvent(MessageBoxEvent be) {
                                            if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                                                for (GPClientViewport viewport : viewportList) {
                                                    store.remove(viewport);
                                                    ViewportGridFieldSet.this.saveButton.enable();
                                                }
                                            }
                                        }
                                    });
                        }
                    }
                });
        deleteViewportButton.setEnabled(Boolean.FALSE);
        buttonBar.add(deleteViewportButton);

        this.gotoViewportButton = new Button(MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_buttonGoToViewportText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.gotoXY()), 
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        final List<GPClientViewport> viewportList = viewportGrid.getSelectionModel().getSelectedItems();
                        if (viewportList != null && viewportList.size() == 1) {
                            ViewportUtility.gotoViewportLocation(map, viewportList.get(0));
                        } else {
                            GeoPlatformMessage.alertMessage(MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_singleSelectionAlertTitleText(),
                                    MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_singleSelectionAlertBodyText());
                        }
                    }
                });
        gotoViewportButton.setEnabled(Boolean.FALSE);
        buttonBar.add(gotoViewportButton);

        this.setDefaultViewportButton = new Button(ButtonsConstants.INSTANCE.setDefautlText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.select()),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        final List<GPClientViewport> viewportList = viewportGrid.getSelectionModel().getSelectedItems();
                        if (viewportList != null && viewportList.size() == 1) {
                            GPClientViewport selectedViewport = viewportList.get(0);
                            for (GPClientViewport viewport : ViewportGridFieldSet.this.store.getModels()) {
                                if (viewport.isDefault()) {
                                    viewport.set(ViewportEnum.IS_DEFAULT.toString(), Boolean.FALSE);
                                    store.update(viewport);
                                } else if (selectedViewport.equals(viewport)) {
                                    viewport.set(ViewportEnum.IS_DEFAULT.toString(), Boolean.TRUE);
                                    store.update(viewport);
                                }
                            }
                        } else {
                            GeoPlatformMessage.alertMessage(MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_setDefaultViewportAlertTitleText(),
                                    MapModuleConstants.INSTANCE.
                                    ViewportGridFieldSet_singleSelectionAlertBodyText());
                        }
                    }
                });
        setDefaultViewportButton.setEnabled(Boolean.FALSE);
        buttonBar.add(setDefaultViewportButton);
        SaveViewportAction saveViewportAction = new SaveViewportAction(GPTrustedLevel.LOW, this.store);
        this.saveButton = new GPSecureButton(ButtonsConstants.INSTANCE.saveText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.save()), 
                saveViewportAction);
        buttonBar.add(this.saveButton);
        this.add(buttonBar, new FormData("100%"));
    }

    private Widget createViewportFilter() {
        this.viewportFilter = new StoreFilterField<GPClientViewport>() {
            @Override
            protected boolean doSelect(Store<GPClientViewport> store, GPClientViewport parent,
                    GPClientViewport record, String property, String filter) {
                String viewportName = record.getName().toString().toLowerCase();
                String viewportDescription = record.getDescription().toString().toLowerCase();
                if (viewportName.contains(filter.toLowerCase()) || viewportDescription.contains(filter.toLowerCase())) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        };
        viewportFilter.setEmptyText(MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_viewportFilterEmptyTextText());
        viewportFilter.bind(this.store);
        viewportFilter.setFieldLabel(MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_viewportFilterLabelText());
        return this.viewportFilter;
    }

    @Override
    public void setWidgetProperties() {
        this.setWidth(ViewportWidget.VIEWPORT_WIDGET_WIDTH - 30);
        this.setHeight(ViewportWidget.VIEWPORT_WIDGET_HEIGHT - 40);
        this.setLayout(new FormLayout());
    }

    private void resetComponent() {
        this.store.removeAll();
        this.viewportFilter.clear();
        this.saveButton.disable();
    }

    private Grid generateGrid() {
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayList();
        final String idIsDefaultColumn = MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_defaultViewportText();
        CheckColumnConfig isDefaultColumn = new CheckColumnConfig(ViewportEnum.IS_DEFAULT.toString(),
                idIsDefaultColumn, 50);
        configs.add(isDefaultColumn);
        final String idNameColumn = MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_nameColumnText();
        ColumnConfig nameColumnConfig = new ColumnConfig(ViewportEnum.NAME.toString(),
                idNameColumn, 80);
        nameColumnConfig.setEditor(new CellEditor(new GPSecureStringTextField()));
        configs.add(nameColumnConfig);
        final String idDescriptionColumn = MapModuleConstants.INSTANCE.
                ViewportGridFieldSet_descriptionColumnText();
        ColumnConfig descriptionColumnConfig = new ColumnConfig(ViewportEnum.DESCRIPTION.toString(),
                idDescriptionColumn, 80);
        descriptionColumnConfig.setEditor(new CellEditor(new GPSecureStringTextField()));
        configs.add(descriptionColumnConfig);
        ColumnConfig minXColumnConfig = new ColumnConfig(ViewportEnum.LOWER_LEFT_X.toString(),
                MapModuleConstants.INSTANCE.ViewportGridFieldSet_minXColumnText(), 70);
        minXColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberFieldBBOX = new NumberField();
        numberFieldBBOX.setMaxValue(180);
        numberFieldBBOX.setMinValue(-180);
        minXColumnConfig.setEditor(new CellEditor(numberFieldBBOX));
        configs.add(minXColumnConfig);
        ColumnConfig minYColumnConfig = new ColumnConfig(ViewportEnum.LOWER_LEFT_Y.toString(),
                MapModuleConstants.INSTANCE.ViewportGridFieldSet_minYColumnText(), 70);
        minYColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberFieldBBOX2 = new NumberField();
        numberFieldBBOX2.setMaxValue(90);
        numberFieldBBOX2.setMinValue(90);
        minYColumnConfig.setEditor(new CellEditor(numberFieldBBOX2));
        configs.add(minYColumnConfig);
        ColumnConfig maxXColumnConfig = new ColumnConfig(ViewportEnum.UPPER_RIGHT_X.toString(),
                MapModuleConstants.INSTANCE.ViewportGridFieldSet_maxXColumnText(), 70);
        maxXColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberFieldBBOX3 = new NumberField();
        numberFieldBBOX3.setMaxValue(180);
        numberFieldBBOX3.setMinValue(-180);
        maxXColumnConfig.setEditor(new CellEditor(numberFieldBBOX3));
        configs.add(maxXColumnConfig);
        ColumnConfig maxYColumnConfig = new ColumnConfig(ViewportEnum.UPPER_RIGHT_Y.toString(),
                MapModuleConstants.INSTANCE.ViewportGridFieldSet_maxYColumnText(), 70);
        maxYColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberFieldBBOX4 = new NumberField();
        numberFieldBBOX4.setMaxValue(90);
        numberFieldBBOX4.setMinValue(-90);
        maxYColumnConfig.setEditor(new CellEditor(numberFieldBBOX4));
        configs.add(maxYColumnConfig);

        ColumnConfig zoomLevelColumnConfig = new ColumnConfig(ViewportEnum.ZOOM_LEVEL.toString(),
                MapModuleConstants.INSTANCE.ViewportGridFieldSet_zoomLevelColumnText(), 70);
        zoomLevelColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberFieldZoom = new NumberField();
        numberFieldZoom.setAllowNegative(Boolean.FALSE);
        numberFieldZoom.setMaxValue(MapLayoutWidget.NUM_ZOOM_LEVEL);
        numberFieldZoom.setMinValue(0);
        zoomLevelColumnConfig.setEditor(new CellEditor(numberFieldZoom));
        configs.add(zoomLevelColumnConfig);
        CheckBoxSelectionModel<GPClientViewport> checkBoxSelectionModel = new CheckBoxSelectionModel<GPClientViewport>();
        checkBoxSelectionModel.setSelectionMode(SelectionMode.MULTI);
        checkBoxSelectionModel.addSelectionChangedListener(new SelectionChangedListener<GPClientViewport>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPClientViewport> se) {
                if (se.getSelection().isEmpty()) {
                    deleteViewportButton.disable();
                    gotoViewportButton.disable();
                    setDefaultViewportButton.disable();
                } else {
                    deleteViewportButton.enable();
                    gotoViewportButton.enable();
                    setDefaultViewportButton.enable();
                }
            }
        });
        checkBoxSelectionModel.setLocked(Boolean.FALSE);
        configs.add(checkBoxSelectionModel.getColumn());
        this.viewportGrid = new EditorGrid<GPClientViewport>(store, new ColumnModel(configs));
        this.viewportGrid.addPlugin(checkBoxSelectionModel);
        this.viewportGrid.setSelectionModel(checkBoxSelectionModel);
        this.viewportGrid.setBorders(Boolean.TRUE);
        this.viewportGrid.setStripeRows(Boolean.TRUE);
        this.viewportGrid.setBorders(Boolean.TRUE);
        this.viewportGrid.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        viewportGrid.setStyleAttribute("borderTop", "none");
        viewportGrid.setAutoExpandColumn(ViewportEnum.DESCRIPTION.toString());
        viewportGrid.setAutoExpandMin(120);
        viewportGrid.setSize("250px", "300px");
        return viewportGrid;
    }

    public void setViewportListStore(List<GPClientViewport> viewportList) {
        this.resetComponent();
        store.add(viewportList);
    }

    public void addViewportElement(final GPClientViewport viewport) {
        store.add(viewport);
        this.saveButton.enable();
        this.viewportGrid.startEditing(store.getCount() - 1, 1);
    }

    @Override
    public final void subclassCallToInit() {
        super.init();
    }
}
