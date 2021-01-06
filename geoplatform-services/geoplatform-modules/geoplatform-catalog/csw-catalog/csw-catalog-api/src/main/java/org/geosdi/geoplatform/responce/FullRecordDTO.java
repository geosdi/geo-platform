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
package org.geosdi.geoplatform.responce;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.geosdi.geoplatform.gui.responce.OnlineResourceProtocolType;
import org.geosdi.geoplatform.gui.responce.URIDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.responce.adapter.UriMapAdapter;

/**
 * Full Record DTO for CSW request.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "FullRecordDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class FullRecordDTO extends AbstractRecordDTO {

    private static final long serialVersionUID = -4843440136860067550L;
    //
    private BBox bBox;
    private String crs;
    //
    @XmlElement(name = "uri")
    @XmlJavaTypeAdapter(UriMapAdapter.class)
    private Map<OnlineResourceProtocolType, URIDTO> uriMap = new HashMap<OnlineResourceProtocolType, URIDTO>();

    public BBox getBBox() {
        return bBox;
    }

    public void setBBox(BBox bBox) {
        this.bBox = bBox;
    }

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public Map<OnlineResourceProtocolType, URIDTO> getUriMap() {
        return uriMap;
    }

    public void setUriMap(Map<OnlineResourceProtocolType, URIDTO> uriMap) {
        this.uriMap = uriMap;
    }

    /**
     * TODO FIX ME : Put in OnlineResourceProtocolType all Protocol Type to
     * avoid null values for keys
     *
     * @param uri
     */
    public void addUri(URIDTO uri) {
        OnlineResourceProtocolType protocolType = OnlineResourceProtocolType
                .fromValue(uri.getProtocol());
        if (protocolType != null) {
            this.uriMap.put(protocolType, uri);
        }
    }

    public URIDTO getURIDtoForWMSGetCapabilities() {
        return (this.uriMap.containsKey(OnlineResourceProtocolType.OGC_WMS_1_1_1_HTTP_GET_CAPABILITIES)
                ? uriMap.get(OnlineResourceProtocolType.OGC_WMS_1_1_1_HTTP_GET_CAPABILITIES)
                : uriMap.get(OnlineResourceProtocolType.OGC_WMS_1_3_0_HTTP_GET_CAPABILITIES));
    }

    @Override
    public String toString() {
        return super.toString()
                + ", bBox = " + bBox
                + ", crs = " + crs
                + ", uriMap = " + uriMap + '}';
    }

}
