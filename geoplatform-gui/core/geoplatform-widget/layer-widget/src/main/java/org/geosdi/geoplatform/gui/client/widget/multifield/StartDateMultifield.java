package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.puregwt.period.event.GPRefreshDateFromEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class StartDateMultifield extends TimePeriodDateMultifield {

    public StartDateMultifield() {
        super();
    }

    /**
     *
     */
    @Override
    protected void refreshDate() {
        WidgetPropertiesHandlerManager.fireEvent(new GPRefreshDateFromEvent(this.getDate()));
    }

    /**
     * @return {@link Validator}
     */
    @Override
    protected Validator addValidator() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                return validateDate() && getDate().before(limitDate) ? LayerModuleMessages.INSTANCE.LayerTimeFilterWidget_lowerLimitDate(limitDate.toString()) : null;
            }
        };
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String dateTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startDateTooltipText();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String hourTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startHourTooltipText();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String minuteTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startMinuteTooltipText();
    }

    /**
     * @return
     */
    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesFromText();
    }

    /**
     * @param dateFrom
     * @param dateTo
     */
    @Override
    public void bindDate(Date dateFrom, Date dateTo) {
        super.bindDate(dateFrom, dateTo);
        this.limitDate = dateFrom;
    }

    /**
     * @param dateWithZeroTime
     */
    protected void afterDateSelected(Date dateWithZeroTime) {
        if (date.getTime() == dateWithZeroTime.getTime()) {
            this.hourField.setMinValue(this.limitDate.getHours());
            this.minuteField.setMinValue(this.limitDate.getMinutes());
            this.hourField.setValue(this.limitDate.getHours());
            this.minuteField.setValue(this.limitDate.getMinutes());
        }
        if (this.date.getTime() != dateWithZeroTime.getTime()) {
            this.hourField.setMinValue(0);
            this.minuteField.setMinValue(0);
            this.hourField.setValue(0);
            this.minuteField.setValue(0);
        }
    }

    /**
     * @param dateWithZeroTime
     * @param time
     */
    protected void afterHourSelected(Date dateWithZeroTime, Date time) {
        if (date.getTime() == dateWithZeroTime.getTime() && limitDate.getHours() == time.getHours()) {
            this.minuteField.setMinValue(this.limitDate.getMinutes());
            this.minuteField.setValue(this.limitDate.getMinutes());
        } else {
            this.minuteField.setMinValue(0);
            this.minuteField.setValue(0);
        }

    }

    /**
     *
     */
    @Override
    protected void setMaxMinValue() {
        this.hourField.setMaxValue(23);
        this.minuteField.setMaxValue(59);
    }

}
