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
package org.geosdi.geoplatform.gui.client.widget.components.filters.accordionwidget;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.config.CatalogFilter;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TimeInfo;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
@CatalogFilter
public class TimeAccordionWidget extends GeoPlatformContentPanel {

    private TimeInfo timeInfo;
    private Radio anytimeRadio;
    private MultiField<DateField> multiDates;
    private DateField startDate;
    private DateField endDate;

    @Inject
    public TimeAccordionWidget(CatalogFinderBean theCatalogFinder) {
        super(true);
        this.timeInfo = theCatalogFinder.getTimeInfo();
    }

    @Override
    public void addComponent() {
        super.setLayout(new FlowLayout(5));

        this.addRadioGroup();
        this.addCalendars();
    }

    private void addRadioGroup() {
        anytimeRadio = new Radio();
        anytimeRadio.setBoxLabel("Anytime");
        anytimeRadio.setValue(true); // Enabled by default
        anytimeRadio.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                timeInfo.setActive(false);
                timeInfo.setStartDate(null);
                timeInfo.setEndDate(null);

                startDate.setMaxValue(null);
                endDate.setMinValue(null);
                multiDates.reset();
                multiDates.disable();
            }
        });

        final Radio temporalExtentRadio = new Radio();
        temporalExtentRadio.setBoxLabel("Temporal Extent");
        temporalExtentRadio.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                timeInfo.setActive(true);

                multiDates.enable();
            }
        });

        final RadioGroup radioGroup = new RadioGroup();
        radioGroup.setOrientation(Style.Orientation.VERTICAL);
        radioGroup.add(anytimeRadio);
        radioGroup.add(temporalExtentRadio);

        super.add(radioGroup);
    }

    private void addCalendars() {
        DateTimeFormat dtFormat = DateTimeFormat.getFormat("dd-MM-yyyy");

        startDate = new DateField();
        startDate.setToolTip("Start date");
        startDate.getPropertyEditor().setFormat(dtFormat);
        startDate.setWidth(100);
//        startDate.setFormatValue(true);
        startDate.setAutoValidate(true);
        startDate.setAllowBlank(false);
        startDate.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                if (startDate.isValid()) {
                    Date selectedDate = startDate.getValue();
                    System.out.println("CHANGE start: " + selectedDate);

                    setStartDate(selectedDate);
                }
            }
        });

        DatePicker startDatePicker = startDate.getDatePicker();
        startDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {

            @Override
            public void handleEvent(DatePickerEvent dpe) {
                Date selectedDate = dpe.getDate();
                System.out.println("SELECT start: " + selectedDate);
                setStartDate(selectedDate);
            }
        });

        endDate = new DateField();
        endDate.setToolTip("End date");
        endDate.getPropertyEditor().setFormat(dtFormat);
        endDate.setWidth(100);
//        endDate.setFormatValue(true);
        endDate.setAutoValidate(true);
        endDate.setAllowBlank(false);
        endDate.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                if (endDate.isValid()) {
                    setEndDate(endDate.getValue());
                }
            }
        });

        DatePicker endDatePicker = endDate.getDatePicker();
        endDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {

            @Override
            public void handleEvent(DatePickerEvent dpe) {
                setEndDate(dpe.getDate());
            }
        });

        multiDates = new MultiField<DateField>();
        multiDates.setOrientation(Style.Orientation.HORIZONTAL);
        multiDates.add(new LabelField("From:&nbsp;"));
        multiDates.add(startDate);
        multiDates.add(new LabelField("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To:&nbsp;"));
        multiDates.add(endDate);
        multiDates.disable(); // Disabled by default
//        multiDates.addListener(Events.Valid, new Listener<FieldEvent>() {
//
//            @Override
//            public void handleEvent(FieldEvent be) {
//                System.out.println("+++++++ valid");
//            }
//        });
//        multiDates.addListener(Events.Invalid, new Listener<FieldEvent>() {
//
//            @Override
//            public void handleEvent(FieldEvent be) {
//                System.out.println("+++++++ NO valid");
//            }
//        });

        super.add(multiDates, new FlowData(5));
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.setAnimCollapse(false);
        super.setHeading("Filter by Time");
        super.setBodyBorder(false);
    }

    @Override
    public void reset() {
        anytimeRadio.setValue(true);
    }

    private void setStartDate(Date selectedDate) {
        endDate.setMinValue(selectedDate);
        timeInfo.setStartDate(selectedDate);
    }

    private void setEndDate(Date selectedDate) {
        startDate.setMaxValue(selectedDate);
        timeInfo.setEndDate(selectedDate);
    }
}
