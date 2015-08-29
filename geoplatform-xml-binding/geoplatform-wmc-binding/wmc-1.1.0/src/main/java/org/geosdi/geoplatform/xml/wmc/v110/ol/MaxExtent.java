package org.geosdi.geoplatform.xml.wmc.v110.ol;

import javax.xml.bind.annotation.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "MaxExtent")
@XmlAccessorType(XmlAccessType.FIELD)
public class MaxExtent extends ExtensionElement {

    private static final long serialVersionUID = -3565658413070490110L;
    //
    @XmlAttribute
    private double minx;
    @XmlAttribute
    private double miny;
    @XmlAttribute
    private double maxx;
    @XmlAttribute
    private double maxy;

    public MaxExtent() {
    }


    public double getMinx() {
        return minx;
    }

    public void setMinx(double minx) {
        this.minx = minx;
    }

    public double getMiny() {
        return miny;
    }

    public void setMiny(double miny) {
        this.miny = miny;
    }

    public double getMaxx() {
        return maxx;
    }

    public void setMaxx(double maxx) {
        this.maxx = maxx;
    }

    public double getMaxy() {
        return maxy;
    }

    public void setMaxy(double maxy) {
        this.maxy = maxy;
    }

    @XmlTransient
    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.MAX_EXTENT;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "minx=" + minx +
                ", miny=" + miny +
                ", maxx=" + maxx +
                ", maxy=" + maxy +
                '}';
    }
}
