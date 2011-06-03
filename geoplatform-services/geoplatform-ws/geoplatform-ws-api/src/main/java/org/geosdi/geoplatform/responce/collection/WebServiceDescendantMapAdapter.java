//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.responce.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.geosdi.geoplatform.responce.collection.WebServiceDescendantMapAdapter.DescendantMap;

/**
 *
 * @author Michele Santomauro
 * @email michele.santomauro@geosdi.org
 */
public class WebServiceDescendantMapAdapter extends XmlAdapter<DescendantMap, Map<Long, Integer>> {

    @Override
    public DescendantMap marshal(Map<Long, Integer> v) throws Exception {
        DescendantMap descendantMap = new DescendantMap();
        List<EntryDescendantMap> entries = descendantMap.getEntryDescendantMap();
        for (Map.Entry<Long, Integer> e : v.entrySet()) {
            entries.add(new EntryDescendantMap(e.getKey(), e.getValue()));
        }
        return descendantMap;
    }

    @Override
    public Map<Long, Integer> unmarshal(DescendantMap v) throws Exception {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (EntryDescendantMap e : v.getEntryDescendantMap()) {
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DescendantMap {

        @XmlElement(name = "entryDescendantMap", required = true, nillable = false)
        private final List<EntryDescendantMap> entryMap = new ArrayList<EntryDescendantMap>();

        public List<EntryDescendantMap> getEntryDescendantMap() {
            return this.entryMap;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EntryDescendantMap {

        @XmlAttribute(name = "key", required = true)
        private final Long key;
        @XmlValue
        private final Integer value;

        public EntryDescendantMap(Long key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public EntryDescendantMap() {
            this.key = null;
            this.value = null;
        }

        public Long getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }
}
