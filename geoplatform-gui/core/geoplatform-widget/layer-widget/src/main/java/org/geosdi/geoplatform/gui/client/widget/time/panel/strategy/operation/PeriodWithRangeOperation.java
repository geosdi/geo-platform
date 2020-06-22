package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.puregwt.period.event.GPPeriodWithRangeOperationEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodWithRangeOperation implements IStrategyDateOperation.ITypeOperationStrategy {

    /**
     * @param store
     * @param from
     * @param to
     */
    @Override
    public void getApplyOperation(List<Date> store, Date from, Date to) {
        List<Date> partialStore = Lists.newArrayList();
        for (int i = 0; i < store.size(); i++) {
            Date d = store.get(i);
            if (d.getTime() > from.getTime()
                    && (d.getTime() <= to.getTime())) {
                if (partialStore.isEmpty())
                    partialStore.add(store.get(i - 1));
                partialStore.add(d);
            }
        }
        WidgetPropertiesHandlerManager.fireEvent(new GPPeriodWithRangeOperationEvent(partialStore));
    }
}
