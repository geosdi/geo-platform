package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import com.google.gwt.i18n.shared.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

import java.time.LocalDateTime;
import java.time.MonthDay;
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
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusYears(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    MONTH("M", 2592000000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_monthTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusMonths(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    WEEK("W", 604800000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_weekTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusWeeks(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    DAY("D", 86400000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_dayTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusDays(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    HOUR("H", 3600000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_hourTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusHours(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    MINUTE("M", 60000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_minuteTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusMinutes(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
                }
            };
        }
    },
    SECOND("S", 1000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_secondTitleText()) {
        @Override
        public IDateOperation getOperatorInstance(final int value) {
            return new IDateOperation() {
                @Override
                public Date addDate(Date data) {
                    DateTimeFormat fmt = DateTimeFormat.getFormat("--MM-dd");
                    MonthDay monthDay = MonthDay.parse(fmt.format(data));
                    LocalDateTime l = (LocalDateTime.of(data.getYear(), data.getMonth() + 1, monthDay.getDayOfMonth(), data.getHours(), data.getMinutes())
                            .plusSeconds(value));
                    return new Date(l.getYear(), l.getMonthValue() - 1, l.getDayOfMonth(), l.getHour(), l.getMinute());
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
