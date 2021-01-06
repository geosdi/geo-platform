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
package org.geosdi.geoplatform.connector.geoserver.model.connection;

import com.google.common.collect.Maps;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
public class GPGeoserverConnectionParametersBuilder implements IGPGeoserverConnectionParametersBuilder {

    private static final long serialVersionUID = -5744680926370539432L;
    //
    private final Map<String, String> params = Maps.newLinkedHashMap();

    private GPGeoserverConnectionParametersBuilder() {
    }

    /**
     * @return
     */
    public static IGPGeoserverConnectionParametersBuilder connectionParametersBuilder() {
        return new GPGeoserverConnectionParametersBuilder();
    }

    /**
     * @param theParam
     * @param <Param>
     * @return
     * @throws Exception
     */
    @Override
    public <Param extends IGPGeoserverConnectionParam> IGPGeoserverConnectionParametersBuilder addParam(@Nonnull(when = NEVER) Param theParam) throws Exception {
        checkArgument(theParam != null, "The Parameter param must not be null.");
        checkNotNull((theParam.getKey() != null) && !(theParam.getKey().trim().isEmpty()), "The Parameter key must not be null or an empty string.");
        this.params.put(theParam.getKey(), theParam.getValue());
        return self();
    }

    /**
     * @param theParams
     * @return {@link IGPGeoserverConnectionParametersBuilder}
     * @throws Exception
     */
    @Override
    public <Param extends IGPGeoserverConnectionParam> IGPGeoserverConnectionParametersBuilder addParams(@Nonnull(when = NEVER) Param... theParams) throws Exception {
        checkArgument(theParams != null, "The Parameter params must not be null.");
        this.params.putAll(stream(theParams)
                .filter(Objects::nonNull)
                .filter(param -> (param.getKey() != null) && !(param.getKey().trim().isEmpty()))
                .collect(toMap(IGPGeoserverConnectionParam::getKey, IGPGeoserverConnectionParam::getValue, (v1, v2) -> v2, LinkedHashMap::new)));
        return self();
    }

    /**
     * @return {@link Map<String, String>}
     */
    @Override
    public Map<String, String> build() {
        return unmodifiableMap(this.params);
    }

    /**
     * @return {@link IGPGeoserverConnectionParametersBuilder}
     */
    protected IGPGeoserverConnectionParametersBuilder self() {
        return this;
    }
}