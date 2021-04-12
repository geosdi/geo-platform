package org.geosdi.geoplatform.gui.model.logo;

import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPAttributionLogoURLBean extends Serializable {

    enum GPAttributionLogoURLKeyValue {
        HEIGHT("height"),
        WIDTH("width"),
        FORMAT("format"),
        ON_LINE_RESOURCE("onlineResource");

        private final String value;

        /**
         * @param theValue
         */
        GPAttributionLogoURLKeyValue(String theValue) {
            this.value = theValue;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     *
     * @return {@link Integer}
     */
    int getHeight();

    /**
     *
     * @param height
     */
    void setHeight(int height);

    /**
     *
     * @return {@link Integer}
     */
    int getWidth();

    /**
     *
     * @param width
     */
    void setWidth(int width);

    /**
     *
     * @return {@link String}
     */
    String getFormat();

    /**
     *
     * @param format
     */
    void setFormat(String format);

    /**
     *
     * @return {@link String}
     */
    String getOnlineResource();

    /**
     *
     * @param onLineResource
     */
    void setOnlineResource(String onLineResource);

}
