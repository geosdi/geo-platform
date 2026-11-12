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
package org.geosdi.geoplatform.experimental.el9.rest.dao;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.ElasticSearchRestDAO;
import org.geosdi.geoplatform.experimental.el9.rest.model.GPPlace;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import java.io.StringReader;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.FAIL_ON_UNKNOW_PROPERTIES_DISABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.UNWRAP_ROOT_VALUE_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRAP_ROOT_VALUE_ENABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRITE_DATES_AS_TIMESTAMPS_DISABLE;

/**
 * DAO di prova per {@link GPPlace}, con il mapper di geoplatform-jackson-support che avvolge i documenti nel nome
 * radice.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPlaceDAO extends ElasticSearchRestDAO<GPPlace> {

    private static final String MAPPING = """
            {"properties": {"place": {"properties": {
                "id": {"type": "keyword"},
                "name": {"type": "text", "fields": {"keyword": {"type": "keyword"}}},
                "code": {"type": "keyword"},
                "description": {"type": "text"},
                "population": {"type": "long"},
                "foundation": {"type": "date"},
                "location": {"type": "geo_point"}
            }}}}
            """;

    /**
     * Crea il DAO con il mapper che avvolge i documenti nel nome radice.
     */
    public GPPlaceDAO() {
        super(GPPlace.class, new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE, WRAP_ROOT_VALUE_ENABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                WRITE_DATES_AS_TIMESTAMPS_DISABLE));
    }

    /**
     * @return {@link TypeMapping} il mapping, scritto in JSON
     */
    @Override
    protected TypeMapping preparePutMapping() {
        return TypeMapping.of(m -> m.withJson(new StringReader(MAPPING)));
    }
}
