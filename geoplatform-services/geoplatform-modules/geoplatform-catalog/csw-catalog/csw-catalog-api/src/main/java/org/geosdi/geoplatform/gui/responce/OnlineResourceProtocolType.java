/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.responce;

/**
 * Some literal protocol of property OnlineResourceType.
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public enum OnlineResourceProtocolType {

    /**
     * OGC Web Map Service (ver 1.1.1).
     */
    OGC_WMS_1_1_1_HTTP_GET_MAP("OGC:WMS-1.1.1-http-get-map"),
    /**
     * OGC Web Map Service (ver 1.3.0).
     */
    OGC_WMS_1_3_0_HTTP_GET_MAP("OGC:WMS-1.3.0-http-get-map"),
    /**
     * File for download through FTP.
     */
    WWW_DOWNLOAD_1_0_FTP__DOWNLOAD("WWW:DOWNLOAD-1.0-ftp--download"),
    /**
     * File for download.
     */
    WWW_DOWNLOAD_1_0_HTTP__DOWNLOAD("WWW:DOWNLOAD-1.0-http--download"),
    /**
     * iCalendar (URL).
     */
    WWW_LINK_1_0_HTTP__ICAL("WWW:LINK-1.0-http--ical"),
    /**
     * Web address (URL).
     */
    WWW_LINK_1_0_HTTP__LINK("WWW:LINK-1.0-http--link"),
    /**
     * Partner web address (URL).
     */
    WWW_LINK_1_0_HTTP__PARTNERS("WWW:LINK-1.0-http--partners"),
    /**
     * Related link (URL).
     */
    WWW_LINK_1_0_HTTP__RELATED("WWW:LINK-1.0-http--related"),
    /**
     * RSS News feed (URL).
     */
    WWW_LINK_1_0_HTTP__RSS("WWW:LINK-1.0-http--rss"),
    /**
     * Showcase product (URL).
     */
    WWW_LINK_1_0_HTTP__SAMPLES("WWW:LINK-1.0-http--samples"),
    /**
     * OPeNDAP URL.
     */
    WWW_LINK_1_0_HTTP__OPENDAP("WWW:LINK-1.0-http--opendap");
    /**
     * Protocol of the OnlineResourceType.
     */
    private String protocol;

    private OnlineResourceProtocolType(String protocol) {
        this.protocol = protocol;
    }

    /**
     * TRUE if the protocol is suitable for WMS GetMap Request.
     */
    public static boolean isForWMSGetMapRequest(OnlineResourceProtocolType protocol) {
        if (protocol == OGC_WMS_1_1_1_HTTP_GET_MAP
                || protocol == OGC_WMS_1_3_0_HTTP_GET_MAP) {
            return true;
        }
        return false;
    }

    /**
     * TRUE if the protocol is suitable for Download.
     */
    public static boolean isForDownload(OnlineResourceProtocolType p) {
        return !OnlineResourceProtocolType.isForWMSGetMapRequest(p);
    }

    public static OnlineResourceProtocolType fromValue(String protocol) {
        for (OnlineResourceProtocolType p : OnlineResourceProtocolType.values()) {
            if (p.protocol.equalsIgnoreCase(protocol)) {
                return p;
            }
        }
        
        return null;
    }

    @Override
    public String toString() {
        return protocol;
    }
}
