package org.geosdi.geoplatform.support.jackson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"args", "headers", "origin", "url"})
public class SimpleBean implements Serializable {

    private static final long serialVersionUID = -3655283512529540786L;
    //
    @JsonProperty(value = "args")
    private Map<String, String> arguments;
    private Map<String, String> headers;
    private String origin;
    private String url;

    public SimpleBean() {
    }

    /**
     * @return {@link Map<String, String>}
     */
    public Map<String, String> getArguments() {
        return arguments;
    }

    /**
     * @param arguments
     */
    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * @return {@link Map<String, String>}
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * @param headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * @return {@link String}
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return {@link String}
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "arguments = " + arguments +
                ", headers = " + headers +
                ", origin = '" + origin + '\'' +
                ", url = '" + url + '\'' +
                '}';
    }
}
