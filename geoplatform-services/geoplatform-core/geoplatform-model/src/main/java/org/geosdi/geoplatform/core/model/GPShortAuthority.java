package org.geosdi.geoplatform.core.model;

import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "ShortAuthority")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPShortAuthority implements IGPShortAuthority {

    private static final long serialVersionUID = -515147481078251895L;
    //
    private final Long id;
    private final String authority;
    private final GPTrustedLevel trustedLevel;
    private final String accountNaturalID;

    public GPShortAuthority(Long theID, String theAuthority, GPTrustedLevel theTrustedLevel, String theAccountNaturalID) {
        this.id = theID;
        this.authority = theAuthority;
        this.trustedLevel = theTrustedLevel;
        this.accountNaturalID = theAccountNaturalID;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getAuthority() {
        return this.authority;
    }

    /**
     * @return {@link GPTrustedLevel}
     */
    @Override
    public GPTrustedLevel getTrustedLevel() {
        return this.trustedLevel;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getAccountNaturalID() {
        return this.accountNaturalID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "  id = " + id +
                ", authority = '" + authority + '\'' +
                ", trustedLevel = " + trustedLevel +
                ", accountNaturalID = '" + accountNaturalID + '\'' +
                '}';
    }
}
