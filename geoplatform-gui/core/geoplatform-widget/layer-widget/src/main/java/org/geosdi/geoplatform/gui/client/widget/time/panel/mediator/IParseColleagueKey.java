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
package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.gwttime.time.LocalDateTime;

import java.util.Date;

/**
 * @author Vito Salvia- CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum IParseColleagueKey {


    YEAR("Y", 31536000000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_yearTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusYears(value));

                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());
                }
            };
        }
    },
    MONTH("M", 2592000000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_monthTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusMonths(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());
                }
            };
        }
    },
    WEEK("W", 604800000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_weekTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusWeeks(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());
                }
            };
        }
    },
    DAY("D", 86400000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_dayTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusDays(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());

                }
            };
        }
    },
    HOUR("H", 3600000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_hourTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusHours(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());
                }
            };
        }
    },
    MINUTE("M", 60000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_minuteTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusMinutes(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());

                }
            };
        }
    },
    SECOND("S", 1000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_secondTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date changeDate(Date data) {
                    LocalDateTime l = (LocalDateTime.fromDateFields(data)
                            .plusSeconds(value));
                    return new Date(l.getYear() - 1900, l.getMonthOfYear() - 1, l.getDayOfMonth(), l.getHourOfDay(), l.getMinuteOfHour(), l.getSecondOfMinute());
                }
            };
        }
    };

    private final String timeLabel;
    private final Long value;
    private final String label;

    IParseColleagueKey(String timeLabel, Long value, String label) {
        this.timeLabel = timeLabel;
        this.value = value;
        this.label = label;
    }

    /**
     * @return
     */
    public abstract IDateOperation getOperatorInstance(int value);

    /**
     * @return
     */
    public String getTimeLabel() {
        return timeLabel;
    }

    /**
     * @return
     */
    public Long getValue() {
        return value;
    }

    /**
     * @return
     */
    public String getLabel() {
        return label;
    }

}
