package org.geosdi.geoplatform.jaxb.function;

import org.geosdi.geoplatform.jaxb.model.Attribute;

import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AttributeFunction implements Function<Integer, Attribute> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Override
    public Attribute apply(Integer value) {
        return new Attribute() {

            {
                super.setAttributeName("ATTRIBUTE_NAME_TEST_" + value);
                super.setAttributeType("ATTRIBUTE_TYPE_TEST_" + value);
            }
        };
    }
}
