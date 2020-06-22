package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.puregwt.period.event.GPRefreshDateToEvent;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class EndDateMultifield extends TimePeriodDateMultifield {

    private CheckBox enableFutureDateCheckBox;

    public EndDateMultifield() {
        super();
    }

    /**
     *
     */
    @Override
    protected void refreshDate() {
        WidgetPropertiesHandlerManager.fireEvent(new GPRefreshDateToEvent(this.getDate()));
    }

    /**
     * @return {@link Validator}
     */
    @Override
    protected Validator addValidator() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                return validateDate() && getDate().after(limitDate) ? LayerModuleMessages.INSTANCE.LayerTimeFilterWidget_upperLimitDate(limitDate.toString()) : null;
            }
        };
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String dateTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endDateTooltipText();
    }

    @Override
    protected void addComponents() {
        super.addComponents();
        this.enableFutureDateCheckBox = new CheckBox();
        this.enableFutureDateCheckBox.setBoxLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_timeNoLimitsTitleText());
        this.enableFutureDateCheckBox.addStyleName("future-date-ck");
        this.enableFutureDateCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (enableFutureDateCheckBox.getValue()) {
                    GeoPlatformMessage.alertMessage(
                            LayerModuleConstants.INSTANCE.
                                    LayerTimeFilterWidget_timeFilterWarningTitleText(),
                            LayerModuleMessages.INSTANCE.
                                    LayerTimeFilterWidget_dateInFuture());
                    dateField.setMaxValue(null);
                } else {
                    dateField.setMaxValue(limitDate);
                }
            }
        });
        super.add(this.enableFutureDateCheckBox);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String hourTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endHourTooltipText();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String minuteTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endMinuteTooltipText();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesToText();
    }

    /**
     * @param dateFrom
     * @param dateTo
     */
    @Override
    public void bindDate(Date dateFrom, Date dateTo) {
        super.bindDate(dateFrom, dateTo);
        this.limitDate = dateTo;
    }

    /**
     * @param dateWithZeroTime
     */
    protected void afterDateSelected(Date dateWithZeroTime) {
        if (date.getTime() == dateWithZeroTime.getTime()) {
            this.hourField.setMaxValue(this.limitDate.getHours());
            this.minuteField.setMaxValue(this.limitDate.getMinutes());
            this.minuteField.setValue(this.limitDate.getMinutes());
            this.hourField.setValue(this.limitDate.getHours());
        }
        if (this.date.getTime() != dateWithZeroTime.getTime()) {
            this.hourField.setMaxValue(23);
            this.minuteField.setMaxValue(59);
            this.minuteField.setValue(0);
            this.hourField.setValue(0);
        }
    }


    /**
     * @param dateWithZeroTime
     * @param time
     */
    protected void afterHourSelected(Date dateWithZeroTime, Date time) {
        if (date.getTime() == dateWithZeroTime.getTime() && limitDate.getHours() == time.getHours()) {
            this.minuteField.setMaxValue(this.limitDate.getMinutes());
            this.minuteField.setValue(this.limitDate.getMinutes());
        } else {
            this.minuteField.setMaxValue(59);
            this.minuteField.setValue(0);
        }

    }

    /**
     *
     */
    @Override
    protected void setMaxMinValue() {
        this.hourField.setMinValue(0);
        this.minuteField.setMinValue(0);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.enableFutureDateCheckBox.setValue(Boolean.FALSE);
    }

}
