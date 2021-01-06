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
package org.geosdi.geoplatform.experimental.openam.support.connector.request.parameter.mediator;

import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.mediator.IRequestParameterMediator;

import java.util.EnumMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class RequestParameterMediator implements IRequestParameterMediator {

    private final EnumMap<RequestParameter.RequestParameterType, RequestParameter> store;

    public RequestParameterMediator(EnumMap<RequestParameter.RequestParameterType, RequestParameter> theStore) {
        this.store = theStore;
    }

    /**
     * @param type
     * @return {@link RequestParameter}
     * @throws Exception
     */
    @Override
    public RequestParameter getRequest(RequestParameter.RequestParameterType type) throws Exception {
        checkNotNull(type, "The Parameter Type must not be null.");
        return this.store.get(type);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " store = " + store +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNotNull(this.store, "The OpenAmRequestParameter Store must not be null.");
    }
}
