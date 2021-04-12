package org.geosdi.geoplatform.gui.model.logo;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;



/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPAttributionLogoURLBean extends GeoPlatformBeanModel implements IGPAttributionLogoURLBean{

    private static final long serialVersionUID = 2654885508494348329L;
    //
    private String format;
    private String onlineResource;
    private int height;
    private int width;

    public GPAttributionLogoURLBean() {
    }

    /**
     *
     * @return
     */
    @Override
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format
     */
    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     *
     * @return
     */
    @Override
    public String getOnlineResource() {
        return onlineResource;
    }

    /**
     *
     * @param onlineResource
     */
    @Override
    public void setOnlineResource(String onlineResource) {
        this.onlineResource = onlineResource;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "GPAttributionLogoURLBean {" +
                "format = " + this.format +
                ", onlineResource = " + this.onlineResource +
                ", height = " + this.height +
                ", width = " + this.width +
                "}";
    }
}
