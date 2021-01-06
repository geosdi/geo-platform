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
package org.geosdi.geoplatform.gui.model.user;

import org.geosdi.geoplatform.gui.global.security.IGPUserSimpleDetail;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPSimpleUser extends GeoPlatformBeanModel implements IGPUserSimpleDetail {

    private static final long serialVersionUID = 6888698454407152018L;
    //
    private Long id; // For performance purpose: used for equals() and hashCode() methods

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
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
        return super.get(GPSimpleUserKeyValue.USERNAME.toString());
    }

    /**
     * @param username the username to set
     */
    @Override
    public void setUsername(String username) {
        super.set(GPSimpleUserKeyValue.USERNAME.toString(), username);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return super.get(GPSimpleUserKeyValue.NAME.toString());
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        super.set(GPSimpleUserKeyValue.NAME.toString(), name);
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return super.get(GPSimpleUserKeyValue.EMAIL.toString());
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        super.set(GPSimpleUserKeyValue.EMAIL.toString(), email);
    }

    /**
     * @return the organization
     */
    @Override
    public String getOrganization() {
        return super.get(GPSimpleUserKeyValue.ORGANIZATION.toString());
    }

    /**
     * @param organization the organization to set
     */
    @Override
    public void setOrganization(String organization) {
        super.set(GPSimpleUserKeyValue.ORGANIZATION.toString(), organization);
    }

    /**
     * @return the authority
     */
    @Override
    public String getAuthority() {
        return super.get(GPSimpleUserKeyValue.AUTORITHY.toString());
    }

    /**
     * @param role the authority to set
     */
    @Override
    public void setAuthority(String role) {
        super.set(GPSimpleUserKeyValue.AUTORITHY.toString(), role);
    }

    /**
     * @return the trustedLevel
     */
    @Override
    public GPTrustedLevel getTrustedLevel() {
        return super.get(GPSimpleUserKeyValue.TRUSTED_LEVEL.toString());
    }

    /**
     * @param trustedLevel the trustedLevel to set
     */
    @Override
    public void setTrustedLevel(GPTrustedLevel trustedLevel) {
        super.set(GPSimpleUserKeyValue.TRUSTED_LEVEL.toString(), trustedLevel);
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
        final GPSimpleUser other = (GPSimpleUser) obj;
        if (id == null || !this.id.equals(other.id)) {
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
}
