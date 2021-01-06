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
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;

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
    private final MapWidget mapWidget;
    private GPEPSGContentPanel epsgPanel;

    public GotoXYWidget(Boolean lazy, MapWidget theMapWidget,
            String markerLayerName) {
        super(lazy);
        this.mapWidget = theMapWidget;
        this.geocoderMarker = new GeocodingVectorMarker(this.mapWidget.getMap(),
                markerLayerName);
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

        this.geocoderMarker.addMarker(center, true);
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
        this.getFormPanel().add(fieldSet);
        saveStatus = new SaveStatus();
        saveStatus.setAutoWidth(true);
        getFormPanel().setButtonAlign(HorizontalAlignment.LEFT);
        getFormPanel().getButtonBar().add(saveStatus);
        getFormPanel().getButtonBar().add(new FillToolItem());
        this.find = new Button(ButtonsConstants.INSTANCE.findText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (getFormPanel().isValid()) {
                            execute();
                        }
                    }

                });
        getFormPanel().addButton(this.find);
        this.close = new Button(ButtonsConstants.INSTANCE.closeText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        GotoXYWidget.this.hide(GotoXYWidget.this.close);
                    }

                });
        getFormPanel().addButton(this.close);
    }

    @Override
    public void initSize() {
        setSize(400, 275);
    }

    @Override
    public void initSizeFormPanel() {
        getFormPanel().setHeaderVisible(false);
        getFormPanel().setSize(380, 250);
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
        this.unregisterMarker();
    }

    @Override
    public final void show() {
        this.registerMark();
        super.show();
    }

    final void registerMark() {
        this.mapWidget.getMap().addLayer(geocoderMarker.getMarkerLayer());
        this.geocoderMarker.addControl();
    }

    final void unregisterMarker() {
        this.geocoderMarker.removeControl();
        this.geocoderMarker.removeMarker();
        this.mapWidget.getMap().removeLayer(this.geocoderMarker.getMarkerLayer());
    }

}
