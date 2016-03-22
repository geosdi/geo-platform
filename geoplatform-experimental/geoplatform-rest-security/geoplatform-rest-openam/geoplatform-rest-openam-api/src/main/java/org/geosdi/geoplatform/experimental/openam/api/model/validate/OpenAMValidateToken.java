package org.geosdi.geoplatform.experimental.openam.api.model.validate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMValidateToken implements IOpenAMValidateToken {

    private static final long serialVersionUID = 5144429319711257646L;
    //
    private boolean valid;
    private String uid;
    private String realm;

    public OpenAMValidateToken() {
    }

    /**
     * @return {@link String}
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param value
     */
    public void setValid(boolean value) {
        this.valid = value;
    }

    /**
     * @return {@link String}
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return {@link String}
     */
    public String getRealm() {
        return realm;
    }

    /**
     * @param realm
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  valid = " + valid +
                ", uid = '" + uid + '\'' +
                ", realm = '" + realm + '\'' +
                '}';
    }
}
