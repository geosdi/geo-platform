package org.geosdi.geoplatform.experimental.el.query.rest.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPElasticSearchQueryExecutionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPElasticSearchQueryExecutionRequest implements IGPElasticSearchQueryExecutionRequest {

    private static final long serialVersionUID = -556033516816317816L;
    //
    private String queryID;
    private Map<String, Object> queryTemplateParameters;

    public GPElasticSearchQueryExecutionRequest() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getQueryID() {
        return this.queryID;
    }

    /**
     * @param theQueryID
     */
    @Override
    public void setQueryID(String theQueryID) {
        this.queryID = theQueryID;
    }

    /**
     * @return {@link Map<String, Object>}
     */
    @Override
    public Map<String, Object> getQueryTemplateParameters() {
        return this.queryTemplateParameters;
    }

    /**
     * @param theQueryTemplateParameters
     */
    @Override
    public <V> void setQueryTemplateParameters(Map<String, V> theQueryTemplateParameters) {
        this.queryTemplateParameters = (Map<String, Object>) theQueryTemplateParameters;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " queryID = '" + queryID + '\'' +
                ", queryTemplateParameters = " + queryTemplateParameters +
                '}';
    }
}
