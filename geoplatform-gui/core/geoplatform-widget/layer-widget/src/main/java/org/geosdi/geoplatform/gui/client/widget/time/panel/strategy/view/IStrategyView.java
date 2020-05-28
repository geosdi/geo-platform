package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IStrategyView {

    /**
     * @param treePanel
     * @return
     */
    GeoPlatformContentPanel getPanel(GPTreePanel<GPBeanTreeModel> treePanel);

    /**
     * @return
     */
    Map<TypeValueEnum, Object> getExtentValues();

    /**
     * @return
     */
    ITypePanelStrategy getStrategy();


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
    class StrategyView implements IStrategyView {

        final Map<Boolean, ITypePanelStrategy> panelMap = Maps.newHashMap();
        private Boolean currentStrategy;

        @Inject
        public StrategyView() {
            this.panelMap.put(Boolean.FALSE, new PeriodViewStrategy());
            this.panelMap.put(Boolean.TRUE, new RangeViewStrategy());
        }

        /**
         * @param treePanel
         * @return
         */
        @Override
        public GeoPlatformContentPanel getPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
            this.currentStrategy = ((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().isRange();
            return this.panelMap.get(this.currentStrategy).buildPanel(treePanel);
        }

        @Override
        public Map<TypeValueEnum, Object> getExtentValues() {
            return this.panelMap.get(this.currentStrategy).getValuesMap();
        }

        /**
         * @return
         */
        @Override
        public ITypePanelStrategy getStrategy() {
            return this.panelMap.get(this.currentStrategy);
        }
    }

}
