package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.puregwt.action.event.GPPeriodWithRangeOperationEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodWithRangeOperation implements IStrategyOperation.ITypeOperationStrategy {

    /**
     * @param store
     * @param from
     * @param to
     */
    @Override
    public void getApplyOperation(List<Date> store, Date from, Date to) {
        List<Date> partialStore = Lists.newArrayList();
        for (Date d : store) {
            if (d.getTime() >= from.getTime()
                    && (d.getTime() <= to.getTime())) {
                partialStore.add(d);
            }
        }
        WidgetPropertiesHandlerManager.fireEvent(new GPPeriodWithRangeOperationEvent(partialStore));
    }
}
