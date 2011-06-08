/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public enum ImageFormat {

    JPEG("image/jpeg"), PNG("image/png");
    private String imageFormat;

    ImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public String getValue() {
        return imageFormat;
    }
}
