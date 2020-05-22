package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Singleton;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IStrategyPanel {

    /**
     * @param value
     * @param treePanel
     * @return
     */
    GeoPlatformContentPanel getPanel(Boolean value, GPTreePanel<GPBeanTreeModel> treePanel);

    /**
     * @param value
     * @return
     */
    Map<TypeValueEnum, Object> getExtentValues(Boolean value);

    /**
     * @param value
     * @return
     */
    ITypePanelStrategy getStrategy(Boolean value);


    interface ITypePanelStrategy {

        /**
         * @param treePanel
         * @return
         */
        GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel);

        /**
         * @return
         */
        Map<TypeValueEnum, Object> getValuesMap();

    }

    abstract class AbstractPanelStrategy implements ITypePanelStrategy {

        protected Map<TypeValueEnum, Object> valuesMap = Maps.newEnumMap(TypeValueEnum.class);

        /**
         * @return
         */
        public Map<TypeValueEnum, Object> getValuesMap() {
            return valuesMap;
        }

        protected abstract void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel);

    }

    @Singleton
    class StrategyPanel implements IStrategyPanel {

        final Map<Boolean, ITypePanelStrategy> panelMap = Maps.newHashMap();

        public StrategyPanel() {
            this.panelMap.put(Boolean.TRUE, new PeriodPanelStrategy());
            this.panelMap.put(Boolean.FALSE, new FilterPanelStrategy());
        }

        /**
         * @param value
         * @param treePanel
         * @return
         */
        @Override
        public GeoPlatformContentPanel getPanel(Boolean value, GPTreePanel<GPBeanTreeModel> treePanel) {
            return this.panelMap.get(value).buildPanel(treePanel);
        }

        @Override
        public Map<TypeValueEnum, Object> getExtentValues(Boolean value) {
            return this.panelMap.get(value).getValuesMap();
        }

        /**
         * @param value
         * @return
         */
        @Override
        public ITypePanelStrategy getStrategy(Boolean value) {
            return this.panelMap.get(value);
        }
    }

}
