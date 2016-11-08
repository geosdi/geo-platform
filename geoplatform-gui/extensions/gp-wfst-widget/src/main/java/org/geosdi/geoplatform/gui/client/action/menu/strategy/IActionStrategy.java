package org.geosdi.geoplatform.gui.client.action.menu.strategy;

import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.ShowFeaturesWidget;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IActionStrategy {

    /**
     *
     */
    void showWidget();

    /**
     *
     * @param widgetType
     */
    void setWidgetType(WidgetType widgetType);

    /**
     *
     * @return {@link WidgetType}
     */
    public WidgetType getWidgetType();

    enum WidgetType{
        EDIT_WFS_ACTION,SHOW_FEATURES;
    }

    @Singleton
    class ActionStrategy implements IActionStrategy{

        private Map<WidgetType,GeoPlatformWindow> platformWindowMap = new HashMap<>();
        private WidgetType widgetType;

        @Inject
        public ActionStrategy(FeatureWidget theFeatureWidget,ShowFeaturesWidget showFeaturesWidget){
            platformWindowMap.put(WidgetType.EDIT_WFS_ACTION,theFeatureWidget);
            platformWindowMap.put(WidgetType.SHOW_FEATURES,showFeaturesWidget);
        }

        /**
         *
         */
        @Override
        public void showWidget() {
            platformWindowMap.get(this.widgetType).show();
        }

        /**
         * @param widgetType
         */
        @Override
        public void setWidgetType(WidgetType widgetType) {
            this.widgetType = widgetType;
        }

        /**
         *
         * @return {@link WidgetType}
         */
        public WidgetType getWidgetType() {
            return widgetType;
        }
    }

}
