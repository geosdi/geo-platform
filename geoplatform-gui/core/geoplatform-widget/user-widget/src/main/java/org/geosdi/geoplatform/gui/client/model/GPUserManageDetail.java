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
package org.geosdi.geoplatform.gui.client.model;

import java.util.Date;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPUserManageDetail extends GPSimpleUser
        implements IGPUserManageDetail {

    private static final long serialVersionUID = 53423038411470538L;
    //
    private String password; // For manual binding
    private Date creationDate; // For manual operation
    private boolean expired; // For manual binding

    public enum GPUserManageDetailKeyValue {

        PASSWORD,
        ENABLED,
        TEMPORARY;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    @Override
    public boolean isEnabled() {
        Boolean enabled = super.get(GPUserManageDetailKeyValue.ENABLED.toString());
        if (enabled == null) {
            return false;
        }
        return enabled.booleanValue();
    }

    /**
     * @param enabled the enabled to set
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.set(GPUserManageDetailKeyValue.ENABLED.toString(), enabled);
    }

    /**
     * @return the creationDate
     */
    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
     * @param temporary the temporary to set
     */
    @Override
    public void setTemporary(boolean temporary) {
        super.set(GPUserManageDetailKeyValue.TEMPORARY.toString(), temporary);
    }

    /**
     * @return the expired
     */
    @Override
    public boolean isExpired() {
        return expired;
    }

    /**
     * @param expired the expired to set
     */
    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPUserManageDetail {");
        str.append("id=").append(getId());
        str.append(", name=").append(getName());
        str.append(", email=").append(getEmail());
        str.append(", username=").append(getUsername());
        str.append(", password=").append(password);
        str.append(", authority=").append(getAuthority());
        str.append(", enabled=").append(isEnabled());
        str.append(", creationDate=").append(creationDate);
        str.append(", temporary=").append(isTemporary());
        str.append(", expired=").append(expired);
        str.append(", organization=").append(getOrganization());
        str.append(", trustedLevel=").append(getTrustedLevel());
        return str.append('}').toString();
    }
}
