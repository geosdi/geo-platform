package org.geosdi.geoplatform.gui.client.widget.multifield;

import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

public class EndDateMultifield extends TimeDimensionDateMultifield {

    public EndDateMultifield() {
        super();
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
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesToText();
    }

    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesFromText();
    }

}