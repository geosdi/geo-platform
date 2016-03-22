package org.geosdi.geoplatform.experimental.openam.api.model.delete;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMDeleteResponse implements IOpenAMDeleteResponse {

    private static final long serialVersionUID = 4620850677044962318L;
    //
    private Boolean success;

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSuccess() {
        return this.success;
    }

    /**
     * @param theValue
     */
    @Override
    public void setSuccess(Boolean theValue) {
        this.success = theValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " success=" + success +
                '}';
    }
}
