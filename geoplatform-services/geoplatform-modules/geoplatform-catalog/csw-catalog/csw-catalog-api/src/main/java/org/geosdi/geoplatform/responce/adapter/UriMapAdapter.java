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
package org.geosdi.geoplatform.responce.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.geosdi.geoplatform.gui.responce.OnlineResourceProtocolType;
import org.geosdi.geoplatform.gui.responce.URIDTO;
import org.geosdi.geoplatform.responce.adapter.UriMapAdapter.EntryUriMap;
import org.geosdi.geoplatform.responce.adapter.UriMapAdapter.UriMap;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UriMapAdapter extends XmlAdapter<UriMap, Map<OnlineResourceProtocolType, URIDTO>> {

    @Override
    public Map<OnlineResourceProtocolType, URIDTO> unmarshal(UriMap v) throws Exception {
        Map<OnlineResourceProtocolType, URIDTO> map = new HashMap<OnlineResourceProtocolType, URIDTO>();
        for (EntryUriMap element : v.getEntryUriMap()) {
            map.put(element.getKey(), element.getValue());
        }
        return map;
    }

    @Override
    public UriMap marshal(Map<OnlineResourceProtocolType, URIDTO> v) throws Exception {
        UriMap uriMap = new UriMap();
        List<EntryUriMap> entries = uriMap.getEntryUriMap();
        for (Map.Entry<OnlineResourceProtocolType, URIDTO> e : v.entrySet()) {
            entries.add(new EntryUriMap(
                    e.getKey(), e.getValue()));
        }
        return uriMap;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class UriMap {

        @XmlElement(name = "EntryUriMap", required = true, nillable = false)
        private final List<EntryUriMap> entryMap = new ArrayList<EntryUriMap>();

        public List<EntryUriMap> getEntryUriMap() {
            return this.entryMap;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EntryUriMap {

        @XmlAttribute(name = "key", required = true)
        private final OnlineResourceProtocolType key;
        @XmlElement(name = "value", required = true, nillable = false)
        private final URIDTO value;

        public EntryUriMap(OnlineResourceProtocolType key, URIDTO value) {
            this.key = key;
            this.value = value;
        }

        public EntryUriMap() {
            this.key = null;
            this.value = null;
        }

        public OnlineResourceProtocolType getKey() {
            return key;
        }

        public URIDTO getValue() {
            return value;
        }
    }
}
