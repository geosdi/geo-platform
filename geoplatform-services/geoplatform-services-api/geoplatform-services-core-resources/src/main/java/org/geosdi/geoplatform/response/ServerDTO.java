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
package org.geosdi.geoplatform.response;

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@XmlRootElement(name = "ServerDTO")
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "serverUrl", "name", "alias", "layerList", "organization", "serverProtected", "username", "password", "proxy"})
public class ServerDTO implements Serializable {

    private static final long serialVersionUID = -1916994804312224037L;

    private Long id;
    private String serverUrl;
    private String name;
    private String alias;
    private String organization;
    private boolean serverProtected;
    private String username;
    private String password;
    private boolean proxy;
    //
    @XmlElementRefs(value = {@XmlElementRef(name = "rasterLayerDTO", type = RasterLayerDTO.class),
            @XmlElementRef(name = "vectorLayerDTO", type = VectorLayerDTO.class)})
    private List<? extends ShortLayerDTO> layerList;

    public ServerDTO() {
        super();
    }

    /**
     * @param server
     */
    public ServerDTO(@Nonnull(when = NEVER) GeoPlatformServer server) {
        checkArgument(server != null, "The Parameter server must not be null.");
        this.id = server.getId();
        this.serverUrl = server.getServerUrl();
        this.name = server.getName();
        this.alias = server.getAliasName();
        this.proxy = server.isProxy();
        this.serverProtected = server.isProtected();
        if(server.isProtected()) {
            this.username = server.getAuthServer().getUsername();
            this.password = server.getAuthServer().getPassword();
        }

    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " {"
                + "id = " + id
                + ", serverUrl = " + serverUrl
                + ", alias = " + alias
                + ", name = " + name
                + ", organization = " + organization
                + ", serverProtected = " + serverProtected
                + ", username = " + username
                + ", password = " + password
                + ", proxy = " + proxy
                + ", layerList = " + layerList
                + '}';
    }
}