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
package org.geosdi.geoplatform.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.geosdi.geoplatform.core.model.adapter.MultiPolygonAdapter;
import org.geosdi.geoplatform.core.model.enums.GrantType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.MultiPolygon;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */

@Entity(name = "GPAccessInfo")
@Table(name = "gp_access_info")
@XmlRootElement(name = "GPAccessInfo")
@org.hibernate.annotations.Table(appliesTo = "gp_access_info", indexes = { @Index(name = "gp_access_info_idx", columnNames = { "layerName" }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "gp_access_info")
public class GPAccessInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_ACCESSINFO_SEQ")
	@SequenceGenerator(name = "GP_ACCESSINFO_SEQ", sequenceName = "GP_ACCESSINFO_SEQ")
	private Long id;

	@Column
	private String layerName;

	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(name = "area", nullable = false)
	private MultiPolygon area;

	@Column
	private String defaultStyle;
	
	@Type(type="text")
	private String cqlFilterRead;
	
	@Type(type="text")
	private String cqlFilterWrite;
	
	@Type(type="text")
	private String attributes;
	
	@Column
	private String clusterNode;
	
	@Column
	private String gsuser;
	
	@Column(name = "grant_type")
	@Enumerated(EnumType.STRING)
	private GrantType grant;

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

	public String getGsuser() {
		return gsuser;
	}

	public void setGsuser(String gsuser) {
		this.gsuser = gsuser;
	}

	public GrantType getGrant() {
		return grant;
	}

	public void setGrant(GrantType grant) {
		this.grant = grant;
	}
	
	
}
