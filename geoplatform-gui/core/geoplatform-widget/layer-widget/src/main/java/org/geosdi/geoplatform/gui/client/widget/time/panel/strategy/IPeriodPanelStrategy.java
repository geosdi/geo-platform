package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy;

import com.google.common.collect.Maps;
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

import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */


public interface IPeriodPanelStrategy {

    /**
     *
     */
    void calculatePeriod();

    class PeriodPanelStrategy extends IStrategyPanel.AbstractPanelStrategy implements IPeriodPanelStrategy {

        static DateTimeFormat parseDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        private Map<String, Long> mapPeriod = Maps.newLinkedHashMap();


        @Override
        public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
            parseExtentValue(treePanel);
            TimePeriodFormPanel timePeriodFormPanel = LayerModuleInjector.MainInjector.getInstance().getTimeDimensionFormPanel();
            WidgetPropertiesHandlerManager.fireEvent(new GPBeanTreeModelBindingEvent(treePanel.getSelectionModel().getSelectedItem()));
            return new TimePeriodPanel(timePeriodFormPanel);
        }

        protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
//            GWT.log("@@@@@@@@@@@@@@@@@"+treePanel.getSelectionModel().getSelectedItem());
            this.buildMap();
            String[] values = (((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split("/"));
            this.valuesMap.put(TypeValueEnum.DATE_FROM, this.parseDateFormat.parse(values[0].replace("Z", "")));
            this.valuesMap.put(TypeValueEnum.DATE_TO, this.parseDateFormat.parse(values[1].replace("Z", "")));
            this.valuesMap.put(TypeValueEnum.PERIOD, values[2]);
        }

        private void buildMap() {
            this.mapPeriod.put("Y", 31536000000l);
            this.mapPeriod.put("M", 2592000000l);
            this.mapPeriod.put("W", 604800000l);
            this.mapPeriod.put("D", 86400000l);
            this.mapPeriod.put("H", 3600000l);
            this.mapPeriod.put("I", 60000l);
            this.mapPeriod.put("S", 1000l);
        }

        public void calculatePeriod() {
            //P3Y1M1W1DT1H1M1S
            String p = this.valuesMap.get(TypeValueEnum.PERIOD).toString().substring(1);
            String time = null;
            String date = null;
            if (p.contains("T")) {
                time = p.substring(p.indexOf("T") + 1).replace("M", "I");
                date = p.substring(0, p.indexOf("T"));
            } else {
                date = p;
            }
            Long period = 0l;

            for (String k : this.mapPeriod.keySet()) {
                if (date != null && date.contains(k)) {

                    period += Integer.parseInt(date.substring(0, date.indexOf(k))) * this.mapPeriod.get(k);
                    date = date.substring(date.indexOf(k) + 1);
                }
                if (time != null && time.contains(k)) {
                    period += Integer.parseInt(time.substring(0, time.indexOf(k))) * this.mapPeriod.get(k);
                    time = time.substring(time.indexOf(k) + 1);
                }
            }
        }


    }


}
