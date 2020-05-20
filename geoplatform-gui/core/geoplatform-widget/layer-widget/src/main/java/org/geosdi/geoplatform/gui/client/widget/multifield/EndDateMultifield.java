package org.geosdi.geoplatform.gui.client.widget.multifield;

import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
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
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_endMinuteTooltipText();
    }

    @Override
    protected String fieldLabel() {
        return LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_multidatesFromText();
    }

}
