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
package org.geosdi.geoplatform.gui.client.widget.time;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.map.event.TimeFilterLayerMapEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LayerTimeFilterWidget extends GeoPlatformWindow {

    private final static short WIDGET_HEIGHT = 200;
    private final static short WIDGET_WIDTH = 350;
    private final static String TIME_FILTER_HEADING = "TIME FILTER EDITOR";
    private final TimeFilterLayerMapEvent timeFilterLayerMapEvent = new TimeFilterLayerMapEvent();
    private NumberField filterTextField;
    private ComboBox<DimensionData> dimensionComboBox;
    private ListStore<DimensionData> store;
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private Label dimensionSizeLabel;
    private final Radio fixedDimensionRadio = new Radio();
    private final Radio variableDimensionRadio = new Radio();
    private final LayoutContainer fixedDimensionContainer = new LayoutContainer(new FormLayout());
    private final LayoutContainer variableDimensionContainer = new LayoutContainer(new FormLayout());
    private RadioGroup dimensionRadioGroup;

    public LayerTimeFilterWidget(boolean lazy, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(lazy);
        this.treePanel = treePanel;
    }

    @Override
    public void addComponent() {
        this.dimensionRadioGroup = new RadioGroup();
        this.dimensionRadioGroup.setFieldLabel("Dimension strategy");
        fixedDimensionRadio.setBoxLabel("Fixed Dimension");
        fixedDimensionRadio.setHideLabel(Boolean.TRUE);
        this.dimensionRadioGroup.add(fixedDimensionRadio);
        variableDimensionRadio.setBoxLabel("Variable Dimension");
        variableDimensionRadio.setHideLabel(Boolean.TRUE);
        this.dimensionRadioGroup.add(variableDimensionRadio);
        this.fixedDimensionContainer.setVisible(Boolean.FALSE);
        this.variableDimensionContainer.setVisible(Boolean.FALSE);
        this.fixedDimensionRadio.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                if (fixedDimensionRadio.getValue()) {
                    fixedDimensionContainer.setVisible(Boolean.TRUE);
                    variableDimensionContainer.setVisible(Boolean.FALSE);
                }
            }
        });
        this.variableDimensionRadio.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                if (variableDimensionRadio.getValue()) {
                    variableDimensionContainer.setVisible(Boolean.TRUE);
                    fixedDimensionContainer.setVisible(Boolean.FALSE);
                }
            }
        });

        this.store = new ListStore<DimensionData>();
        this.dimensionComboBox = new ComboBox<DimensionData>();
        this.dimensionComboBox.setFieldLabel("Fixed dimension");
        this.dimensionComboBox.setStore(this.store);
        this.dimensionComboBox.setDisplayField(DimensionData.DIMENSION_KEY);
        dimensionSizeLabel = new Label();
        dimensionSizeLabel.setStyleAttribute("font-size", "12px");
        this.filterTextField = new NumberField();
        this.filterTextField.setFieldLabel("Dimension to Display");
        FormPanel panel = new FormPanel();
        panel.setHeaderVisible(Boolean.FALSE);
        panel.setFrame(Boolean.TRUE);
        panel.setBorders(Boolean.FALSE);
        panel.setHeight(WIDGET_HEIGHT - 67);
        panel.add(this.filterTextField, new FormData("100%"));
        panel.add(this.dimensionRadioGroup);
        this.variableDimensionContainer.add(dimensionSizeLabel, new FormData("100%"));
        this.variableDimensionContainer.add(this.filterTextField);
        this.fixedDimensionContainer.add(this.dimensionComboBox);
        panel.add(this.fixedDimensionContainer, new FormData("100%"));
        panel.add(this.variableDimensionContainer, new FormData("100%"));
        super.add(panel);
        Button apply = new Button("Apply",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
                        IMementoSave mementoSave = LayerModuleInjector.MainInjector.getInstance().getMementoSave();
                        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(layerSelected);
                        if (fixedDimensionRadio.getValue()) {
                            layerSelected.setTimeFilter((String) dimensionComboBox.getValue().get(DimensionData.DIMENSION_KEY));
                            layerSelected.setVariableTimeFilter(null);
                        } else {
                            int value = filterTextField.getValue().intValue();
                            if (value < 0 || value > store.getModels().size()) {
                                GeoPlatformMessage.errorMessage("Time Filter Error", "Incorrect Position time, you must specify a valid position in size range");
                            } else {
                                layerSelected.setTimeFilter("" + filterTextField.getValue().intValue());
                                layerSelected.setVariableTimeFilter((String) store.getModels().get(store.getModels().size() - value - 1).get(DimensionData.DIMENSION_KEY));
                            }
                        }
                        mementoSave.putOriginalPropertiesInCache(memento);
                        timeFilterLayerMapEvent.setLayerBean(layerSelected);
                        GPHandlerManager.fireEvent(timeFilterLayerMapEvent);
                        treePanel.refresh(layerSelected);
                    }
                });

        super.addButton(apply);
        Button close = new Button("Close",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        filterTextField.clear();
                        hide();
                    }
                });

        super.addButton(close);
    }

    private void loadDataToDisplay() {
        GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
        LayerRemote.Util.getInstance().getLayerDimension(layerSelected.getName(),
                new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught.getCause() instanceof GPSessionTimeout) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(null));
                        } else {
                            GeoPlatformMessage.errorMessage("Error Loading Time Filter",
                                    "An error occurred while making the requested connection.\n"
                                    + "Verify network connections and try again."
                                    + "\nIf the problem persists contact your system administrator.");
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Error Loading Time Filter",
                                    SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                            System.out.println("Error Loading Time Filter: " + caught.toString()
                                    + " data: " + caught.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {
                        List<String> dimensionList = Lists.newArrayList(result.split(","));
                        dimensionSizeLabel.setText("Dimension Size: " + dimensionList.size());
                        store.removeAll();
                        for (String dimension : GeoPlatformUtils.safeList(dimensionList)) {
                            store.add(new DimensionData(dimension));
                        }
                    }
                });
    }

    @Override
    public void show() {
        super.show();
        this.loadDataToDisplay();
        GPLayerTreeModel layerElement = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
        String timeFilter = layerElement.getTimeFilter();
        String variableTimeFilter = layerElement.getVariableTimeFilter();
        if (variableTimeFilter != null) {
            try {
                int integerTimeFilter = Integer.parseInt(timeFilter);
                this.filterTextField.setValue(integerTimeFilter);
                this.dimensionRadioGroup.setValue(this.variableDimensionRadio);
            } catch (NumberFormatException nfe) {
            }
        } else if (timeFilter != null) {
            this.dimensionComboBox.setValueField(timeFilter);
            this.dimensionRadioGroup.setValue(this.fixedDimensionRadio);
        }
    }

    @Override
    public void initSize() {
        super.setSize(WIDGET_WIDTH, WIDGET_HEIGHT);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading(TIME_FILTER_HEADING);
        super.setLayout(new FormLayout());
        super.setModal(Boolean.TRUE);
    }
}
