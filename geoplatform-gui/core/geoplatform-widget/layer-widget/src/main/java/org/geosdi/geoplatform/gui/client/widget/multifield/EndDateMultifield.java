package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class EndDateMultifield extends TimePeriodDateMultifield {

    public EndDateMultifield() {
        super();
    }

    @Override
    protected Validator addValidator() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                return validateDate() && getDate().after(limitDate) ? LayerModuleMessages.INSTANCE.LayerTimeFilterWidget_upperLimitDate(limitDate.toString()) : null;
            }
        };
    }


    @Override
    protected String dateTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endDateTooltipText();
    }

    @Override
    protected String hourTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endHourTooltipText();
    }

    @Override
    protected String minuteTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endMinuteTooltipText();
    }

    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesToText();
    }

    @Override
    public void bindDate(Date dateFrom, Date dateTo) {
        super.bindDate(dateFrom, dateTo);
        this.limitDate = dateTo;
    }

    protected void afterDateSelected(Date dateWithZeroTime) {
        if (date.getTime() == dateWithZeroTime.getTime()) {
            this.hourField.setMaxValue(this.limitDate.getHours());
            this.minuteField.setValue(this.limitDate.getMinutes());
            this.hourField.setValue(this.limitDate.getHours());
        }
        if (this.date.getTime() != dateWithZeroTime.getTime()) {
            this.hourField.setMaxValue(23);
            this.minuteField.setMaxValue(23);
            this.minuteField.setValue(0);
            this.hourField.setValue(0);
        }
    }

    protected void afterHourSelected(Date dateWithZeroTime, Date time) {
        if (date.getTime() == dateWithZeroTime.getTime() && limitDate.getHours() == time.getHours()) {
            this.minuteField.setMaxValue(limitDate.getMinutes());
        } else {
            this.minuteField.setMaxValue(59);
        }

    }

    @Override
    protected void setMaxMinValue() {
        this.hourField.setMinValue(0);
        this.minuteField.setMinValue(0);
    }

}
