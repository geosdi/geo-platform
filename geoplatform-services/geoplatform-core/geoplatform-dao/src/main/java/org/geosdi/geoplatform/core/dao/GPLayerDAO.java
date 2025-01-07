/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.criteria.BaseCriteriaJpaDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Francesco Izzi - geoSDI
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public interface GPLayerDAO extends BaseCriteriaJpaDAO<GPLayer, Long> {

    /**
     * @param id
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean removeById(Long id) throws GPDAOException;

    /**
     * @param layerName
     * @return {@link GPLayer}
     * @throws GPDAOException
     */
    GPLayer findByLayerName(String layerName) throws GPDAOException;

    /**
     * @param projectID
     * @return {@link ArrayList<String>}
     * @throws GPDAOException
     */
    ArrayList<String> findDistinctDataSourceByProjectId(Long projectID) throws GPDAOException;

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
    Boolean updatePositionsRangeInOppositeWay(Long projectID, int beginPositionFirstRange, int endPositionFirstRange,
            int beginPositionSecondRange, int endPositionSecondRange, int deltaValueFirstRange,
            int deltaValueSecondRange) throws GPDAOException;

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
     * @param layerID
     * @param checked
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    Boolean persistCheckStatusLayer(Long layerID, boolean checked) throws GPDAOException;

    /**
     * @param projectID
     * @param lessOrEqualTo
     * @param greatherOrEqualTo
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> findByPositionAndProjectID(Long projectID, Integer lessOrEqualTo, Integer greatherOrEqualTo)
            throws GPDAOException;

    /**
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByFolderID(Long folderID) throws GPDAOException;

    /**
     * @param projectID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByProjectIDSortAscByTitle(Long projectID) throws GPDAOException;

    /**
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByFolderIDSortDescByPosition(Long folderID) throws GPDAOException;

    /**
     * @param folderIDs
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByFolderIDsSortDescByPosition(List<Long> folderIDs) throws GPDAOException;

    /**
     * @param projectID
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByProjectAndFolderID(Long projectID, Long folderID) throws GPDAOException;

    /**
     * @param projectID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    List<GPLayer> searchLayersByProjectID(Long projectID) throws GPDAOException;
}