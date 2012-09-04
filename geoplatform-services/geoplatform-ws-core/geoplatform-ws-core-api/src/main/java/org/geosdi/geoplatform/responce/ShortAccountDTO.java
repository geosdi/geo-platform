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
package org.geosdi.geoplatform.responce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(value = {UserDTO.class, ApplicationDTO.class})
public abstract class ShortAccountDTO {

    private Long id;
    private String organization;
    private boolean enabled;
    private boolean temporary;
    private boolean expired;
    private Date creationDate;
    private List<String> roles;

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
        return str.toString();
    }

    /**
     * TODO Each entity class will call the appropriate DTO for the conversion.
     * E.g. GPUser will have the method public UserDTO getDTO()
     */
    public static List<ShortAccountDTO> convertToShortAccountDTOList(List<GPAccount> accounts) {
        List<ShortAccountDTO> accountsDTO = new ArrayList<ShortAccountDTO>(accounts.size());

        for (GPAccount account : accounts) {
            ShortAccountDTO shortAccount = null;
            if (account instanceof GPUser) {
                shortAccount = new UserDTO((GPUser) account);
            } else if (account instanceof GPApplication) {
                shortAccount = new ApplicationDTO((GPApplication) account);
            }
            accountsDTO.add(shortAccount);
        }

        return accountsDTO;
    }
}
