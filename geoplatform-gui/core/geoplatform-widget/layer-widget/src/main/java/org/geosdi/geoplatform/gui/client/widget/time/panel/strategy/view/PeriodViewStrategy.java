package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view;

import com.google.gwt.i18n.shared.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.binding.event.GPBeanTreeModelBindingEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.panel.TimePeriodFormPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimePeriodPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodViewStrategy extends IStrategyView.AbstractPanelStrategy {

    static DateTimeFormat parseDateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    @Override
    public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        parseExtentValue(treePanel);
        TimePeriodFormPanel timePeriodFormPanel = LayerModuleInjector.MainInjector.getInstance().getTimeDimensionFormPanel();
        WidgetPropertiesHandlerManager.fireEvent(new GPBeanTreeModelBindingEvent(treePanel));
        return new TimePeriodPanel(timePeriodFormPanel);
    }

        protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
            String[] values = (((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split("/"));
            Date dateTo;
            if (values[2] == "PT5M") {
                dateTo = new Date();
                int minutes = dateTo.getMinutes();
                int minutesDiff = minutes % 5 + 10;
                dateTo.setMinutes(minutes - minutesDiff);
                dateTo.setSeconds(0);
            } else {
                dateTo = this.parseDateFormat.parse(values[1]);
            }
            this.valuesMap.put(TypeValueEnum.DATE_FROM, this.parseDateFormat.parse(values[0]));
            this.valuesMap.put(TypeValueEnum.DATE_TO, dateTo);
            this.valuesMap.put(TypeValueEnum.PERIOD, values[2]);
        }


}


