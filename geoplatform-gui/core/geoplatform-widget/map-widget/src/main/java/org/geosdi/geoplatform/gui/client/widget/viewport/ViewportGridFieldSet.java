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
package org.geosdi.geoplatform.gui.client.widget.viewport;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.fieldset.GPFieldSet;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.enumeration.ViewportEnum;
import org.gwtopenmaps.openlayers.client.Map;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ViewportGridFieldSet extends GPFieldSet {

    public final static short VIEWPORT_GRID_WIDTH = 550;
    private ListStore<GPClientViewport> store = new ListStore<GPClientViewport>();
    private Map map;
    private EditorGrid<GPClientViewport> viewportGrid;
    private Button deleteViewportButton;

    public ViewportGridFieldSet(Map map) {
        this.map = map;
        this.subclassCallToInit();
    }

    @Override
    public void addComponents() {
//        store.addStoreListener(new StoreListener<GPClientViewport>() {
//            private boolean isAceptableQuantityValue(Record record) {
//                boolean result = Boolean.TRUE;
////                Double oldQuantity = (Double) record.getChanges().get(ColorMapEntryKeyValue.QUANTITY.toString());
//                Double newQuantity = (Double) record.get(ColorMapEntryKeyValue.QUANTITY.toString());
////                System.out.println("Quantity: " + quantity + " - newQuantity: " + newQuantity);
//                if (newQuantity == null) {
//                    result = Boolean.FALSE;
//                } else {
//                    for (ColorMapEntry entry : store.getModels()) {
//                        //the first check avoids to test the same entry
//                        if (!entry.equals(record.getModel()) && entry.getQuantity() != null
//                                && entry.getQuantity().equals(newQuantity)) {
//                            result = Boolean.FALSE;
//                            break;
//                        }
//                    }
//                }
//                return result;
//            }
//
//            @Override
//            public void storeUpdate(StoreEvent<GPClientViewport> se) {
////                Collections.sort(store.getModels());
//                Record record = se.getRecord();
//                if (record != null && this.isAceptableQuantityValue(record)) {
//                    Collections.sort(rasterSymbolizer.getColorMap().getColorMapEntryList());
//                    super.storeUpdate(se);
//                    viewportGrid.getStore().sort(viewportGrid.getColumnModel().getDataIndex(1), SortDir.ASC);
//                    viewportGrid.getView().refresh(Boolean.TRUE);
//                } else if (record != null) {
//                    se.getModel().setQuantity((Double) record.getChanges().get(ColorMapEntryKeyValue.QUANTITY.toString()));
//                    record.cancelEdit();
//                    GeoPlatformMessage.errorMessage("Duplicated Quantity Value",
//                            "The quantity value must not be duplicated or null!");
//                }
//            }
//
//            @Override
//            public void storeRemove(StoreEvent<GPClientViewport> se) {
//                if (!typeColorMapComboBox.getDisplayField().equals(GPTypeColorMapKeyValue.VALUES.getValue())
//                        && store.getCount() < 2) {
//                    store.add(SLDFactorySingleton.getInstance().generateColorMapEntry(rasterSymbolizer));
//                }
//                super.storeRemove(se);
//            }
//        });

        this.add(this.generateGrid(), new FormData("100%"));
        ButtonBar buttonBar = new ButtonBar();

        Button addEntryButton = new Button("Add Viewport", BasicWidgetResources.ICONS.done(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                //Change panel and fill using the current bbox
            }
        });
        buttonBar.add(addEntryButton);

        this.deleteViewportButton = new Button("Delete Viewport", BasicWidgetResources.ICONS.delete(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<GPClientViewport> viewportList = viewportGrid.getSelectionModel().getSelectedItems();
                if (viewportList != null) {
                    GeoPlatformMessage.confirmMessage("Delete Viewport",
                            "Are you sure you want to delete the selected Element?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
//                                        rasterSymbolizer.getColorMap().getColorMapEntryList().removeAll(viewportList);
//                                        for (ColorMapEntry colorMapEntry : viewportList) {
//                                            store.remove(colorMapEntry);
//                                        }
                                    }
                                }
                            });
                }
            }
        });

        deleteViewportButton.setEnabled(Boolean.FALSE);
        buttonBar.add(deleteViewportButton);
        Button saveButton = new Button("Apply", BasicWidgetResources.ICONS.gear(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                store.commitChanges();
            }
        });

        buttonBar.add(saveButton);
        this.add(buttonBar, new FormData("100%"));
    }

    @Override
    public void setWidgetProperties() {
        this.setHeading("Viewport Widget");
        this.setWidth(VIEWPORT_GRID_WIDTH);
        this.setLayout(new FormLayout());
    }

    private void resetComponent() {
        this.store.removeAll();
    }

    private Grid generateGrid() {
        List<ColumnConfig> configs = Lists.newArrayList();
        final String idNameColumn = "Name";
        ColumnConfig nameColumnConfig = new ColumnConfig(ViewportEnum.NAME.toString(),
                idNameColumn, 80);
//        GridCellRenderer<GPClientViewport> colorCellRenderer = new GridCellRenderer<GPClientViewport>() {
//            @Override
//            public String render(GPClientViewport model, String property, ColumnData config, int rowIndex, int colIndex,
//                    ListStore<GPClientViewport> store, Grid<GPClientViewport> grid) {
//
//                return "<span style='display: block; background-color:" + model.getColor() + "'>" + model.getColor() + "</span>";
//            }
//        };
//        colorColumnConfig.setRenderer(colorCellRenderer);
        configs.add(nameColumnConfig);
        final String idDescriptionColumn = "Description";
        ColumnConfig descriptionColumnConfig = new ColumnConfig(ViewportEnum.DESCRIPTION.toString(),
                idDescriptionColumn, 80);
        descriptionColumnConfig.setEditor(new CellEditor(new TextField<String>()));
//        descriptionColumnConfig.setEditor(new CellEditor(new NumberField()));
        configs.add(descriptionColumnConfig);

        ColumnConfig bboxColumnConfig = new ColumnConfig(ViewportEnum.BBOX.toString(),
                "BBOX", 120);
        bboxColumnConfig.setEditor(new CellEditor(new TextField<String>()));
        configs.add(bboxColumnConfig);

        ColumnConfig zoomLevelColumnConfig = new ColumnConfig(ViewportEnum.ZOOM_LEVEL.toString(),
                "Zoom Level", 80);
        zoomLevelColumnConfig.setNumberFormat(NumberFormat.getDecimalFormat());
        NumberField numberField = new NumberField();
        numberField.setAllowNegative(Boolean.FALSE);
        numberField.setMaxValue(MapLayoutWidget.NUM_ZOOM_LEVEL);
        numberField.setMinValue(0);
        zoomLevelColumnConfig.setEditor(new CellEditor(new NumberField()));
        configs.add(zoomLevelColumnConfig);
        CheckBoxSelectionModel<GPClientViewport> checkBoxSelectionModel = new CheckBoxSelectionModel<GPClientViewport>();
        checkBoxSelectionModel.setSelectionMode(SelectionMode.MULTI);
        checkBoxSelectionModel.addSelectionChangedListener(new SelectionChangedListener<GPClientViewport>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPClientViewport> se) {
                if (se.getSelection().isEmpty()) {
                    deleteViewportButton.disable();
                } else {
                    deleteViewportButton.enable();
                }
            }
        });
        checkBoxSelectionModel.setLocked(Boolean.FALSE);
        configs.add(checkBoxSelectionModel.getColumn());
        this.viewportGrid = new EditorGrid<GPClientViewport>(store, new ColumnModel(configs));
        this.viewportGrid.addPlugin(checkBoxSelectionModel);
        this.viewportGrid.setSelectionModel(checkBoxSelectionModel);
        viewportGrid.setBorders(Boolean.TRUE);
        viewportGrid.setStripeRows(Boolean.TRUE);
        viewportGrid.setBorders(Boolean.TRUE);
        this.viewportGrid.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        viewportGrid.setStyleAttribute("borderTop", "none");
        viewportGrid.setAutoExpandColumn(ViewportEnum.NAME.toString());
        viewportGrid.setAutoExpandMin(120);
        viewportGrid.setSize("250px", "300px");
