/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GPPrintBean extends GeoPlatformBeanModel {

    public enum GPPrintEnumBean {

        GPPRINT_TITLE("title"), GPPRINT_MAP_TITLE("mapTitle"), GPPRINT_COMMENTS(
        "comments"), GPPRINT_DPI("dpi");
        //
        private String value;

        GPPrintEnumBean(String theValue) {
            this.value = theValue;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    //
    private String title;
    private String mapTitle;
    private String comments;
    private DPI dpi;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
        set(GPPrintEnumBean.GPPRINT_COMMENTS.toString(), this.comments);
    }

    public DPI getDpi() {
        return dpi;
    }

    public void setDpi(DPI dpi) {
        this.dpi = dpi;
        set(GPPrintEnumBean.GPPRINT_DPI.toString(), this.dpi);
    }

    public String getMapTitle() {
        return mapTitle;
    }

    public void setMapTitle(String mapTitle) {
        this.mapTitle = mapTitle;
        set(GPPrintEnumBean.GPPRINT_MAP_TITLE.toString(), this.mapTitle);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        set(GPPrintEnumBean.GPPRINT_TITLE.toString(), this.title);
    }

    @Override
    public String toString() {
        return "GPPrintBean{" + "title=" + title + ", mapTitle="
                + mapTitle + ", comments=" + comments
                + ", dpi=" + dpi + '}';
    }
    
    public void reset() {
        this.setTitle(null);
        this.setMapTitle(null);
        this.setComments(null);
        this.setDpi(null);
    }
}
