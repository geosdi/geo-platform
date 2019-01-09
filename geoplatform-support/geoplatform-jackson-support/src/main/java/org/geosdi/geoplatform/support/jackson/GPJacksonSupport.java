/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;

import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Stream;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonSupport implements JacksonSupport {

    private final ObjectMapper mapper;

    public GPJacksonSupport() {
        this(defaultProp());
    }

    /**
     * @param format
     */
    public GPJacksonSupport(DateFormat format) {
        this();
        this.mapper.setDateFormat(format);
    }

    /**
     * @param features
     */
    public GPJacksonSupport(JacksonSupportConfigFeature... features) {
        mapper = new ObjectMapper();
        Stream.of(features).filter(f -> f != null).forEach(f -> f.configureMapper(mapper));
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(
                TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();

        mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(
                primary, secondary));
    }

    /**
     * @param format
     * @return {@link JacksonSupport}
     */
    @Override
    public JacksonSupport setDateFormat(DateFormat format) {
        this.mapper.setDateFormat(format);
        return this;
    }

    /**
     * @return {@link ObjectMapper}
     */
    @Override
    public final ObjectMapper getDefaultMapper() {
        return this.mapper;
    }

    /**
     * @param module
     * @return {@link GPJacksonSupport}
     */
    @Override
    public final GPJacksonSupport registerModule(Module module) {
        this.mapper.registerModule(module);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

    /**
     * @param feature
     * @return {@link GPJacksonSupport}
     */
    @Override
    public GPJacksonSupport configure(JacksonSupportConfigFeature feature) {
        feature.configureMapper(mapper);
        return this;
    }

    /**
     * @param features
     * @return {@link GPJacksonSupport}
     */
    @Override
    public GPJacksonSupport configure(JacksonSupportConfigFeature... features) {
        for (JacksonSupportConfigFeature feature : features) {
            feature.configureMapper(mapper);
        }
        return this;
    }

    /**
     * @param locale
     * @return {@link GPJacksonSupport}
     */
    @Override
    public GPJacksonSupport setLocale(Locale locale) {
        this.mapper.setLocale(locale);
        return this;
    }

    /**
     * @param timeZone
     * @return {@link GPJacksonSupport}
     */
    @Override
    public GPJacksonSupport setTimeZone(TimeZone timeZone) {
        this.mapper.setTimeZone(timeZone);
        return this;
    }

    public static JacksonSupportConfigFeature[] defaultProp() {
        return new JacksonSupportConfigFeature[]{UNWRAP_ROOT_VALUE_ENABLE,
                FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                WRAP_ROOT_VALUE_ENABLE,
                INDENT_OUTPUT_ENABLE};
    }
}
