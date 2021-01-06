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
package org.geosdi.geoplatform.core.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.hibernate.annotations.Index;

/**
 * @author Francesco Izzi - geoSDI
 *
 */
@JsonTypeInfo(  
    use = JsonTypeInfo.Id.NAME,  
    include = JsonTypeInfo.As.PROPERTY,  
    property = "type")  
@JsonSubTypes({  
    @JsonSubTypes.Type(value = GPRasterLayer.class, name = "GPRaster"),  
    @JsonSubTypes.Type(value = GPVectorLayer.class, name = "GPVector") }) 
@XmlTransient
@XmlSeeAlso(value = {GPRasterLayer.class, GPVectorLayer.class})
@Entity(name = "GPLayer")
@Table(name = "gp_layer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GPLayer implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5746325405739614413L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_LAYER_SEQ")
    @SequenceGenerator(name = "GP_LAYER_SEQ", sequenceName = "GP_LAYER_SEQ")
    private Long id;
    //
    @Column(nullable = false)
    private String title;
    //
    @Column
    @Index(name = "_LAYER_NAME_INDEX")
    private String name;
    //
    @Column(name = "alias_name")
    private String alias;
    //
    @Column(name = "abstract", columnDefinition = "TEXT")
    private String abstractText;
    //
    @Column(name = "url_server")
    private String urlServer;
    //
    @Column
    private String srs;
    //
    @Column(name = "cql_filter", columnDefinition = "TEXT")
    private String cqlFilter;
    //
    @Column(name = "time_filter", columnDefinition = "TEXT")
    private String timeFilter;
    //
    @Embedded
    private GPBBox bbox;
    //
    @Column(name = "layer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GPLayerType layerType;
    //    
    @Column
    private int position = -1;
    //
    @Column
    private boolean checked = false;
    //
    @Column
    private boolean shared = false;
    //
    @Column
    private boolean cached = false;
    //
    @Column(name = "single_tile_request", nullable = false)
    private boolean singleTileRequest = false;

    public abstract GPFolder getFolder();

    public abstract void setFolder(GPFolder folder);

    public abstract GPProject getProject();

    public abstract void setProject(GPProject project);

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCqlFilter() {
        return cqlFilter;
    }

    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    public String getTimeFilter() {
        return timeFilter;
    }

    public void setTimeFilter(String timeFilter) {
        this.timeFilter = timeFilter;
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * @return the urlServer
     */
    public String getUrlServer() {
        return urlServer;
    }

    /**
     * @param urlServer the urlServer to set
     */
    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }

    /**
     * @return the srs
     */
    public String getSrs() {
        return srs;
    }

    /**
     * @param srs the srs to set
     */
    public void setSrs(String srs) {
        this.srs = srs;
    }

    /**
     * @return the bbox
     */
    public GPBBox getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    public void setBbox(GPBBox bbox) {
        this.bbox = bbox;
    }

    /**
     * @return the layerType
     */
    public GPLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType the layerType to set
     */
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the shared
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return the cached
     */
    public boolean isCached() {
        return cached;
    }

    /**
     * @param cached the cached to set
     */
    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public boolean isSingleTileRequest() {
        return singleTileRequest;
    }

    public void setSingleTileRequest(boolean singleTileRequest) {
        this.singleTileRequest = singleTileRequest;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("id=").append(id);
        str.append(", title=").append(title);
        str.append(", name=").append(name);
        str.append(", alias=").append(alias);
        str.append(", abstractText=").append(abstractText);
        str.append(", urlServer=").append(urlServer);
        str.append(", srs=").append(srs);
        str.append(", bbox=").append(bbox);
        str.append(", layerType=").append(layerType);
        str.append(", position=").append(position);
        str.append(", checked=").append(checked);
        str.append(", shared=").append(shared);
        str.append(", cached=").append(cached);
        str.append(", singleTileRequest=").append(singleTileRequest);
        str.append(", cqlFilter=").append(cqlFilter);
        str.append(", timeFilter=").append(timeFilter);
        if (this.getFolder() != null) {
            str.append(", folder.name=").append(this.getFolder().getName());
            str.append("(id=").append(this.getFolder().getId()).append(")");
        } else {
            str.append(", folder=NULL");
        }
        if (this.getProject() != null) {
            str.append(", project.name=").append(this.getProject().getName());
            str.append("(id=").append(this.getProject().getId()).append(")");
        } else {
            str.append(", project=NULL");
        }
        return str.append("}").toString();
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GPLayer other = (GPLayer) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
