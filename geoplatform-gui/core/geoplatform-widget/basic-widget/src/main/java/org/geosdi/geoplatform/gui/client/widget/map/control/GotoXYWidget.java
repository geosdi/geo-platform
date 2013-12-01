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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.map.GPEPSGContentPanel;
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
public class GotoXYWidget extends GeoPlatformFormWidget<PointRepresentation>
        implements IGeoPlatformForm {

    private NumberField xNumberField;
    private NumberField yNumberField;
    private Button find;
    private Button close;
    private final GeocodingVectorMarker geocoderMarker;
    private final GeoPlatformMap mapWidget;
    private GPEPSGContentPanel epsgPanel;

    public GotoXYWidget(Boolean lazy, GeoPlatformMap mapWidget) {
        super(lazy);
        this.mapWidget = mapWidget;
        this.geocoderMarker = new GeocodingVectorMarker(this.mapWidget.getMap());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.form.IGeoPlatformForm#execute()
     */
    @Override
    public void execute() {
        String epsgCode = this.epsgPanel.getEpsgTextField().getValue();
        LonLat center = new LonLat(xNumberField.getValue().doubleValue(),
                yNumberField.getValue().doubleValue());
        if (!epsgCode.equals(this.mapWidget.getMap().getProjection())) {
            center.transform(epsgCode, this.mapWidget.getMap().getProjection());
        }

        this.geocoderMarker.addMarker(center);
    }

    @Override
    public void addComponentToForm() {
        super.setHeadingHtml(
                BasicWidgetConstants.INSTANCE.GotoXYWidget_headingText());
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(
                BasicWidgetConstants.INSTANCE.GotoXYWidget_fieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(80);
        fieldSet.setLayout(layout);
        this.epsgPanel = new GPEPSGContentPanel(Boolean.FALSE);
        fieldSet.add(this.epsgPanel);
        this.xNumberField = new NumberField();
        this.xNumberField.setFieldLabel(BasicWidgetConstants.INSTANCE.
                GotoXYWidget_xFieldLabelText());
        fieldSet.add(this.xNumberField);
        this.yNumberField = new NumberField();
        this.yNumberField.setFieldLabel(BasicWidgetConstants.INSTANCE.
                GotoXYWidget_yFieldLabelText());
        fieldSet.add(this.yNumberField);
        this.formPanel.add(fieldSet);
        saveStatus = new SaveStatus();
        saveStatus.setAutoWidth(true);
        formPanel.setButtonAlign(HorizontalAlignment.LEFT);
        formPanel.getButtonBar().add(saveStatus);
        formPanel.getButtonBar().add(new FillToolItem());
        this.find = new Button(ButtonsConstants.INSTANCE.findText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (formPanel.isValid()) {
                            execute();
                        }
                    }

                });
        formPanel.addButton(this.find);
        this.close = new Button(ButtonsConstants.INSTANCE.closeText(),
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
        this.xNumberField.reset();
        this.yNumberField.reset();
        this.saveStatus.clearStatus("");
        this.epsgPanel.reset();
    }

    @Override
    public void show() {
        this.registerMark();
        super.show();
    }

    @Override
    public void hide(Button buttonPressed) {
        super.hide(buttonPressed);
        if (buttonPressed != null) {
            this.unregisterMarker();
        }
    }

    private void registerMark() {
        this.mapWidget.getMap().addLayer(geocoderMarker.getMarkerLayer());
        this.geocoderMarker.addControl();
    }

    private void unregisterMarker() {
        this.geocoderMarker.removeControl();
        this.geocoderMarker.removeMarker();
        this.mapWidget.getMap().removeLayer(this.geocoderMarker.getMarkerLayer());
    }

}
