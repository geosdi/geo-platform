package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation;

import org.geosdi.geoplatform.gui.client.puregwt.action.event.GPPeriodNoDateEvent;
import org.geosdi.geoplatform.gui.client.puregwt.action.event.GPPeriodWithSingleDateEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodSingleDateOperation implements IStrategyOperation.ITypeOperationStrategy {

    /**
     * @param store
     * @param from
     * @param to
     */
    @Override
    public void getApplyOperation(List<Date> store, Date from, Date to) {
        for (int i = 0; i < store.size(); i++) {
            if (i > 0 && i < store.size() && store.get(i).getTime() > from.getTime()) {
                WidgetPropertiesHandlerManager.fireEvent(new GPPeriodNoDateEvent(store.get(i - 1), store.get(i)));
                break;
            } else if (store.get(i).getTime() == from.getTime()) {
                WidgetPropertiesHandlerManager.fireEvent(new GPPeriodWithSingleDateEvent(i));
                break;
            }
        }
    }
}
