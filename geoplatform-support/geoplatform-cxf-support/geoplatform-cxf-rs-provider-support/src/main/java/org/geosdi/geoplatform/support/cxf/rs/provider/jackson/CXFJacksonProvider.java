/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.cxf.rs.provider.jackson;

import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.AnnotationIntrospector;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;

import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder.GPJacksonSupportBuilder.builder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.USE_FAST_BIG_NUMBER_PARSER_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CXFJacksonProvider extends CXFBaseJacksonProvider {

    private static final JacksonSupport<JsonMapper> JACKSON_SUPPORT = builder(FALSE)
            .withIntespectorBuilder(JAKARTA)
            .configure(UNWRAP_ROOT_VALUE_ENABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE, NON_NULL,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_ENABLE, INDENT_OUTPUT_ENABLE,
                    FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE, USE_FAST_DOUBLE_PARSER_ENABLE, USE_FAST_BIG_NUMBER_PARSER_ENABLE)
            .withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
            .build();

    public CXFJacksonProvider() {
        this(JACKSON_SUPPORT.getDefaultMapper(), JAKARTA.build());
    }

    /**
     * @param theJacksonSupport
     */
    public CXFJacksonProvider(@Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(((theJacksonSupport != null) ? theJacksonSupport.getDefaultMapper() : JACKSON_SUPPORT.getDefaultMapper()), JAKARTA.build());
    }

    /**
     *
     * @param theMapper
     * @param theAnnotationIntrospector
     */
    public CXFJacksonProvider(JsonMapper theMapper, AnnotationIntrospector theAnnotationIntrospector) {
        super(((theMapper != null) ? theMapper : JACKSON_SUPPORT.getDefaultMapper()), theAnnotationIntrospector);
    }

    /**
     * @return {@link JsonMapper}
     */
    @Override
    public final JsonMapper getDefaultMapper() {
        return _mapperConfig.getDefaultMapper();
    }

    /**
     * @return {@link JsonMapper}
     */
    @Override
    public JsonMapper getConfiguredMapper() {
        return _mapperConfig.getConfiguredMapper();
    }
}