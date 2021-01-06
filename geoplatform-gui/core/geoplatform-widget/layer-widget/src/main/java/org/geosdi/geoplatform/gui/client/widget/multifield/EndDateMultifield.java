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
