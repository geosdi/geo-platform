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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDTO.class, name = "UserDTO"),
        @JsonSubTypes.Type(value = ApplicationDTO.class, name = "ApplicationDTO")})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(value = {UserDTO.class, ApplicationDTO.class})
public abstract class ShortAccountDTO implements Serializable {

    private static final long serialVersionUID = 7240357958523018074L;
    //
    private Long id;
    private String organization;
    private boolean enabled;
    private boolean temporary;
    private boolean expired;
    private Date creationDate;
    private List<String> roles;
    private GPTrustedLevel trustedLevel;

    public ShortAccountDTO() {
    }

    public ShortAccountDTO(GPAccount account) {
        this.id = account.getId();
        this.organization = account.getOrganization().getName(); // TODO Possibile NullPointerException
        this.enabled = account.isEnabled();
        this.temporary = account.isAccountTemporary();
        this.expired = !account.isAccountNonExpired();
        this.creationDate = account.getCreationDate();

        if (account.getGPAuthorities() != null) {
            roles = new ArrayList<String>(account.getGPAuthorities().size());
            for (GPAuthority authority : account.getGPAuthorities()) {
                roles.add(authority.getAuthority());

                if (this.trustedLevel == null
                        || this.trustedLevel.ordinal() < authority.getTrustedLevel().ordinal()) {
                    this.trustedLevel = authority.getTrustedLevel();
                }
            }
        }
    }

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
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the temporary
     */
    public boolean isTemporary() {
        return temporary;
    }

    /**
     * @param temporary the temporary to set
     */
    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    /**
     * @return the expired
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * @param expired the expired to set
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * @return the trustedLevel
     */
    public GPTrustedLevel getTrustedLevel() {
        return trustedLevel;
    }

    /**
     * @param trustedLevel the trustedLevel to set
     */
    public void setTrustedLevel(GPTrustedLevel trustedLevel) {
        this.trustedLevel = trustedLevel;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("id=").append(id);
        str.append(", organization=").append(organization);
        str.append(", enabled=").append(enabled);
        str.append(", temporary=").append(temporary);
        str.append(", expired=").append(expired);
        str.append(", creationDate=").append(creationDate);
        if (roles != null) {
            str.append(", roles.size=").append(roles.size());
        } else {
            str.append(", roles=NULL");
        }
        str.append(", trustedLevel=").append(trustedLevel);
        return str.toString();
    }
}
