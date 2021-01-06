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
package org.geosdi.geoplatform.experimental.openam.support.connector.request.users;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.DELETE_USER;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMDeleteUserRequest")
public class OpenAMDeleteUserRequest implements BaseOpenAMRequest<OpenAMDeleteUserRequest> {

    private static final long serialVersionUID = 3606300607189245748L;
    //
    @Value("openAMConnectorConfigurator{openam.users.request.path:@null}")
    private String path;
    private ThreadLocal<String> extraParamPath = new ThreadLocal<>();

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return ((this.extraParamPath == null) || (this.extraParamPath.get().isEmpty()))
                ? this.path : this.path.concat(this.extraParamPath.get());
    }

    /**
     * @param theExtraParamPath
     */
    @Override
    public OpenAMDeleteUserRequest setExtraPathParam(String... theExtraParamPath) {
        if ((theExtraParamPath != null) && (theExtraParamPath.length > 0)) {
            this.extraParamPath.set(Arrays
                    .stream(theExtraParamPath)
                    .filter(p -> ((p != null) && !(p.isEmpty())))
                    .collect(Collectors.joining("/")));
        }
        return self();
    }

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return DELETE_USER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                ", extraParamPath = '" + extraParamPath.get() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }
}
