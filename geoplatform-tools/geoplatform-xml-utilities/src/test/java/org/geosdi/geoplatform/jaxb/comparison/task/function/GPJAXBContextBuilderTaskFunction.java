package org.geosdi.geoplatform.jaxb.comparison.task.function;

import org.geosdi.geoplatform.jaxb.comparison.task.GPJAXBContextBuilderTaskPooled;
import org.geosdi.geoplatform.jaxb.comparison.task.GPJAXBContextBuilderTaskSimple;
import org.geosdi.geoplatform.jaxb.comparison.task.GPJAXBContextBuilderTaskType;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextBuilderTaskFunction implements Function<Integer, Callable<Long>> {

    private final GPJAXBContextBuilderTaskType taskType;

    public GPJAXBContextBuilderTaskFunction(GPJAXBContextBuilderTaskType theTaskType) {
        this.taskType = theTaskType;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public Callable<Long> apply(Integer integer) {
        return ((this.taskType == GPJAXBContextBuilderTaskType.SIMPLE)
                ? new GPJAXBContextBuilderTaskSimple() : new GPJAXBContextBuilderTaskPooled());
    }
}
