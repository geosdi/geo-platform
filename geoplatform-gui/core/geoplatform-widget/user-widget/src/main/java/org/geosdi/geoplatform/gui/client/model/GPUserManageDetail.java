/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
    private String password;
    private String authority;

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *          the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return super.get(GPUserManageDetailKeyValue.USERNAME.toString());
    }

    /**
     * @param username
     *          the username to set
     */
    @Override
    public void setUsername(String username) {
        super.set(GPUserManageDetailKeyValue.USERNAME.toString(), username);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return super.get(GPUserManageDetailKeyValue.NAME.toString());
    }

    /**
     * @param name
     *          the name to set
     */
    @Override
    public void setName(String name) {
        super.set(GPUserManageDetailKeyValue.NAME.toString(), name);
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return super.get(GPUserManageDetailKeyValue.EMAIL.toString());
    }

    /**
     * @param email 
     *          the email to set
     */
    @Override
    public void setEmail(String email) {
        super.set(GPUserManageDetailKeyValue.EMAIL.toString(), email);
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
    public String getAuthority() {
        return authority;
    }

    /**
     * @param role
     *          the authority to set
     */
    @Override
    public void setAuthority(String role) {
        this.authority = role;
        super.set(GPUserManageDetailKeyValue.AUTORITHY.toString(), this.authority.toString());
    }

    /**
     * @return the temporary
     */
    @Override
    public boolean isTemporary() {
        Boolean temporary = super.get(GPUserManageDetailKeyValue.TEMPORARY.toString());
        if (temporary == null) {
            return false;
        }
        return temporary.booleanValue();
    }

    /**
     * @param temporary
     *          the temporary to set
     */
    @Override
    public void setTemporary(boolean temporary) {
        super.set(GPUserManageDetailKeyValue.TEMPORARY.toString(), temporary);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPUserManageDetail other = (GPUserManageDetail) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPUserManageDetail {");
        str.append("id=").append(id);
        str.append(", name=").append(getName());
        str.append(", email=").append(getEmail());
        str.append(", username=").append(getUsername());
        str.append(", password=").append(password);
        str.append(", authority=").append(authority);
        str.append(", temporary=").append(isTemporary());
        return str.append('}').toString();
    }
}
