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
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
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
import org.geosdi.geoplatform.gui.client.puregwt.decorator.event.TreeChangeLabelEvent;
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
import org.geosdi.geoplatform.gui.puregwt.layers.decorator.event.GPTreeLabelEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LayerTimeFilterWidget extends GeoPlatformWindow {

    public final static String LAYER_TIME_DELIMITER = " - [";
    private final static short WIDGET_HEIGHT = 230;
    private final static short WIDGET_WIDTH = 360;
    private final static String TIME_FILTER_HEADING = "TIME FILTER EDITOR";
    private final TimeFilterLayerMapEvent timeFilterLayerMapEvent = new TimeFilterLayerMapEvent();
    private final GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();
    private NumberField startFilterTextField;
    private NumberField endFilterTextField;
    private ComboBox<DimensionData> startDimensionComboBox;
    private ComboBox<DimensionData> endDimensionComboBox;
    private ListStore<DimensionData> startStore;
    private ListStore<DimensionData> endStore;
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

        this.startStore = new ListStore<DimensionData>();
        this.startDimensionComboBox = new ComboBox<DimensionData>();
        this.startDimensionComboBox.setFieldLabel("Start Fixed dimension");
        this.startDimensionComboBox.setStore(this.startStore);
        this.startDimensionComboBox.setDisplayField(DimensionData.DIMENSION_KEY);
        this.startDimensionComboBox.addSelectionChangedListener(new SelectionChangedListener<DimensionData>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<DimensionData> se) {
                if (se.getSelectedItem() != null) {
                    endStore.removeAll();
                    int indexStart = startStore.getModels().indexOf(se.getSelectedItem()) + 1;
//                    System.out.println("Sublist start: " + indexStart);
//                    System.out.println("Sublist end: " + (startStore.getModels().size() - 1));
                    if (indexStart < startStore.getModels().size()) {
                        endDimensionComboBox.enable();
                        endStore.add(startStore.getModels().subList(
                                startStore.indexOf(se.getSelectedItem()) + 1,
                                startStore.getModels().size()));
                    } else {
                        endDimensionComboBox.disable();
                    }
                }
            }
        });
        this.endStore = new ListStore<DimensionData>();
        this.endDimensionComboBox = new ComboBox<DimensionData>();
        this.endDimensionComboBox.setFieldLabel("End Fixed dimension");
        this.endDimensionComboBox.setStore(this.endStore);
        this.endDimensionComboBox.setDisplayField(DimensionData.DIMENSION_KEY);
        this.endDimensionComboBox.disable();
        dimensionSizeLabel = new Label();
        dimensionSizeLabel.setStyleAttribute("font-size", "12px");
        this.startFilterTextField = new NumberField();
        this.startFilterTextField.setFieldLabel("Start Dimension to Display");
        this.endFilterTextField = new NumberField();
        this.endFilterTextField.setFieldLabel("End Dimension to Display");
        FormPanel panel = new FormPanel();
        panel.setHeaderVisible(Boolean.FALSE);
        panel.setFrame(Boolean.TRUE);
        panel.setBorders(Boolean.FALSE);
        panel.setHeight(WIDGET_HEIGHT - 67);
        panel.add(this.dimensionRadioGroup);
        this.variableDimensionContainer.add(dimensionSizeLabel, new FormData("100%"));
        this.variableDimensionContainer.add(this.startFilterTextField);
        this.variableDimensionContainer.add(this.endFilterTextField);
        this.fixedDimensionContainer.add(this.startDimensionComboBox);
        this.fixedDimensionContainer.add(this.endDimensionComboBox);
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
                        String layerName;
                        if (layerSelected.getAlias() != null
                                && layerSelected.getAlias().indexOf(LAYER_TIME_DELIMITER) != -1) {
                            layerName = layerSelected.getAlias().substring(0,
                                    layerSelected.getAlias().indexOf(LAYER_TIME_DELIMITER));
                        } else {
                            layerName = layerSelected.getLabel();
                        }
                        if (fixedDimensionRadio.getValue()) {
                            String timeFilter = (String) startDimensionComboBox.getValue().get(DimensionData.DIMENSION_KEY);
                            if (endDimensionComboBox.getValue() != null) {
                                timeFilter += "/" + (String) endDimensionComboBox.getValue().get(DimensionData.DIMENSION_KEY);
                            }
                            layerSelected.setTimeFilter(timeFilter);
                            layerSelected.setVariableTimeFilter(null);
                            layerSelected.setAlias(layerName + LAYER_TIME_DELIMITER + layerSelected.getTimeFilter() + "]");
                            WidgetPropertiesHandlerManager.fireEvent(labelEvent);
                        } else {
                            int startFilter = startFilterTextField.getValue().intValue();
                            Number endFilter = endFilterTextField.getValue();
//                            System.out.println("End filter: " + endFilter);
                            if (startFilter < 0 || startFilter > startStore.getModels().size() - 1
                                    || (endFilter != null && (endFilter.intValue() <= startFilter
                                    || endFilter.intValue() > startStore.getModels().size() - 1))) {
                                GeoPlatformMessage.errorMessage("Time Filter Error", "Incorrect Position time, you must specify a valid position in size range");
                                return;
                            } else {
                                String timeFilter = "" + startFilterTextField.getValue().intValue();
                                String variableTimeFilter = (String) startStore.getModels().get(startStore.getModels().size() - startFilter - 1).get(DimensionData.DIMENSION_KEY);
                                if (endFilterTextField.getValue() != null) {
                                    timeFilter += "/" + endFilterTextField.getValue().intValue();
                                    variableTimeFilter += "/" + (String) startStore.getModels().get(startStore.getModels().size() - endFilter.intValue() - 1).get(DimensionData.DIMENSION_KEY);
                                }
                                layerSelected.setTimeFilter(timeFilter);
                                layerSelected.setVariableTimeFilter(variableTimeFilter);
                                layerSelected.setAlias(layerName + LAYER_TIME_DELIMITER + layerSelected.getVariableTimeFilter() + "]");
                                WidgetPropertiesHandlerManager.fireEvent(labelEvent);
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
                        startFilterTextField.clear();
                        endFilterTextField.clear();
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
                        startStore.removeAll();
                        endStore.removeAll();
                        for (String dimension : GeoPlatformUtils.safeList(dimensionList)) {
                            startStore.add(new DimensionData(dimension));
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
                String[] variableTimeFilterSplitted = timeFilter.split("/");
                int integerTimeFilter = Integer.parseInt(variableTimeFilterSplitted[0]);
                this.startFilterTextField.setValue(integerTimeFilter);
                this.dimensionRadioGroup.setValue(this.variableDimensionRadio);
                if (variableTimeFilterSplitted.length > 1) {
                    integerTimeFilter = Integer.parseInt(variableTimeFilterSplitted[1]);
                    this.endFilterTextField.setValue(integerTimeFilter);
                }
            } catch (NumberFormatException nfe) {
            }
        } else if (timeFilter != null) {
            String[] timeFilterSplitted = timeFilter.split("/");
            this.startDimensionComboBox.setValue(new DimensionData(timeFilterSplitted[0]));
            if (timeFilterSplitted.length > 1) {
                this.endDimensionComboBox.enable();
                this.endDimensionComboBox.setValue(new DimensionData(timeFilterSplitted[1]));
            }
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
