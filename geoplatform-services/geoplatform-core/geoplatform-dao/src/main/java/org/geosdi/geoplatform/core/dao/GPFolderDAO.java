/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.core.dao;

import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.criteria.BaseCriteriaJpaDAO;

import java.util.List;
import java.util.Map;

/**
 * @author Francesco Izzi - geoSDI
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public interface GPFolderDAO extends BaseCriteriaJpaDAO<GPFolder, Long> {

    /**
     * @param id
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean removeById(Long id) throws GPDAOException;

    /**
     * @param folderName
     * @return {@link GPFolder}
     * @throws GPDAOException
     */
    GPFolder findByFolderName(String folderName) throws GPDAOException;

    /**
     * @param projectID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchRootFolders(Long projectID) throws GPDAOException;

    /**
     * @param projectID
     * @param beginPositionFirstRange
     * @param endPositionFirstRange
     * @param beginPositionSecondRange
     * @param endPositionSecondRange
     * @param deltaValueFirstRange
     * @param deltaValueSecondRange
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean updatePositionsRangeInOppositeWay(Long projectID, int beginPositionFirstRange,
            int endPositionFirstRange, int beginPositionSecondRange, int endPositionSecondRange,
            int deltaValueFirstRange, int deltaValueSecondRange) throws GPDAOException;

    /**
     * @param projectID
     * @param beginPosition
     * @param endPosition
     * @param deltaValue
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean updatePositionsRange(Long projectID, int beginPosition, int endPosition, int deltaValue) throws GPDAOException;

    /**
     * @param projectID
     * @param lowerBoundPosition
     * @param deltaValue
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean updatePositionsLowerBound(Long projectID, int lowerBoundPosition, int deltaValue) throws GPDAOException;

    /**
     * @param descendantsMap
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean updateAncestorsDescendants(Map<Long, Integer> descendantsMap) throws GPDAOException;

    /**
     * @param folderID
     * @param checked
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean persistCheckStatusFolder(Long folderID, boolean checked) throws GPDAOException;

    /**
     * @param checked
     * @param folderIDs
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean persistCheckStatusFolders(boolean checked, Long... folderIDs) throws GPDAOException;

    /**
     * @param projectID
     * @param lessOrEqualTo
     * @param greatherOrEqualTo
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> findByPositionAndProjectID(Long projectID, Integer lessOrEqualTo, Integer greatherOrEqualTo)
            throws GPDAOException;

    /**
     * @param page
     * @param size
     * @param name
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchPagebleFolders(Integer page, Integer size, String name) throws GPDAOException;

    /**
     * @param name
     * @return {@link Number}
     * @throws GPDAOException
     */
    Number countFolders(String name) throws GPDAOException;

    /**
     * @param page
     * @param size
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchPagebleFoldersByParentID(Integer page, Integer size, Long parentID) throws GPDAOException;

    /**
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchFoldersByParentID(Long parentID) throws GPDAOException;

    /**
     * @param ids
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> find(Long... ids) throws GPDAOException;

    /**
     * @param projectID
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchFoldersByProjectAndParentID(Long projectID, Long parentID) throws GPDAOException;

    /**
     * @param projectID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    List<GPFolder> searchSubFoders(Long projectID) throws GPDAOException;
}
