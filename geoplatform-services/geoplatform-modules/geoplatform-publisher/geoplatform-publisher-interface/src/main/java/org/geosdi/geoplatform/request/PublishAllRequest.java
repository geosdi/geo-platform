/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.request;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PublishAllRequest implements Serializable {

    private static final long serialVersionUID = -4220372985206008478L;
    //
    private String sessionID;
    private String workspace;
    private String dataStoreName;
    @XmlElementWrapper(name = "layerNames")
    @XmlElement(name = "layerName")
    private List<String> layerNames;

    public PublishAllRequest() {
    }

    public PublishAllRequest(String theSessionID, String theWorkspace,
            String theDataStoreName, List<String> theLayerNames) {
        this.sessionID = theSessionID;
        this.workspace = theWorkspace;
        this.dataStoreName = theDataStoreName;
        this.layerNames = theLayerNames;
    }

    /**
     * @return the sessionID
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * @return the workspace
     */
    public String getWorkspace() {
        return workspace;
    }

    /**
     * @param workspace the workspace to set
     */
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    /**
     * @return the dataStoreName
     */
    public String getDataStoreName() {
        return dataStoreName;
    }

    /**
     * @param dataStoreName the dataStoreName to set
     */
    public void setDataStoreName(String dataStoreName) {
        this.dataStoreName = dataStoreName;
    }

    /**
     * @return the layerNames
     */
    public List<String> getLayerNames() {
        return layerNames;
    }

    /**
     * @param layerNames the layerNames to set
     */
    public void setLayerNames(List<String> layerNames) {
        this.layerNames = layerNames;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "sessionID = " + sessionID
                + ", workspace = " + workspace
                + ", dataStoreName = " + dataStoreName
                + ", layerNames = " + layerNames + '}';
    }

}
