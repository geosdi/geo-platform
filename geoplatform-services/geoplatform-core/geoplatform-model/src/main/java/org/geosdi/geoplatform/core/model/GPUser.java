/**
 * 
 */
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author giuseppe
 * 
 */
@Entity(name = "User")
@Table(name = "gp_user")
@XmlRootElement(name = "User")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
public class GPUser implements Serializable, UserDetails {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1354980934257649175L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_USER_SEQ")
    @SequenceGenerator(name = "GP_USER_SEQ", sequenceName = "GP_USER_SEQ")
    @Column
    private long id;
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;
    /**
     * since memberService integration
     */
    @Column(name = "user_password")
    private String password;
    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    /**
     * since memberService integration
     */
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = false;
    @Column(name = "send_email", nullable = false)
    private boolean sendEmail = false;
    @Column(name = "accountNonExpired")
    private Boolean accountNonExpired;
    @Column(name = "accountNonLocked")
    private Boolean accountNonLocked;
    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired;
    //for joing the tables (many-to-many)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
    joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "authorityId")})
    private Collection<GPAuthority> gpAuthorities;

    /**
     * Default constructor
     */
    public GPUser() {
        super();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the enabled
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the sendEmail
     */
    public boolean isSendEmail() {
        return sendEmail;
    }

    /**
     * @param sendEmail
     *            the sendEmail to set
     */
    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public String toString() {
        return "GPUser{" + "id=" + id + "username=" + username
                + "password=" + password + "emailAddress=" + emailAddress
                + "enabled=" + enabled + "sendEmail=" + sendEmail
                + "accountNonExpired=" + accountNonExpired + "accountNonLocked="
                + accountNonLocked + "credentialsNonExpired="
                + credentialsNonExpired + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPUser other = (GPUser) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
        for (GrantedAuthority ga : gpAuthorities) {
            auth.add(ga);
        }
        return auth;
    }

    /**
     * @return the gpAuthorities
     */
    public Collection<GPAuthority> getGpAuthorities() {
        return gpAuthorities;
    }

    /**
     * @param gpAuthorities the gpAuthorities to set
     */
    public void setGpAuthorities(List<GPAuthority> gpAuthorities) {
        this.gpAuthorities = gpAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return credentialsNonExpired;
    }
}
