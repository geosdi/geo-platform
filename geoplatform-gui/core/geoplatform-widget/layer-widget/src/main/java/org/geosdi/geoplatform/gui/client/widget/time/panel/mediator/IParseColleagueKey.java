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