//        viewportGrid.addListener(Events.CellDoubleClick, new Listener<GridEvent<GPClientViewport>>() {
//
//            @Override
//            public void handleEvent(final GridEvent<GPClientViewport> gridEvent) {
//                if (gridEvent.getColIndex() == 0) {
//                    try {
//                        colorPicker.setHex(gridEvent.getModel().getColor().substring(1));
//                    } catch (Exception ex) {
//                    }
////                    gridEvent.getGrid().getColumnModel().getColumn(0).getEditor().getField().focus();
////                    gridEvent.getGrid().getSelectionModel().getSelectedItem();
//                    colorPicker.setColorChooseListener(new Listener<ColorChooseEvent>() {
//                        @Override
//                        public void handleEvent(ColorChooseEvent colorChooseEvent) {
//                            gridEvent.getGrid().getSelectionModel().getSelectedItem().setColor(
//                                    colorChooseEvent.getChoosedColor());
//                            gridEvent.getGrid().getStore().update(gridEvent.getGrid().getSelectionModel().getSelectedItem());
//
//                        }
//                    });
//                    colorPicker.show();
//                }
//            }
//        });
        return viewportGrid;
    }

    public void setViewportListStore(List<GPClientViewport> viewportList) {
        store.add(viewportList);
    }

    @Override
    public final void subclassCallToInit() {
        super.init();
    }
}
