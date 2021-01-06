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
package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.puregwt.reset.GPResetComponentHandler;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class TimePeriodDateMultifield extends MultiField implements GPResetComponentHandler {

    protected DateField dateField;
    private final static DateTimeFormat FORMATTER_DATE = DateTimeFormat.getFormat("dd/MM/yyyy");
    private final static DateTimeFormat FORMATTER_TIME = DateTimeFormat.getFormat("HH:mm");
    protected Date date;
    private DateTimeFormat dtFormat;
    protected Date limitDate;
    protected SpinnerField hourField;
    protected SpinnerField minuteField;

    public TimePeriodDateMultifield() {
        super();
        this.addComponents();
        WidgetPropertiesHandlerManager.addHandler(GPResetComponentHandler.TYPE, this);
    }

    protected void addComponents() {
        super.setSpacing(20);
        this.dtFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
        this.dateField = new DateField();
        this.dateField.setToolTip(dateTooltip());
        this.dateField.getPropertyEditor().setFormat(dtFormat);
        this.dateField.setWidth(90);
        this.dateField.setEditable(false);
        this.dateField.setAllowBlank(Boolean.FALSE);
        DatePicker datePicker = this.dateField.getDatePicker();
        datePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {
            @Override
            public void handleEvent(DatePickerEvent dpe) {
                date = dpe.getDate();
                hourField.clear();
                hourField.enable();
                minuteField.setEnabled(Boolean.TRUE);
                Date dateWithZeroTime = FORMATTER_DATE.parse(FORMATTER_DATE.format(limitDate));
                afterDateSelected(dateWithZeroTime);
                refreshDate();
            }
        });

        this.hourField = new SpinnerField();
        this.hourField.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                minuteField.clear();
                minuteField.enable();
                Date time = FORMATTER_TIME.parse(FORMATTER_TIME.format(limitDate));
                Date dateWithZeroTime = FORMATTER_DATE.parse(FORMATTER_DATE.format(limitDate));
                afterHourSelected(dateWithZeroTime, time);
                refreshDate();
            }
        });

        this.hourField.setToolTip(hourTooltip());
        this.hourField.setWidth(40);
        this.hourField.disable();
        this.hourField.setAllowBlank(Boolean.FALSE);

        this.minuteField = new SpinnerField();
        this.minuteField.setToolTip(minuteTooltip());
        this.minuteField.disable();
        this.minuteField.setWidth(40);
        this.minuteField.setAllowBlank(Boolean.FALSE);

        this.minuteField.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                refreshDate();
            }
        });

        super.setFieldLabel(fieldLabel());
        super.setOrientation(Style.Orientation.HORIZONTAL);
        super.add(this.dateField);
        super.add(this.hourField);
        super.add(this.minuteField);
        super.addStyleName("dateMultifield");
        super.setValidator(addValidator());
        setMaxMinValue();
    }

    /**
     *
     */
    protected abstract void refreshDate();

    /**
     * @param dateWithZeroTime
     */
    protected abstract void afterDateSelected(Date dateWithZeroTime);

    /**
     * @param dateWithZeroTime
     * @param time
     */
    protected abstract void afterHourSelected(Date dateWithZeroTime, Date time);

    /**
     *
     */
    protected abstract void setMaxMinValue();

    /**
     * @return {@link Validator}
     */
    protected abstract Validator addValidator();

    /**
     * @return {@link String}
     */
    protected abstract String dateTooltip();

    /**
     * @return {@link String}
     */
    protected abstract String hourTooltip();

    /**
     * @return {@link String}
     */
    protected abstract String minuteTooltip();

    /**
     * @return {@link String}
     */
    protected abstract String fieldLabel();

    /**
     * @return {@link Date}
     */
    public Date getDate() {
        if (this.hourField.getValue() != null)
            this.date.setHours(this.hourField.getValue().intValue());
        if (this.minuteField.getValue() != null)
            this.date.setMinutes(this.minuteField.getValue().intValue());
        return this.date;
    }

    protected boolean validateDate() {
        return this.date != null && this.hourField.isValid() && this.minuteField.isValid();
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.minuteField.reset();
        this.minuteField.reset();
        this.dateField.reset();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
//        initComponents();
    }

    private void initComponents() {
        this.date = null;
        this.dateField.reset();
        this.hourField.reset();
        this.minuteField.reset();
        super.reset();
    }

    protected void buildDateField() {
        this.dateField.setValue(this.limitDate);
        this.hourField.setValue(this.limitDate.getHours());
        this.minuteField.setValue(this.limitDate.getMinutes());
    }

    /**
     * @param dateFrom
     * @param dateTo
     */
    public void bindDate(Date dateFrom, Date dateTo) {
        dateField.setMaxValue(dateTo);
        dateField.setMinValue(dateFrom);
    }

    @Override
    public void removeFilterTime() {
        initComponents();
    }
}
