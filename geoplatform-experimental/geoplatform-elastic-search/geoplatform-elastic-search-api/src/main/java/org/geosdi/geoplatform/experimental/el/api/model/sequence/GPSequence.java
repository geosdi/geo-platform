package org.geosdi.geoplatform.experimental.el.api.model.sequence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPSequence")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPSequence implements IGPSequence {

    private String id;
    private Long version;

    public GPSequence() {
    }

    public GPSequence(String id) {
        this.id = id;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getSequenceId() {
        return this.id;
    }

    /**
     * @param theID
     */
    @Override
    public void setSequenceID(String theID) {
        this.id = theID;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(Long theVersion) {
        this.version = theVersion;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " id = '" + id + '\'' +
                '}';
    }
}
