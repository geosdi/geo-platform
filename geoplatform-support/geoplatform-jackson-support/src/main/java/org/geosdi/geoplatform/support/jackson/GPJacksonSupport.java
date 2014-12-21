/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.support.jackson;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import java.text.SimpleDateFormat;
import org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.FAIL_ON_UNKNOW_PROPERTIES_DISABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.INDENT_OUTPUT_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.UNWRAP_ROOT_VALUE_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRAP_ROOT_VALUE_ENABLE;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonSupport implements JacksonSupport {

    private final ObjectMapper mapper;

    public GPJacksonSupport() {
        this(defaultProp());
    }

    public GPJacksonSupport(GPJacksonSupportEnum... features) {
        mapper = new ObjectMapper();
        for (GPJacksonSupportEnum feature : features) {
            if (feature.getFeature() instanceof MapperFeature) {
                mapper.configure((MapperFeature) feature.getFeature(),
                        feature.getValue());
            } else if (feature.getFeature() instanceof DeserializationFeature) {
                mapper.configure((DeserializationFeature) feature.getFeature(),
                        feature.getValue());
            } else if (feature.getFeature() instanceof SerializationFeature) {
                mapper.configure((SerializationFeature) feature.getFeature(),
                        feature.getValue());
            }
        }

        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(
                TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();

        mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(
                primary, secondary));

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
    }

    @Override
    public final ObjectMapper getDefaultMapper() {
        return this.mapper;
    }

    @Override
    public final void registerModule(Module module) {
        this.mapper.registerModule(module);
    }

    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

    public static GPJacksonSupportEnum[] defaultProp() {
        return new GPJacksonSupportEnum[]{UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE};
    }
}
