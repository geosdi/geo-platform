package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.binding.GPDateBindingHandler;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class StartDateMultifield extends TimeDimensionDateMultifield {

    public StartDateMultifield() {
        super();
        WidgetPropertiesHandlerManager.addHandler(GPDateBindingHandler.TYPE, this);
    }

    @Override
    protected Validator addValidator() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                return validateDate() && getDate().before(limitDate) ? LayerModuleMessages.INSTANCE.LayerTimeFilterWidget_lowerLimitDate(limitDate.toString()) : null;
            }
        };
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

    @Override
    public void bindDate(GPBeanTreeModel gpTreePanel) {
        String dates[] = ((RasterTreeNode) gpTreePanel).getExtent().getValue().split(",");
        this.limitDate = this.parseDateFormat.parse(dates[0].replace("Z", ""));
        super.buildDateField();
        super.dateField.setMinValue(this.limitDate);
    }

}
