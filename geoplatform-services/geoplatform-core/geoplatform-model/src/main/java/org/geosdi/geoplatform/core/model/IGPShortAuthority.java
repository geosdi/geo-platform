package org.geosdi.geoplatform.core.model;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPShortAuthority extends GrantedAuthority {

    /**
     * @return {@link Long}
     */
    Long getId();

    /**
     * @return {@link String}
     */
    String getAuthority();

    /**
     * @return {@link GPTrustedLevel}
     */
    GPTrustedLevel getTrustedLevel();

    /**
     * @return {@link String}
     */
    String getAccountNaturalID();
}