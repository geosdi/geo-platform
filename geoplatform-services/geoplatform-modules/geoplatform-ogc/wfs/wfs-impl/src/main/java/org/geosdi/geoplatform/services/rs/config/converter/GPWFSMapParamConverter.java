package org.geosdi.geoplatform.services.rs.config.converter;

import javax.ws.rs.ext.ParamConverter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWFSMapParamConverter implements ParamConverter<Map<String, String>> {

    /**
     * Parse the supplied value and create an instance of {@code T}.
     *
     * @param value the string value.
     * @return the newly created instance of {@code T}.
     * @throws IllegalArgumentException if the supplied string cannot be
     *                                  parsed or is {@code null}.
     */
    @Override
    public Map<String, String> fromString(String value) {
        value = value.replace("{", "").replace("}", "");
        return Pattern.compile(",").splitAsStream(value)
                .map(v -> v.split("="))
                .collect(toMap(parts -> parts[0], parts -> parts[1], (v1, v2) -> v1, LinkedHashMap::new));
    }

    /**
     * Convert the supplied value to a String.
     * <p>
     * This method is reserved for future use. Proprietary JAX-RS extensions may leverage the method.
     * Users should be aware that any such support for the method comes at the expense of producing
     * non-portable code.
     * </p>
     *
     * @param value the value of type {@code T}.
     * @return a String representation of the value.
     * @throws IllegalArgumentException if the supplied object cannot be
     *                                  serialized or is {@code null}.
     */
    @Override
    public String toString(Map<String, String> value) {
        return value.toString();
    }
}