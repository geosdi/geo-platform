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
package org.geosdi.geoplatform.gui.client.model;

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.model.IGeoPlatformFinder;

/**
 * @author giuseppe
 * 
 */
public class FinderBean extends GeoPlatformBeanModel implements
        IGeoPlatformFinder {

    /**
     *
     */
    private static final long serialVersionUID = -8014889849325486781L;
    //
    private String uuid;
    private String title;
    private String abstractValue;
    private String keywordValues = "";
    private BboxClientInfo bbox;
    private List<MetadataOnlineResourceBean> metadataOnlineResourceBeanList = new ArrayList<MetadataOnlineResourceBean>();

    public MetadataOnlineResourceBean makeInstance() {
        return new MetadataOnlineResourceBean();
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the abstractValue
     */
    public String getAbstractValue() {
        return abstractValue;
    }

    /**
     * @param abstractValue the abstractValue to set
     */
    public void setAbstractValue(String abstractValue) {
        this.abstractValue = abstractValue;
    }

    /**
     * @return the keywordValues
     */
    public String getKeywordValues() {
        return keywordValues;
    }

    /**
     * @param keywordValues the keywordValues to set
     */
    public void setKeywordValues(String keywordValues) {
        this.keywordValues = keywordValues;
    }

    /**
     * @return the bbox
     */
    public BboxClientInfo getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    public void setBbox(BboxClientInfo bbox) {
        this.bbox = bbox;
    }

    /**
     * @return the metadataOnlineResourceBeanList
     */
    public List<MetadataOnlineResourceBean> getMetadataOnlineResourceBeanList() {
        return metadataOnlineResourceBeanList;
    }

    /**
     * @param metadataOnlineResourceBeanList the metadataOnlineResourceBeanList to set
     */
    public void setMetadataOnlineResourceBeanList(List<MetadataOnlineResourceBean> metadataOnlineResourceBeanList) {
        this.metadataOnlineResourceBeanList = metadataOnlineResourceBeanList;
    }

    @Override
    public String toString() {
        return "FinderBean{" + "uuid=" + uuid + ", title=" + title + ", abstractValue=" + abstractValue + ", keywordValues=" + keywordValues + ", bbox=" + bbox + ", metadataOnlineResourceBeanList=" + metadataOnlineResourceBeanList + '}';
    }
    
    public class MetadataOnlineResourceBean {

        private String URL;
        private String protocol;
        private String name;
        private String description;

        /**
         * @return the URL
         */
        public String getURL() {
            return URL;
        }

        /**
         * @param URL the URL to set
         */
        public void setURL(String URL) {
            this.URL = URL;
        }

        /**
         * @return the protocol
         */
        public String getProtocol() {
            return protocol;
        }

        /**
         * @param protocol the protocol to set
         */
        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "MetadataOnlineResourceBean{" + "URL=" + URL + ", protocol=" + protocol + ", name=" + name + ", description=" + description + '}';
        }
    }
}
