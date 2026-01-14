/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.gui.shared.GPLayerType;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPLayer extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getId();

    /**
     * @param theId
     */
    void setId(Long theId);

    /**
     * @return {@link String}
     */
    String getTitle();

    /**
     * @param theTitle
     */
    void setTitle(String theTitle);

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link String}
     */
    String getAlias();

    /**
     * @param theAlias
     */
    void setAlias(String theAlias);

    /**
     * @return {@link String}
     */
    String getCqlFilter();

    /**
     * @param theCqlFilter
     */
    void setCqlFilter(String theCqlFilter);

    /**
     * @return {@link String}
     */
    String getTimeFilter();

    /**
     * @param theTimeFilter
     */
    void setTimeFilter(String theTimeFilter);

    /**
     * @return {@link String}
     */
    String getAbstractText();

    /**
     * @param theAbstractText
     */
    void setAbstractText(String theAbstractText);

    /**
     * @return {@link String}
     */
    String getUrlServer();

    /**
     * @param theUrlServer
     */
    void setUrlServer(String theUrlServer);

    /**
     * @return {@link String}
     */
    String getSrs();

    /**
     * @param theSrs
     */
    void setSrs(String theSrs);

    /**
     * @return {@link GPBBox}
     */
    GPBBox getBbox();

    /**
     * @param theBbox
     */
    void setBbox(GPBBox theBbox);

    /**
     * @return {@link  GPLayerType}
     */
    GPLayerType getLayerType();

    /**
     * @param theLayerType
     */
    void setLayerType(GPLayerType theLayerType);

    /**
     * @return {@link Integer}
     */
    int getPosition();

    /**
     * @param thePosition
     */
    void setPosition(int thePosition);

    /**
     * @return {@link Boolean}
     */
    boolean isChecked();

    /**
     * @param theChecked
     */
    void setChecked(boolean theChecked);

    /**
     * @return {@link Boolean}
     */
    boolean isShared();

    /**
     * @param theShared
     */
    void setShared(boolean theShared);

    /**
     * @return {@link Boolean}
     */
    boolean isCached();

    /**
     * @param theCached
     */
    void setCached(boolean theCached);

    /**
     * @return {@link Boolean}
     */
    boolean isSingleTileRequest();

    /**
     * @param theSingleTileRequest
     */
    void setSingleTileRequest(boolean theSingleTileRequest);

    /**
     * @return {@link GPFolder}
     */
    GPFolder getFolder();

    /**
     * @param theFolder
     */
    void setFolder(GPFolder theFolder);

    /**
     * @return {@link GPProject}
     */
    GPProject getProject();

    /**
     * @param theProject
     */
    void setProject(GPProject theProject);
}