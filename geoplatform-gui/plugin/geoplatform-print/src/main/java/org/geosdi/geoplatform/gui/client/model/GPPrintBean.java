/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
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

    public GPPrintBean() {
        this.setComments("-");
        this.setMapTitle("-");
    }

    public String getComments() {
        return comments;
    }

    public final void setComments(String comments) {
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

    public final void setMapTitle(String mapTitle) {
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
        this.setMapTitle("-");
        this.setComments("-");
        this.setDpi(null);
    }
}
