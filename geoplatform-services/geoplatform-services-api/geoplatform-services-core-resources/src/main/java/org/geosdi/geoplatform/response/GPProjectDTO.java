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
package org.geosdi.geoplatform.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPProjectDTO extends Serializable {

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
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link Integer}
     */
    Integer getNumberOfElements();

    /**
     * @param numberOfElements
     */
    void setNumberOfElements(Integer numberOfElements);

    /**
     * @return {@link Integer}
     */
    Integer getVersion();

    /**
     * @param theVersion
     */
    void setVersion(Integer theVersion);

    /**
     * @return {@link Boolean}
     */
    boolean isShared();

    /**
     * @param theShared
     */
    void setShared(boolean theShared);

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * @param theDescription
     */
    void setDescription(String theDescription);

    /**
     * @return {@link String}
     */
    String getImagePath();

    /**
     * @param theImagePath
     */
    void setImagePath(String theImagePath);

    /**
     * @return {@link Boolean}
     */
    boolean isInternalPublic();

    /**
     * @param internalPublic
     */
    void setInternalPublic(boolean internalPublic);

    /**
     * @return {@link Boolean}
     */
    boolean isExternalPublic();

    /**
     * @param externalPublic
     */
    void setExternalPublic(boolean externalPublic);

    /**
     * @return {@link Date}
     */
    Date getCreationDate();

    /**
     * @param theCreationDate
     */
    void setCreationDate(Date theCreationDate);

    /**
     * @return {@link Boolean}
     */
    boolean isDefaultProject();

    /**
     * @param theDefaultProject
     */
    void setDefaultProject(boolean theDefaultProject);

    /**
     * @param <Owner>
     * @return {@link Owner}
     */
    <Owner extends ShortAccountDTO> Owner getOwner();

    /**
     * @param theOwner
     * @param <Owner>
     */
    <Owner extends ShortAccountDTO> void setOwner(Owner theOwner);

    /**
     * @return {@link List<FolderDTO>}
     */
    List<FolderDTO> getRootFolders();

    /**
     * @param theRootFolders
     */
    void setRootFolders(List<FolderDTO> theRootFolders);
}