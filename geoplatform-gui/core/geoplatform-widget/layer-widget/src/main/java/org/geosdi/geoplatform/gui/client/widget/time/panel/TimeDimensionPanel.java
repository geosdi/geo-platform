package org.geosdi.geoplatform.gui.client.widget.time.panel;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;

public class TimeDimensionPanel extends GeoPlatformContentPanel {

    private DateField startDate;
    private DateField endDate;
    private SpinnerField startHourField;
    private SpinnerField startMinField;
    private SpinnerField endHourField;
    private SpinnerField endMinField;
    private CheckBox endDateCheckBox;
    private DateTimeFormat dtFormat;
    private MultiField multiDatesEnd;

    public TimeDimensionPanel() {
        super(Boolean.TRUE);
    }

    @Override
    public void addComponent() {
        this.dtFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
        this.endDateCheckBox = new CheckBox();
        this.endDateCheckBox.setValue(true);
        this.endDateCheckBox.setBoxLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_enableToDate());
        this.endDateCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                multiDatesEnd.setVisible(endDateCheckBox.getValue());
            }
        });
        this.buildEndDate();
        super.add(buildStartDate(), new FlowData(10));
        super.add(this.endDateCheckBox, new FlowData(10));
        super.add(this.multiDatesEnd, new FlowData(10));
    }

    private MultiField buildStartDate() {
        this.startDate = new DateField();
        this.startDate.setToolTip(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startDateTooltipText());
        this.startDate.getPropertyEditor().setFormat(dtFormat);
        this.startDate.setWidth(100);
        this.startDate.setEditable(false);
        DatePicker startDatePicker = this.startDate.getDatePicker();
        startDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {
            @Override
            public void handleEvent(DatePickerEvent dpe) {
            }
        });
        this.startHourField = new SpinnerField();
        this.startHourField.setWidth(70);
        this.startHourField.setMinValue(0);
        this.startHourField.setMaxValue(23);

        this.startMinField = new SpinnerField();
        this.startMinField.setWidth(70);
        this.startMinField.setMinValue(1);
        this.startMinField.setMaxValue(60);

        this.endDate = new DateField();
        this.endDate.setToolTip(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endDateTooltipText());
        this.endDate.getPropertyEditor().setFormat(dtFormat);
        this.endDate.setWidth(100);
        this.endDate.setEditable(false);
        MultiField multiDates = new MultiField();
        multiDates.setOrientation(Style.Orientation.HORIZONTAL);
        multiDates.add(new LabelField(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesFromText()));
        multiDates.add(this.startDate);
        multiDates.add(this.startHourField);
        multiDates.add(new LabelField(":"));
        multiDates.add(startMinField);
        return multiDates;
    }

    private void buildEndDate() {
        this.endDate = new DateField();
        this.endDate.setToolTip(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endDateTooltipText());
        this.endDate.getPropertyEditor().setFormat(dtFormat);
        this.endDate.setWidth(100);
        this.endDate.setEditable(false);
        DatePicker startDatePicker = this.endDate.getDatePicker();
        startDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {
            @Override
            public void handleEvent(DatePickerEvent dpe) {
            }
        });
        this.endHourField = new SpinnerField();
        this.endHourField.setWidth(70);
        this.endHourField.setMinValue(0);
        this.endHourField.setMaxValue(23);

        this.endMinField = new SpinnerField();
        this.endMinField.setWidth(70);
        this.endMinField.setMinValue(1);
        this.endMinField.setMaxValue(60);

        this.endDate = new DateField();
        this.endDate.setToolTip(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endDateTooltipText());
        this.endDate.getPropertyEditor().setFormat(dtFormat);
        this.endDate.setWidth(100);
        this.endDate.setEditable(false);
        this.multiDatesEnd = new MultiField();
        this.multiDatesEnd.setOrientation(Style.Orientation.HORIZONTAL);
        this.multiDatesEnd.add(new LabelField(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesToText()));
        this.multiDatesEnd.add(this.endDate);
        this.multiDatesEnd.add(this.endHourField);
        this.multiDatesEnd.add(new LabelField(":"));
        this.multiDatesEnd.add(this.endMinField);
    }

    @Override
    public void initSize() {
        setHeaderVisible(false);
        setBorders(false);
    }

    @Override
    public void setPanelProperties() {

    }
}
