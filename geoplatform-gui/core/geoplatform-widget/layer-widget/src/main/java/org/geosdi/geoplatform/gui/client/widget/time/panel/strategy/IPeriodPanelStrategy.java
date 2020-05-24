package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy;

import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.binding.event.GPBeanTreeModelBindingEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.panel.TimePeriodFormPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimePeriodPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */


public interface IPeriodPanelStrategy {

    /**
     * @return {@link Long}
     */
    Long calculatePeriod();

    class PeriodPanelStrategy extends IStrategyPanel.AbstractPanelStrategy implements IPeriodPanelStrategy {

        static DateTimeFormat parseDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        private Map<String, Long> mapPeriod = Maps.newLinkedHashMap();
        private IParseMediator.ParseMediator parseMediator;

        public PeriodPanelStrategy(IParseMediator.ParseMediator parseMediator) {
            this.parseMediator = parseMediator;
        }


        @Override
        public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
            parseExtentValue(treePanel);
            TimePeriodFormPanel timePeriodFormPanel = LayerModuleInjector.MainInjector.getInstance().getTimeDimensionFormPanel();
            WidgetPropertiesHandlerManager.fireEvent(new GPBeanTreeModelBindingEvent(treePanel.getSelectionModel().getSelectedItem()));
            return new TimePeriodPanel(timePeriodFormPanel);
        }

        protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
            String[] values = (((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split("/"));
//            GWT.log("@@@@"+values);
            this.valuesMap.put(TypeValueEnum.DATE_FROM, this.parseDateFormat.parse(values[0].replace("Z", "")));
            this.valuesMap.put(TypeValueEnum.DATE_TO, this.parseDateFormat.parse(values[1].replace("Z", "")));
            this.valuesMap.put(TypeValueEnum.PERIOD, values[2]);
            calculatePeriod();
        }


        /**
         * @return {@link Long}
         */
        public Long calculatePeriod() {
            Long period = this.parseMediator.calculatePeriod("P3Y1M1W1DT1H1M1S");
            GWT.log("P:" + period);
//            Long period = this.parseMediator.calculatePeriod(this.valuesMap.get(TypeValueEnum.PERIOD).toString());
            return period;
        }


    }


}
