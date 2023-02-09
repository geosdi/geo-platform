/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

import static jakarta.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI Group
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @todo Analyze deletion of accountNaturalID field or account field.
 */
//@XmlRootElement(name = "Authority")
@XmlAccessorType(FIELD)
@Entity(name = "Authority")
@Table(name = "gp_authority", indexes = {
        @Index(columnList = "account_natural_id", name = "AUTHORITY_ACCOUNT_NATURAL_ID_INDEX")
})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "authority")
public class GPAuthority implements GrantedAuthority, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5005299814060260152L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gp_autorithy_generator")
    @GenericGenerator(name = "gp_autorithy_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "GP_AUTHORITY_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private Long id;
    //
    @Column(nullable = false)
    private String authority;
    //
    @Column(name = "trusted_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private GPTrustedLevel trustedLevel;
    //
    @Column(name = "account_natural_id", nullable = false)
    private String accountNaturalID;
    //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPAccount account;

    public GPAuthority() {
    }

    public GPAuthority(Long id, String authority, GPTrustedLevel trustedLevel, String accountNaturalID) {
        this.id = id;
        this.authority = authority;
        this.trustedLevel = trustedLevel;
        this.accountNaturalID = accountNaturalID;
    }

    public GPAuthority(GPAccount account, GPTrustedLevel trustedLevel, String authority) {
        this.account = account;
        this.accountNaturalID = account.getNaturalID();
        this.trustedLevel = trustedLevel;
        this.authority = authority;
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
     * @return authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
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
     * @return the accountNaturalID
     */
    public String getAccountNaturalID() {
        return accountNaturalID;
    }

    /**
     * @param accountNaturalID the accountNaturalID to set
     */
    public void setAccountNaturalID(String accountNaturalID) {
        this.accountNaturalID = accountNaturalID;
    }

    /**
     * @return the account
     */
    public GPAccount getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(GPAccount account) {
        this.account = account;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("id=").append(id);
        str.append(", authority=").append(authority);
        str.append(", trustedLevel=").append(trustedLevel);
        str.append(", accountNaturalID=").append(accountNaturalID);
        str.append(", account=").append(account);
        return str.append("}").toString();
    }
}
