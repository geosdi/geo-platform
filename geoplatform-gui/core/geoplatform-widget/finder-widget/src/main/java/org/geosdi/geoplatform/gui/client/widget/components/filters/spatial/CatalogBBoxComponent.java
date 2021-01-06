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
package org.geosdi.geoplatform.gui.client.widget.components.filters.spatial;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.CatalogFinderWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.puregwt.event.CatalogBBoxChangeEvent;
import org.geosdi.geoplatform.gui.client.puregwt.handler.CatalogBBoxHandler;
import org.geosdi.geoplatform.gui.client.widget.components.GPCatalogFinderComponent;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogBBoxComponent implements GPCatalogFinderComponent,
        CatalogBBoxHandler {

    private AreaInfo areaInfo;
    private GPSecureStringTextField maxLatField;
    private GPSecureStringTextField minLonField;
    private GPSecureStringTextField maxLonField;
    private GPSecureStringTextField minLatField;

    public CatalogBBoxComponent(GPEventBus bus,
            AreaInfo theAreaInfo) {
        this.areaInfo = theAreaInfo;

        bus.addHandler(CatalogBBoxHandler.TYPE, this);
    }

    public FlexTable getTopComponent() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setMarginLeft(120, Unit.PX);

        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label maxLatLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogBBoxComponent_maxLatLabelText());
        maxLatLabel.setStyleName("catalogBBOX-Label");

        table.setWidget(1, 1, maxLatLabel);

        maxLatField = new GPSecureStringTextField();
        maxLatField.setWidth(55);
        maxLatField.setReadOnly(true);

        maxLatField.setFireChangeEventOnSetValue(true);
        maxLatField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent fe) {
                        BBox bBox = areaInfo.getBBox();
                        double value = (fe.getValue() != null)
                                ? Double.valueOf(fe.getValue().toString()).doubleValue()
                                : 0;

                        bBox.setMaxY(value);
                    }
                });

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 2, maxLatField);

        return table;
    }

    public FlexTable getBottomComponents() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setMarginLeft(120, Unit.PX);

        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label minLatLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogBBoxComponent_minLatLabelText());
        minLatLabel.setStyleName("catalogBBOX-Label");

        table.setWidget(1, 1, minLatLabel);

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        minLatField = new GPSecureStringTextField();
        minLatField.setWidth(55);
        minLatField.setReadOnly(true);

        minLatField.setFireChangeEventOnSetValue(true);
        minLatField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent fe) {
                        BBox bBox = areaInfo.getBBox();
                        double value = (fe.getValue() != null)
                                ? Double.valueOf(fe.getValue().toString()).doubleValue()
                                : 0;

                        bBox.setMinY(value);
                    }
                });

        table.setWidget(1, 2, minLatField);

        return table;
    }

    public FlexTable getCenterComponents() {
        FlexTable table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        this.addLeftComponents(table);
        this.addCenterComponents(table);
        this.addRightComponents(table);

        return table;
    }

    private void addLeftComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label minLonLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogBBoxComponent_minLonLabelText());
        minLonLabel.setStyleName("catalogBBOX-Label");

        table.setWidget(1, 1, minLonLabel);

        minLonField = new GPSecureStringTextField();
        minLonField.setWidth(55);
        minLonField.setReadOnly(true);

        minLonField.setFireChangeEventOnSetValue(true);
        minLonField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent fe) {
                        BBox bBox = areaInfo.getBBox();
                        double value = (fe.getValue() != null)
                                ? Double.valueOf(fe.getValue().toString()).doubleValue()
                                : 0;

                        bBox.setMinX(value);
                    }
                });

        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 2, minLonField);
    }

    private void addCenterComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 3,
                HasHorizontalAlignment.ALIGN_CENTER);

        Image compass = new Image(CatalogFinderWidgetResources.ICONS.compass());

        table.setWidget(1, 3, compass);

        table.getCellFormatter().getElement(1, 3).getStyle().setPaddingLeft(
                40, Unit.PX);
    }

    private void addRightComponents(FlexTable table) {
        table.getCellFormatter().setHorizontalAlignment(1, 4,
                HasHorizontalAlignment.ALIGN_CENTER);

        Label maxLonLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogBBoxComponent_maxLonLabelText());
        maxLonLabel.setStyleName("catalogBBOX-Label");

        table.setWidget(1, 4, maxLonLabel);

        table.getCellFormatter().getElement(1, 4).getStyle().setPaddingLeft(
                40, Unit.PX);

        maxLonField = new GPSecureStringTextField();
        maxLonField.setWidth(55);
        maxLonField.setReadOnly(true);

        maxLonField.setFireChangeEventOnSetValue(true);
        maxLonField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent fe) {
                        BBox bBox = areaInfo.getBBox();
                        double value = (fe.getValue() != null)
                                ? Double.valueOf(fe.getValue().toString()).doubleValue()
                                : 0;

                        bBox.setMaxX(value);
                    }
                });

        table.getCellFormatter().setHorizontalAlignment(1, 5,
                HasHorizontalAlignment.ALIGN_CENTER);

        table.setWidget(1, 5, maxLonField);
    }

    @Override
    public void reset() {
        this.maxLatField.reset();
        this.minLonField.reset();
        this.maxLonField.reset();
        this.minLatField.reset();
    }

    @Override
    public void onBBoxChange(CatalogBBoxChangeEvent event) {
        this.minLatField.setValue((event.getLowerLeftY() != null)
                ? event.getLowerLeftY().toString() : null);
        this.minLonField.setValue((event.getLowerLeftX() != null)
                ? event.getLowerLeftX().toString() : null);
        this.maxLatField.setValue((event.getUpperRightY() != null)
                ? event.getUpperRightY().toString() : null);
        this.maxLonField.setValue((event.getUpperRightX() != null)
                ? event.getUpperRightX().toString() : null);
    }
}
