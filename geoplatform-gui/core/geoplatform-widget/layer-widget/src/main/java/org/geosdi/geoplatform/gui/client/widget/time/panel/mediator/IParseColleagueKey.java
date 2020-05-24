package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

public enum IParseColleagueKey {

    YEAR("Y", 31536000000l), MONT("M", 2592000000l), WEEK("W", 604800000l), DAY("D", 86400000l),
    HOUR("H", 3600000l), MINUTE("M", 60000l), SECOND("S", 1000l);

    private final String timeLabel;
    private final Long value;

    IParseColleagueKey(String timeLabel, Long value) {
        this.timeLabel = timeLabel;
        this.value = value;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public Long getValue() {
        return value;
    }
}
