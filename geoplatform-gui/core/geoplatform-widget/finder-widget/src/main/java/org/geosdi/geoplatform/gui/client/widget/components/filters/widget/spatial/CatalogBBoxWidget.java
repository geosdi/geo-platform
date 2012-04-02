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
package org.geosdi.geoplatform.gui.client.widget.components.filters.widget.spatial;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.CatalogFinderWidgetResources;
import org.geosdi.geoplatform.gui.client.config.CatalogSpatialFilter;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.responce.AreaInfo.AreaSearchType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@CatalogSpatialFilter
public class CatalogBBoxWidget extends GeoPlatformContentPanel {

    public CatalogBBoxWidget() {
        super(true);
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        setHeaderVisible(false);
        setBodyBorder(false);
    }

    @Override
    public void addComponent() {
        this.addBboxComponent();
        this.addComboComponent();
        this.addCheckBoxComponent();
    }

    private void addBboxComponent() {
        this.topComponents();
        this.centerComponents();
        this.bottomComponents();
    }

    private void topComponents() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setPaddingLeft(100, Unit.PX);

        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label maxLatLabel = new Label("Max Lat");
        maxLatLabel.setStyleAttribute("backgroundColor", "white");
        maxLatLabel.setStyleAttribute("font",
                "normal 10px tahoma, arial, helvetica, sans-serif");

        table.setWidget(1, 1, maxLatLabel);

        TextField<String> maxLatField = new TextField<String>();
        maxLatField.setWidth(50);

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 2, maxLatField);

        super.add(table);
    }

    private void centerComponents() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        this.addLeftComponents(table);
        this.addCenterComponents(table);
        this.addRightComponents(table);

        super.add(table);
    }

    private void addLeftComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label minLonLabel = new Label("Min Lon");
        minLonLabel.setStyleAttribute("backgroundColor", "white");
        minLonLabel.setStyleAttribute("font",
                "normal 10px tahoma, arial, helvetica, sans-serif");

        table.setWidget(1, 1, minLonLabel);

        TextField<String> minLonField = new TextField<String>();
        minLonField.setWidth(50);

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 2, minLonField);
    }

    private void addCenterComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 3,
                HasHorizontalAlignment.ALIGN_CENTER);

        Image compass = CatalogFinderWidgetResources.ICONS.compass().createImage();

        table.setWidget(1, 3, compass);

        table.getCellFormatter().getElement(1, 3).getStyle().setPaddingLeft(
                40, Unit.PX);
    }

    private void addRightComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 4,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label maxLonLabel = new Label("Max Lon");
        maxLonLabel.setStyleAttribute("backgroundColor", "white");
        maxLonLabel.setStyleAttribute("font",
                "normal 10px tahoma, arial, helvetica, sans-serif");

        table.setWidget(1, 4, maxLonLabel);

        table.getCellFormatter().getElement(1, 4).getStyle().setPaddingLeft(
                35, Unit.PX);

        TextField<String> maxLonField = new TextField<String>();
        maxLonField.setWidth(50);

        table.getCellFormatter().setHorizontalAlignment(1, 5,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 5, maxLonField);
    }

    private void bottomComponents() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setPaddingLeft(100, Unit.PX);

        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label minLatLabel = new Label("Min Lan");
        minLatLabel.setStyleAttribute("backgroundColor", "white");
        minLatLabel.setStyleAttribute("font",
                "normal 10px tahoma, arial, helvetica, sans-serif");

        table.setWidget(1, 1, minLatLabel);

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        TextField<String> minLatField = new TextField<String>();
        minLatField.setWidth(50);

        table.setWidget(1, 2, minLatField);

        super.add(table);
    }

    private void addComboComponent() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setPaddingLeft(60, Unit.PX);

        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label typeLabel = new Label("Type:");
        typeLabel.setStyleAttribute("backgroundColor", "white");
        typeLabel.setStyleAttribute("font",
                "normal 12px tahoma, arial, helvetica, sans-serif");

        table.setWidget(1, 1, typeLabel);

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        SimpleComboBox<AreaSearchType> combo = new SimpleComboBox<AreaSearchType>();
        combo.setForceSelection(true);
        combo.setEditable(false);

        combo.setWidth(120);
        combo.setTriggerAction(TriggerAction.ALL);
        combo.add(AreaSearchType.valuesAsList());
        combo.setSimpleValue(AreaSearchType.IS);

        table.setWidget(1, 2, combo);

        super.add(table);
    }

    private void addCheckBoxComponent() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);
        
        table.getCellFormatter().setHorizontalAlignment(2, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        CheckBox activateFilter = new CheckBox();
        activateFilter.setBoxLabel("Activate Spatial Filter");

        table.setWidget(2, 1, activateFilter);
        
        super.add(table);
    }
}
