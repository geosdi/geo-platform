/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class GPUserManageDetail extends GeoPlatformBeanModel
        implements IGPUserManageDetail {

    private static final long serialVersionUID = 53423038411470538L;
    //
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private GPRole authority;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *          the username to set
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
        super.set(GPUserManageDetailKeyValue.USERNAME.toString(), this.username);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     *          the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
        super.set(GPUserManageDetailKeyValue.NAME.toString(), this.name);
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
     *          the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *          the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the authority
     */
    @Override
    public GPRole getAuthority() {
        return authority;
    }

    /**
     * @param role
     *          the authority to set
     */
    @Override
    public void setAuthority(GPRole role) {
        this.authority = role;
        super.set(GPUserManageDetailKeyValue.AUTORITHY.toString(), this.authority.toStringUI());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPUserManageDetail {");
        str.append("id=").append(id);
        str.append(", name=").append(name);
        str.append(", username=").append(username);
        str.append(", email=").append(email);
        str.append(", authority=").append(authority);
        return str.append('}').toString();
    }
}
