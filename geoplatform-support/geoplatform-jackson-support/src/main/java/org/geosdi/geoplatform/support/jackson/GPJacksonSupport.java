/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.support.jackson.annotation.GPJacksonXmlAnnotationIntrospectorBuilder;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.AnnotationIntrospector;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.introspect.AnnotationIntrospectorPair;
import tools.jackson.databind.introspect.JacksonAnnotationIntrospector;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonAnnotationIntrospectorBuilder.JACKSON;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.DEFAULT;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static tools.jackson.databind.json.JsonMapper.builder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonSupport implements JacksonSupport<JsonMapper> {

    private static final Logger logger = LoggerFactory.getLogger(GPJacksonSupport.class);
    //
    private final JsonMapper mapper;

    /**
     * @param theMapper
     */
    GPJacksonSupport(@Nonnull(when = NEVER) JsonMapper theMapper) {
        checkArgument(theMapper != null, "The Parameter mapper must not be null.");
        this.mapper = theMapper;
    }

    /**
     * <p>In this case will be used only {@link JacksonAnnotationIntrospector}</p>
     */
    public GPJacksonSupport() {
        this(DEFAULT);
    }

    /**
     * <p>If the parameter theBuilder is null then will be used only {@link JacksonAnnotationIntrospector}</p>
     *
     * @param theBuilder
     */
    public GPJacksonSupport(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theBuilder) {
        this(theBuilder, defaultProp());
    }

    /**
     * <p>In this case will be used only {@link JacksonAnnotationIntrospector}</p>
     *
     * @param features
     */
    public GPJacksonSupport(@Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        this(null, features);
    }

    /**
     * @param theBuilder
     * @param features
     */
    public GPJacksonSupport(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theBuilder, @Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        checkArgument(features != null, "The Parameter features must not be null.");
        this(theBuilder, Arrays.asList(features), null);
    }

    /**
     * @param theBuilder
     * @param theFeatures
     * @param theJacksonModules
     */
    public GPJacksonSupport(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theBuilder, @Nonnull(when = NEVER) List<JacksonSupportConfigFeature> theFeatures, @Nullable List<JacksonModule> theJacksonModules) {
        checkArgument(theFeatures != null, "The Parameter features must not be null.");
        JsonMapper.Builder jsonBuilder = builder();
        AnnotationIntrospector primary = JACKSON.build();
        AnnotationIntrospector secondary = ((theBuilder != null) ? theBuilder.build() : null);
        jsonBuilder.annotationIntrospector((secondary != null) ? new AnnotationIntrospectorPair(primary, secondary) : primary);
        fromIterable(theFeatures)
                .filter(Objects::nonNull)
                .doOnComplete(() -> logger.trace("##############{} configure all Features.", this.getProviderName()))
                .subscribe(f -> f.configureMapper(jsonBuilder), Throwable::printStackTrace);
        if (theJacksonModules != null) {
            fromIterable(theJacksonModules)
                    .filter(Objects::nonNull)
                    .doOnComplete(() -> logger.trace("##############{} configure all Jackson Modules.", this.getProviderName()))
                    .subscribe(jsonBuilder::addModule, Throwable::printStackTrace);
        }
        this.mapper = jsonBuilder.build();
    }

    /**
     * @return {@link JsonMapper}
     */
    @Override
    public final JsonMapper getDefaultMapper() {
        return this.mapper;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

    /**
     * @return {@link JacksonSupportConfigFeature[]}
     */
    public static JacksonSupportConfigFeature[] defaultProp() {
        return new JacksonSupportConfigFeature[]{UNWRAP_ROOT_VALUE_ENABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_ENABLE, INDENT_OUTPUT_ENABLE,
                FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE, USE_FAST_DOUBLE_PARSER_ENABLE, USE_FAST_BIG_NUMBER_PARSER_ENABLE};
    }
}