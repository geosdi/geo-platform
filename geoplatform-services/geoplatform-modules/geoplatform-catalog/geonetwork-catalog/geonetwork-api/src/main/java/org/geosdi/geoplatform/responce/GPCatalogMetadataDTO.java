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

import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPBBox;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class GPCatalogMetadataDTO {

    private String uuid;
    private String title;
    private String abstractValue;
    private String keywordValues = "";
    private GPBBox bbox;
    private List<GPCatalogMetadataOnlineResource> onLineResources = Lists.newArrayList();

    public List<GPCatalogMetadataOnlineResource> getOnlineResource() {
        return onLineResources;
    }

    public void setOnlineResource(List<GPCatalogMetadataOnlineResource> theOnlineResource) {
        this.onLineResources = theOnlineResource;
    }

    public void addOnlineResource(GPCatalogMetadataOnlineResource onlineResource) {
        this.onLineResources.add(onlineResource);
    }

    public String getAbstractValue() {
        return abstractValue;
    }

    public void setAbstractValue(String abstractValues) {
        this.abstractValue = abstractValues;
    }

    public GPBBox getBbox() {
        return bbox;
    }

    public void setBbox(GPBBox bbox) {
        this.bbox = bbox;
    }

    public String getKeywordValues() {
        return keywordValues;
    }

    public void setKeywordValues(String keywordValues) {
        this.keywordValues = keywordValues;
    }

    public void addKeywordValue(String value) {
        if (this.keywordValues.length() == 0) {
            this.keywordValues += value;
        } else {
            this.keywordValues += "; " + value;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "GPCatalogMetadataDTO{" + "uuid=" + uuid + ", title=" + title
                + ", abstractValue=" + abstractValue + ", keywordValues="
                + keywordValues + ", bbox=" + bbox
                + ", gpCatalogMetadataOnlineResourceList="
                + onLineResources + '}';
    }
}
