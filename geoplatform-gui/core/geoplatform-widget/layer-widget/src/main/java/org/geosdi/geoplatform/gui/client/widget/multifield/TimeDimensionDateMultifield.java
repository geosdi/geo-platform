package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.*;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
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
        super.setSpacing(20);
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

        super.setFieldLabel(fieldLabel());
        super.setOrientation(Style.Orientation.HORIZONTAL);
        super.add(this.dateField);
        super.add(this.hourField);
        super.add(minuteField);
        super.addStyleName("dateMultifield");
//        super.setAutoWidth(Boolean.FALSE);
//        super.addListener(Events.Update, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                GWT.log("@@"+be);
//            }
//        });
        super.setValidator(new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
//                GWT.log(  "" +getElement().getChild(1).getChild(0));
//                ((El)field.getElement().getParentElement().getChildNodes().getItem(2)).setStyleAttribute("display", "none");
                return "ERROR";
            }
        });
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

    @Override
    protected void onAttach() {
        super.onAttach();
        initComponents();
    }

    private void initComponents() {
        this.date = null;
        this.dateField.reset();
        this.hourField.reset();
        this.minuteField.reset();
    }

    public void clearErrors() {
        this.hourField.clearInvalid();
        this.minuteField.clearInvalid();
        this.dateField.clearInvalid();
    }

}
