package org.geosdi.geoplatform.gui.client.model.projects;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPShortClientProject implements Serializable {

    private static final long serialVersionUID = 2745723380748842920L;
    //
    private Integer version;
    private Integer numberOfElements;

    public GPShortClientProject() {
    }

    public GPShortClientProject(Integer theVersion, Integer theNumberOfElements) {
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
        return "GPShortClientProject{" +
                "version=" + version +
                ", numberOfElements=" + numberOfElements +
                '}';
    }
}
