package org.geosdi.geoplatform.services.rs.config.converter;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Provider
public class GPWFSMapParamConverterProvider implements ParamConverterProvider {

    /**
     * Obtain a {@link ParamConverter} that can provide from/to string conversion
     * for an instance of a particular Java type.
     *
     * @param rawType     the raw type of the object to be converted.
     * @param genericType the type of object to be converted. E.g. if an String value
     *                    representing the injected request parameter
     *                    is to be converted into a method parameter, this will be the
     *                    formal type of the method parameter as returned by {@code Class.getGenericParameterTypes}.
     * @param annotations an array of the annotations associated with the convertible
     *                    parameter instance. E.g. if a string value is to be converted into a method parameter,
     *                    this would be the annotations on that parameter as returned by
     *                    {@link Method#getParameterAnnotations}.
     * @return the string converter, otherwise {@code null}.
     */
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        return (rawType.equals(Map.class) ? (ParamConverter<T>) new GPWFSMapParamConverter() : null);
    }
}