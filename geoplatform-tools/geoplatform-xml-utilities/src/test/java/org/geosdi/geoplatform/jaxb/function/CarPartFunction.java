package org.geosdi.geoplatform.jaxb.function;

import org.geosdi.geoplatform.jaxb.model.CarPart;

import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CarPartFunction implements Function<Integer, CarPart> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    public CarPart apply(Integer value) {
        return new CarPart() {

            {
                super.setPartName("PART_NAME_TEST_" + value);
            }
        };
    }
}
