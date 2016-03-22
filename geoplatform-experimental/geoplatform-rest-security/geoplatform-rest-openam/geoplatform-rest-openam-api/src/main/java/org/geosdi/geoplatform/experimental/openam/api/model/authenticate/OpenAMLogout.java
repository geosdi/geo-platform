package org.geosdi.geoplatform.experimental.openam.api.model.authenticate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMLogout implements IOpenAMLogout {

    private static final long serialVersionUID = 4691566338944247976L;
    //
    private String result;

    public OpenAMLogout() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getResult() {
        return this.result;
    }

    /**
     * @param theResult
     */
    @Override
    public void setResult(String theResult) {
        this.result = theResult;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " result = '" + result + '\'' +
                '}';
    }
}
