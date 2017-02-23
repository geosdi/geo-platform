package org.geosdi.geoplatform.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShortProjectDTO implements Serializable {

    private static final long serialVersionUID = -2692298561415328918L;
    //
    private Integer version;
    private Integer numberOfElements;

    public ShortProjectDTO() {
    }

    public ShortProjectDTO(Integer theVersion, Integer theNumberOfElements) {
        this.version = theVersion;
        this.numberOfElements = theNumberOfElements;
    }

    /**
     * @return {@link Integer}
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return {@link Integer}
     */
    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements
     */
    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                " version = " + version +
                ", numberOfElements = " + numberOfElements +
                '}';
    }
}
