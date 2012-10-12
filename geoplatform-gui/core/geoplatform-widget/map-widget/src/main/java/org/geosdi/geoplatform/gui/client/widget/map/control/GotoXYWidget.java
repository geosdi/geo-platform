/**
 * ------------------------------------------------------------------ --
 * Copyright <Creation_date>-<Last modification date> SELEX Sistemi -- Integrati
 * S.p.A. all rights reserved. -- This software is the property of SELEX Sistemi
 * Integrati S.p.A. -- and can not be reproduced, used to prepare Derivative
 * Works of, -- publicly displayed, publicly performed, sublicensed, and it --
 * cannot be distributed as the Work itself and such Derivative -- Works in
 * Source or Object form except under a license agreement -- granted by SELEX
 * Sistemi Integrati S.p.A.
 * ------------------------------------------------------------------
 *
 * @file AddAOEWidget.java
 *
 * @author giuseppe
 * @date 21/ott/2010
 * @brief
 * @date Date ; Name ; Description of modification
 */
package org.geosdi.geoplatform.gui.client.widget.map.control;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import org.geosdi.geoplatform.gui.client.util.EPSGTemplate;
import org.geosdi.geoplatform.gui.client.util.EPSGUtility;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.form.GeoPlatformFormWidget;
import org.geosdi.geoplatform.gui.client.widget.form.IGeoPlatformForm;
import org.geosdi.geoplatform.gui.client.widget.map.marker.advanced.GeocodingVectorMarker;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.gwtopenmaps.openlayers.client.LonLat;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GotoXYWidget extends GeoPlatformFormWidget<PointRepresentation> implements
        IGeoPlatformForm {

    private TextField<String> epsgTextField;
    private NumberField xNumberField;
    private NumberField yNumberField;
    private Button find;
    private Button close;
    private GeocodingVectorMarker geocoderMarker = new GeocodingVectorMarker();
    private GeoPlatformMap mapWidget;
    private ListStore<EPSGTemplate> storeEPSG;
    private ComboBox<EPSGTemplate> comboEPSG;
    private SelectionChangedListener<EPSGTemplate> comboEpsgListener;

    public GotoXYWidget(Boolean lazy, GeoPlatformMap mapWidget) {
        super(lazy);
        this.mapWidget = mapWidget;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.form.IGeoPlatformForm#execute()
     */
    // @Override
    @Override
    public void execute() {
        String epsgCode = "";
        epsgCode = epsgTextField.getValue();

        LonLat center = new LonLat(xNumberField.getValue().doubleValue(), yNumberField.getValue().doubleValue());

        if (!epsgCode.equals(this.mapWidget.getMap().getProjection())) {
            center.transform(epsgCode, this.mapWidget.getMap().getProjection());
        }
//        this.geocoderMarker.setProvider(provider);
        this.geocoderMarker.addMarker(center, this.mapWidget.getMap());
//        GPToolbarActionHandlerManager.fireEvent(new UpdateModelAndButtonEvent(bean));
//        org.gwtopenmaps.openlayers.client.MapWidget. //
        //        inject();
        //        Dispatcher.forwardEvent(GeoPortalEvents.SAVE_AOE_LINE, this.entity);

    }

    @Override
    public void addComponentToForm() {
        super.setHeading("Find Position");
        fieldSet = new FieldSet();
        fieldSet.setHeading("Goto X - Y");
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(80);
        fieldSet.setLayout(layout);


        this.storeEPSG = new ListStore<EPSGTemplate>();
        this.storeEPSG.add(EPSGUtility.getCommonEPSG());

        this.comboEPSG = new ComboBox<EPSGTemplate>();
        this.comboEPSG.setFieldLabel("Common EPSG");
        this.comboEPSG.setEmptyText("Choose EPSG...");
        this.comboEPSG.setDisplayField(EPSGTemplate.EPSGEnum.EPSG_DESCRIPTION.getValue());
        this.comboEPSG.setEditable(false);
        this.comboEPSG.setAllowBlank(true);
        this.comboEPSG.setForceSelection(true);
        this.comboEPSG.setTypeAhead(true);
        this.comboEPSG.setTriggerAction(ComboBox.TriggerAction.ALL);

        this.comboEPSG.setStore(this.storeEPSG);

        this.comboEpsgListener = new SelectionChangedListener<EPSGTemplate>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<EPSGTemplate> se) {
                epsgTextField.setValue(se.getSelectedItem().getEpsg());
            }
        };

        fieldSet.add(this.comboEPSG);


        epsgTextField = new TextField<String>();
        epsgTextField.setAllowBlank(false);
        epsgTextField.setFieldLabel("EPSG");

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                comboEPSG.reset();
            }
        };

        epsgTextField.addKeyListener(keyListener);
        fieldSet.add(epsgTextField);

        this.xNumberField = new NumberField();
        this.xNumberField.setFieldLabel("X");
        fieldSet.add(this.xNumberField);

        this.yNumberField = new NumberField();
        this.yNumberField.setFieldLabel("Y");
        fieldSet.add(this.yNumberField);

        this.formPanel.add(fieldSet);

        saveStatus = new SaveStatus();
        saveStatus.setAutoWidth(true);

        formPanel.setButtonAlign(HorizontalAlignment.LEFT);

        formPanel.getButtonBar().add(saveStatus);

        formPanel.getButtonBar().add(new FillToolItem());

        this.find = new Button("Find", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (formPanel.isValid()) {
                    execute();
                }
            }
        });

        formPanel.addButton(this.find);

        this.close = new Button("Close",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        GotoXYWidget.this.hide(GotoXYWidget.this.close);
                    }
                });

        formPanel.addButton(this.close);
    }

    @Override
    public void initSize() {
        setSize(400, 275);
    }

    @Override
    public void initSizeFormPanel() {
        formPanel.setHeaderVisible(false);
        formPanel.setSize(380, 250);
    }

    /**
     * Reset All Form Values and Erase AOE from Map if it is necessary
     *
     */
    @Override
    public void reset() {
        this.epsgTextField.reset();
        this.xNumberField.reset();
        this.yNumberField.reset();
        this.saveStatus.clearStatus("");
        this.comboEPSG.removeSelectionListener(this.comboEpsgListener);
        this.comboEPSG.reset();
    }

    @Override
    public void show() {
        super.init();
        this.registerMark();
        this.comboEPSG.addSelectionChangedListener(this.comboEpsgListener);
        super.show();
    }

    @Override
    public void hide(Button buttonPressed) {
        super.hide(buttonPressed);
        if (buttonPressed != null) {
            System.out.println("Unregistering");
            this.unregisterMarker();
        }
    }

    private void registerMark() {
        this.mapWidget.getMap().addLayer(geocoderMarker.getMarkerLayer());
        this.geocoderMarker.addControl(this.mapWidget.getMap());
    }

    private void unregisterMarker() {
        this.geocoderMarker.removeControl(this.mapWidget.getMap());
        this.geocoderMarker.removeMarker();
        this.mapWidget.getMap().removeLayer(this.geocoderMarker.getMarkerLayer());
    }
}
