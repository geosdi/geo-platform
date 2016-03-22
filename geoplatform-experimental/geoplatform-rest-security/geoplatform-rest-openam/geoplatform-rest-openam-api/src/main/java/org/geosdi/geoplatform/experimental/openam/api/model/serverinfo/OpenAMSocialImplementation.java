package org.geosdi.geoplatform.experimental.openam.api.model.serverinfo;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMSocialImplementation implements IOpenAMSocialImplementation {

    private static final long serialVersionUID = 8894194481849660528L;
    //
    private String iconPath;
    private String authnChain;
    private String displayName;
    private Boolean valid;

    public OpenAMSocialImplementation() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getIconPath() {
        return this.iconPath;
    }

    /**
     * @param theIconPath
     */
    @Override
    public void setIconPath(String theIconPath) {
        this.iconPath = theIconPath;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getAuthnChain() {
        return this.authnChain;
    }

    /**
     * @param theAuthnChain
     */
    @Override
    public void setAuthnChain(String theAuthnChain) {
        this.authnChain = theAuthnChain;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @param theDisplayName
     */
    @Override
    public void setDisplayName(String theDisplayName) {
        this.displayName = theDisplayName;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * @param theValid
     */
    @Override
    public void setValid(Boolean theValid) {
        this.valid = theValid;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " iconPath = '" + iconPath + '\'' +
                ", authnChain = '" + authnChain + '\'' +
                ", displayName = '" + displayName + '\'' +
                ", valid = " + valid +
                '}';
    }
}
