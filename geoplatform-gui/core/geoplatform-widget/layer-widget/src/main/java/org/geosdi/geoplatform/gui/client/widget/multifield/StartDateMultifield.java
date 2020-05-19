package org.geosdi.geoplatform.gui.client.widget.multifield;

import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

public class StartDateMultifield extends TimeDimensionDateMultifield {

    public StartDateMultifield() {
        super();
    }

    @Override
    protected String dateTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startDateTooltipText();
    }

    @Override
    protected String hourTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startHourTooltipText();
    }

    @Override
    protected String minuteTooltip() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_startMinuteTooltipText();
    }

    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesFromText();
    }

}
