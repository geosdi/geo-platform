package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

public abstract class TimeDimensionDateMultifield extends MultiField {

    private DateField dateField;
    private SpinnerField hourField;
    private SpinnerField minuteField;
    private Date date;
    private DateTimeFormat dtFormat;

    public TimeDimensionDateMultifield() {
        super();
        this.addComponents();
    }

    private void addComponents() {
        this.dtFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
        this.dateField = new DateField();
        this.dateField.setToolTip(dateTooltip());
        this.dateField.getPropertyEditor().setFormat(dtFormat);
        this.dateField.setWidth(100);
        this.dateField.setEditable(false);
        this.dateField.setAllowBlank(Boolean.FALSE);
        DatePicker fromDatePicker = this.dateField.getDatePicker();
        fromDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {
            @Override
            public void handleEvent(DatePickerEvent dpe) {
                date = dpe.getDate();
            }
        });
        this.hourField = new SpinnerField();
        this.hourField.setToolTip(hourTooltip());
        this.hourField.setWidth(50);
        this.hourField.setMinValue(0);
        this.hourField.setMaxValue(23);
        this.hourField.setAllowBlank(Boolean.FALSE);

        this.minuteField = new SpinnerField();
        this.minuteField.setToolTip(minuteTooltip());
        this.minuteField.setWidth(50);
        this.minuteField.setMinValue(0);
        this.minuteField.setMaxValue(59);
        this.minuteField.setAllowBlank(Boolean.FALSE);

        super.setSpacing(20);
        super.setFieldLabel(fieldLabel());
        super.setOrientation(Style.Orientation.HORIZONTAL);
        super.add(this.dateField);
        super.add(this.hourField);
        super.add(minuteField);
    }


    protected abstract String dateTooltip();

    protected abstract String hourTooltip();

    protected abstract String minuteTooltip();

    protected abstract String fieldLabel();

    /**
     * @return {@link Date}
     */
    public Date getDate() {
        this.date.setHours(this.hourField.getValue().intValue());
        this.date.setMinutes(this.minuteField.getValue().intValue());
        return this.date;
    }

}
