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

import com.vividsolutions.jts.geom.MultiPolygon;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.geosdi.geoplatform.core.model.adapter.MultiPolygonAdapter;
import org.geosdi.geoplatform.core.model.enums.GrantType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Entity(name = "GSResource")
@Table(name = "gs_resource")
@XmlRootElement(name = "GSResource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "gs_resource")
public class GSResource implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2010157772649295820L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GS_RESOURCE_SEQ")
    @SequenceGenerator(name = "GS_RESOURCE_SEQ", sequenceName = "GS_RESOURCE_SEQ")
    private Long id;
    //
    @ManyToOne
    @JoinColumn(name = "gs_account")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GSAccount gsAccount;
    //
    @Column(name = "layer_name")
    @Index(name = "LAYER_NAME_INDEX")
    private String layerName;
    //
    @Column
    private String workspace;
    //
    @Type(type = "org.hibernatespatial.GeometryUserType")
    @Column
    private MultiPolygon area;
    //
    @Column(name = "default_style")
    private String defaultStyle;
    //
    @Column(name = "cql_filter_read")
    @Type(type = "text")
    private String cqlFilterRead;
    //
    @Column(name = "cql_filter_write")
    @Type(type = "text")
    private String cqlFilterWrite;
    //
    @Type(type = "text")
    private String attributes;
    //
    @Column(name = "cluster_node")
    private String clusterNode;
    //
    @Column(name = "grant_type")
    @Enumerated(EnumType.STRING)
    private GrantType grant;

    public GSResource() {
    }

    public GSResource(GrantType grant) {
        this.grant = grant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    @XmlJavaTypeAdapter(MultiPolygonAdapter.class)
    public MultiPolygon getArea() {
        return area;
    }

    public void setArea(MultiPolygon area) {
        this.area = area;
    }

    public String getDefaultStyle() {
        return defaultStyle;
    }

    public void setDefaultStyle(String defaultStyle) {
        this.defaultStyle = defaultStyle;
    }

    public String getCqlFilterRead() {
        return cqlFilterRead;
    }

    public void setCqlFilterRead(String cqlFilterRead) {
        this.cqlFilterRead = cqlFilterRead;
    }

    public String getCqlFilterWrite() {
        return cqlFilterWrite;
    }

    public void setCqlFilterWrite(String cqlFilterWrite) {
        this.cqlFilterWrite = cqlFilterWrite;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getClusterNode() {
        return clusterNode;
    }

    public void setClusterNode(String clusterNode) {
        this.clusterNode = clusterNode;
    }

    public GrantType getGrant() {
        return grant;
    }

    public void setGrant(GrantType grant) {
        this.grant = grant;
    }

    /**
     * @return the gsAccount
     */
    public GSAccount getGsAccount() {
        return gsAccount;
    }

    /**
     * @param gsAccount the gsAccount to set
     */
    public void setGsAccount(GSAccount gsAccount) {
        this.gsAccount = gsAccount;
    }
}
