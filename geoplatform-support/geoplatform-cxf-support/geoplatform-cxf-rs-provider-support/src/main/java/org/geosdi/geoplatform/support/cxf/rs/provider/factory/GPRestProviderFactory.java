/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.cxf.rs.provider.factory;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.geosdi.geoplatform.support.cxf.rs.provider.configurator.GPRestProviderType;
import org.geosdi.geoplatform.support.cxf.rs.provider.jackson.CXFJacksonProvider;
import org.geosdi.geoplatform.support.cxf.rs.provider.jettyson.GPJSONProvider;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPRestProviderFactory {

    static final GPRestProviderType DEFAULT_TYPE = GPRestProviderType.JACKSON;

    private static final Logger logger = LoggerFactory.getLogger(
            GPRestProviderFactory.class);

    private GPRestProviderFactory() {
    }

    public static Object createProvider(GPRestProviderType type) {
        switch ((type != null) ? type : DEFAULT_TYPE) {
            case JACKSON:
                logger.debug("\n\n############################### RestProviderFactory "
                                + "is building an instance of {}\n\n",
                        CXFJacksonProvider.class);
                return new CXFJacksonProvider();

            case JACKSON_WITHOUT_ROOT:
                logger.debug("\n\n############################### RestProviderFactory "
                        + "is building an instance of {}\n\n", CXFJacksonProvider.class);
                return new CXFJacksonProvider(new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
                        FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                        ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                        WRAP_ROOT_VALUE_DISABLE,
                        INDENT_OUTPUT_ENABLE)
                        .configure(GPJsonIncludeFeature.NON_NULL));

            case JACKSON_JODA_TIME:
                logger.debug("\n\n############################### RestProviderFactory "
                                + "is building an instance of {} with JODA_TIME SUPPORT\n\n",
                        CXFJacksonProvider.class);
                return new CXFJacksonProvider(new GPJacksonSupport().registerModule(new JodaModule())
                        .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
                        .configure(GPJsonIncludeFeature.NON_NULL));

            case JACKSON_JODA_TIME_WITHOUT_ROOT:
                logger.debug("\n\n############################### RestProviderFactory "
                                + "is building an instance of {} with JODA_TIME_WITHOUT_ROOT SUPPORT\n\n",
                        CXFJacksonProvider.class);
                return new CXFJacksonProvider(new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
                        FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                        ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                        WRAP_ROOT_VALUE_DISABLE,
                        INDENT_OUTPUT_ENABLE)
                        .registerModule(new JodaModule())
                        .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
                        .configure(GPJsonIncludeFeature.NON_NULL));

            case JETTYSON:
                logger.debug("\n\n############################### "
                                + "RestProviderFactory is building an instance of {}\n\n",
                        GPJSONProvider.class);
                return new GPJSONProvider<>();

            default:
                logger.debug("\n\n############################### "
                                + "RestProviderFactory DEFAULT Provider instance of {}\n\n",
                        GPJSONProvider.class);
                return new GPJSONProvider<>();
        }
    }

}
