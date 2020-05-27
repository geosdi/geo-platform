package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

/**
 * @author Vito Salvia- CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum IParseColleagueKey {

    YEAR("Y", 31536000000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_yearTitleText()),
    MONT("M", 2592000000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_monthTitleText()),
    WEEK("W", 604800000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_weekTitleText()),
    DAY("D", 86400000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_dayTitleText()),
    HOUR("H", 3600000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_hourTitleText()),
    MINUTE("M", 60000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_minuteTitleText()),
    SECOND("S", 1000l, LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_secondTitleText());

    private final String timeLabel;
    private final Long value;
    private final String label;

    IParseColleagueKey(String timeLabel, Long value, String label) {
        this.timeLabel = timeLabel;
        this.value = value;
        this.label = label;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public Long getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
