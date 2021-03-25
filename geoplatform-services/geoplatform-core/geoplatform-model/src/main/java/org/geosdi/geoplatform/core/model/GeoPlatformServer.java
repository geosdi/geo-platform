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
package org.geosdi.geoplatform.core.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
//@XmlRootElement(name = "Server")
@Getter
@Setter
@Entity
@Table(name = "gp_server", indexes = {
        @Index(columnList = "server_url", name = "SERVER_URL_INDEX"),
        @Index(columnList = "name", name = "SERVER_NAME_INDEX")})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "server")
public class GeoPlatformServer implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8546115928654105043L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_SERVER_SEQ")
    @SequenceGenerator(name = "GP_SERVER_SEQ", sequenceName = "GP_SERVER_SEQ")
    private Long id;
    //
    @Column(name = "server_url", nullable = false, unique = true)
    private String serverUrl;
    //
    @Column
    private String name;
    //
    @Column(name = "alias_name")
    private String aliasName;
    //
    @Column
    private String title;
    //
    @Column(name = "abstract", columnDefinition = "TEXT")
    private String abstractServer;
    //
    @Column(name = "capability_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GPCapabilityType serverType;
    //
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPOrganization organization;
    @Embedded
    private GPAuthServer authServer;
    @Column
    private boolean proxy;

    public GeoPlatformServer() {
    }

    public GeoPlatformServer(Long id, String serverUrl, String name, String aliasName, String title,
            String abstractServer, GPCapabilityType serverType, GPOrganization organization) {
        this.id = id;
        this.serverUrl = serverUrl;
        this.name = name;
        this.aliasName = aliasName;
        this.title = title;
        this.abstractServer = abstractServer;
        this.serverType = serverType;
        this.organization = organization;
    }

    /**
     *
     * @return
     */
    public boolean isProtected() {
        return this.authServer != null && (this.authServer.getPassword() != null && !this.authServer.getPassword().isEmpty())
                && (this.authServer.getUsername() != null && !this.authServer.getUsername().isEmpty());
    }


    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GeoPlatformServer {");
        str.append("id=").append(id);
        str.append(", serverType=").append(serverType);
        str.append(", serverUrl=").append(serverUrl);
        str.append(", name=").append(name);
        str.append(", aliasName=").append(aliasName);
        str.append(", title=").append(title);
        str.append(", abstractServer=").append(abstractServer);
        str.append(", organization=").append(organization);
        str.append(", authServer=").append(authServer);
        str.append(", proxy=").append(proxy);
        return str.append("}").toString();
    }
}