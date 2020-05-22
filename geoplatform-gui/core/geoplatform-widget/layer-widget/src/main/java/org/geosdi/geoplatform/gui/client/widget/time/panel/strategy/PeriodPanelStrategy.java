package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy;

import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.binding.event.GPBeanTreeModelBindingEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.panel.TimePeriodFormPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimePeriodPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodPanelStrategy extends IStrategyPanel.AbstractPanelStrategy {

    static DateTimeFormat parseDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        parseExtentValue(treePanel);
        TimePeriodFormPanel timePeriodFormPanel = LayerModuleInjector.MainInjector.getInstance().getTimeDimensionFormPanel();
        WidgetPropertiesHandlerManager.fireEvent(new GPBeanTreeModelBindingEvent(treePanel.getSelectionModel().getSelectedItem()));
        return new TimePeriodPanel(timePeriodFormPanel);
    }

    protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
        String[] values = (((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split("/"));
        this.valuesMap.put(TypeValueEnum.DATE_FROM, this.parseDateFormat.parse(values[0].replace("Z", "")));
        this.valuesMap.put(TypeValueEnum.DATE_TO, this.parseDateFormat.parse(values[1].replace("Z", "")));
        this.valuesMap.put(TypeValueEnum.PERIOD, values[2]);
    }

}
