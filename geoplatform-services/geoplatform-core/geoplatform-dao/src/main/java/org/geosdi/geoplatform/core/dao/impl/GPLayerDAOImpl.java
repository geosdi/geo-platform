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
package org.geosdi.geoplatform.core.dao.impl;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Francesco Izzi - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Repository(value = "layerDAO")
@Profile(value = "jpa")
class GPLayerDAOImpl extends GPAbstractJpaDAO<GPLayer, Long> implements GPLayerDAO {

    GPLayerDAOImpl() {
        super(GPLayer.class);
    }

    /**
     * @param id
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean removeById(Long id) throws GPDAOException {
        return (super.deleteByID(id) == 1 ? TRUE : FALSE);
    }

    /**
     * @param layerName
     * @return {@link GPLayer}
     * @throws GPDAOException
     */
    @Override
    public GPLayer findByLayerName(String layerName) throws GPDAOException {
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()), "The Parameter layerName must not be null or an empty string");
        try {
            Query query = this.entityManager.createQuery("from GPLayer l where l.name=:name");
            query.setParameter("name", layerName);
            List<GPLayer> layers = query.getResultList();
            return ((layers != null) && !(layers.isEmpty()) ? layers.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List <String>}
     * @throws GPDAOException
     */
    @Override
    public ArrayList<String> findDistinctDataSourceByProjectId(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select distinct l.urlServer from GPLayer l " +
                    "join l.project as p where p.id=:id");
            query.setParameter("id", projectID);
            ArrayList<String> result = Lists.newArrayList();
            List<String> founds = query.getResultList();
            result.addAll(founds);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

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
    @Override
    public Boolean updatePositionsRangeInOppositeWay(Long projectID, int beginPositionFirstRange, int endPositionFirstRange,
            int beginPositionSecondRange, int endPositionSecondRange, int deltaValueFirstRange, int deltaValueSecondRange)
            throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must be not null");
        checkArgument(beginPositionFirstRange > 0, "The Parameter beginPositionFirstRange must be greater than zero");
        checkArgument(beginPositionSecondRange > 0, "The Parameter beginPositionSecondRange must be greater than zero");
        checkArgument(beginPositionFirstRange < endPositionFirstRange, "The Parameter beginPositionFirstRange must be lesser than endPositionFirstRange");
        checkArgument(beginPositionSecondRange < endPositionSecondRange, "The Parameter beginPositionSecondRange must be lesser than endPositionSecondRange");
        checkArgument(endPositionFirstRange > beginPositionSecondRange, "The Parameter endPositionFirstRange must be greater than beginPositionSecondRange");
        checkArgument(deltaValueFirstRange != 0, "The Parameter deltaValueFirstRange must not be 0");
        checkArgument(deltaValueSecondRange != 0, "The Parameter deltaValueSecondRange must not be 0");
        try {
            // Select the layers of interest (first range)
            Query queryFirstRange = this.entityManager.createQuery("select l from GPLayer l join l.project as p where p.id=:id " +
                    "and l.position >=:pStart and l.position <=:pEnd");
            queryFirstRange.setParameter("id", projectID);
            queryFirstRange.setParameter("pStart", beginPositionFirstRange);
            queryFirstRange.setParameter("pEnd", endPositionFirstRange);
            List<GPLayer> matchingLayerFirstRange = queryFirstRange.getResultList();
            logger.debug("\n*** UPDATE First Range Layer with Position: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPositionFirstRange, endPositionFirstRange, endPositionFirstRange - beginPositionFirstRange + 1, deltaValueFirstRange);
            logger.debug("\n*** Matching First Range Layer count: {} ***", matchingLayerFirstRange.size());
            // Select the layers of interest (second range)
            Query querySecondRange = this.entityManager.createQuery("from GPLayer l join l.project as p where p.id=:id " +
                    "and l.position >=:pStart and l.position <=:pEnd");
            querySecondRange.setParameter("id", projectID);
            querySecondRange.setParameter("pStart", beginPositionSecondRange);
            querySecondRange.setParameter("pEnd", endPositionSecondRange);
            List<GPLayer> matchingLayerSecondRange = querySecondRange.getResultList();

            logger.debug("\n*** UPDATE Second Range Layer with Position: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPositionSecondRange, endPositionSecondRange, endPositionSecondRange - beginPositionSecondRange + 1, deltaValueSecondRange);
            logger.debug("\n*** Matching Second Range Layer count: {} ***", matchingLayerSecondRange.size());
            // Update (first range)
            int[] oldPositionsFirstRange = new int[matchingLayerFirstRange.size()];
            for (int ind = matchingLayerFirstRange.size() - 1; ind >= 0; ind--) {
                GPLayer layer = matchingLayerFirstRange.get(ind);
                oldPositionsFirstRange[ind] = layer.getPosition();
                layer.setPosition(layer.getPosition() + deltaValueFirstRange);
                logger.trace("\n*** Position of the UPDATED GPLayer First Range: {} ({} + {}) ***",
                        layer.getPosition(), oldPositionsFirstRange[ind], deltaValueFirstRange);
            }
            // Update (second range)
            int[] oldPositionsSecondRange = new int[matchingLayerSecondRange.size()];
            for (int ind = matchingLayerSecondRange.size() - 1; ind >= 0; ind--) {
                GPLayer layer = matchingLayerSecondRange.get(ind);
                oldPositionsSecondRange[ind] = layer.getPosition();
                layer.setPosition(layer.getPosition() + deltaValueSecondRange); // (shift in opposite way)
                logger.trace("\n*** Position of the UPDATED GPLayer Second Range: {} ({} + {}) ***",
                        layer.getPosition(), oldPositionsSecondRange[ind], deltaValueSecondRange);
            }

            List<GPLayer> matchingLayers = new ArrayList<>();
            matchingLayers.addAll(matchingLayerFirstRange);
            matchingLayers.addAll(matchingLayerSecondRange);
            GPLayer[] layersUpdated = super.update(matchingLayers).stream().toArray(GPLayer[]::new);
            // Check the update
            for (int ind = 0; ind < matchingLayerFirstRange.size(); ind++) {
                logger.trace("\n*** Check Position - First Range: {} ({} + {}) ***",
                        layersUpdated[ind].getPosition(), oldPositionsFirstRange[ind], deltaValueFirstRange);
                if ((oldPositionsFirstRange[ind] + deltaValueFirstRange) != layersUpdated[ind].getPosition()) {
                    return FALSE;
                }
            }
            int indSplit = matchingLayerFirstRange.size();
            for (int ind = 0; ind < matchingLayerSecondRange.size(); ind++) {
                logger.trace("\n*** Check Position - Second Range: {} ({} + {}) ***",
                        layersUpdated[ind + indSplit].getPosition(), oldPositionsSecondRange[ind], deltaValueSecondRange);
                if ((oldPositionsSecondRange[ind] + deltaValueSecondRange) != layersUpdated[ind + indSplit].getPosition()) {
                    return FALSE;
                }
            }
            return TRUE;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @param beginPosition
     * @param endPosition
     * @param deltaValue
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean updatePositionsRange(Long projectID, int beginPosition, int endPosition, int deltaValue) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must be not null");
        checkArgument(beginPosition <= endPosition, "The Parameter beginPosition must be lesser than or equal endPosition");
        checkArgument(deltaValue != 0, "The Parameter deltaValue must not be 0");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.project as p where p.id=:id " +
                    "and l.position >=:pStart and l.position <=:pEnd");
            query.setParameter("id", projectID);
            query.setParameter("pStart", beginPosition);
            query.setParameter("pEnd", endPosition);
            List<GPLayer> matchingLayers = query.getResultList();
            logger.debug("\n*** Layers with Position in range: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPosition, endPosition, endPosition - beginPosition + 1, deltaValue);
            logger.debug("\n*** Matching Layers count: {} ***", matchingLayers.size());
            // No updates (select 0 layers)
            if (matchingLayers.isEmpty()) {
                return TRUE;
            }
            return (matchingLayers.isEmpty() ? TRUE : this.updatePositions(matchingLayers, deltaValue));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @param lowerBoundPosition
     * @param deltaValue
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean updatePositionsLowerBound(Long projectID, int lowerBoundPosition, int deltaValue) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must be not null");
        checkArgument(deltaValue != 0, "The Parameter deltaValue must not be 0");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.project as p where p.id=:id " +
                    "and l.position >=:pStart");
            query.setParameter("id", projectID);
            query.setParameter("pStart", lowerBoundPosition);
            List<GPLayer> matchingLayers = query.getResultList();
            logger.debug("\n*** UPDATE Layers with Position from {} *** deltaValue = {} ***",
                    lowerBoundPosition, deltaValue);
            logger.info("\n*** Matching Layers count: {} ***", matchingLayers);
            return (matchingLayers.isEmpty() ? TRUE : this.updatePositions(matchingLayers, deltaValue));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param layerID
     * @param checked
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean persistCheckStatusLayer(Long layerID, boolean checked) throws GPDAOException {
        checkArgument(layerID != null, "The Parameter layerID must not be null.");
        // Retrieve the layer
        GPLayer layer = super.find(layerID);
        if (layer == null) {
            logger.debug("\n*** The Layer with ID \"{}\" does NOT exist into DB ***", layerID);
            return FALSE;
        }
        logger.trace("\n*** Layer RETRIEVED:\n{}\n*** MOD checked to {} ***", layer, checked);
        // Merge iff the check status is different
        if (layer.isChecked() != checked) {
            layer.setChecked(checked);
            GPLayer layerUpdated = super.update(layer);
            if (layerUpdated.isChecked() != checked) {
                return FALSE;
            }
        }
        return TRUE;
    }

    /**
     * @param projectID
     * @param lessOrEqualTo
     * @param greatherOrEqualTo
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> findByPositionAndProjectID(Long projectID, Integer lessOrEqualTo, Integer greatherOrEqualTo) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        checkArgument(lessOrEqualTo != null, "The Parameter lessOrEqualTo must not be null.");
        checkArgument(greatherOrEqualTo != null, "The Parameter greatherOrEqualTo must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.project as p " +
                    "where p.id=:projectID and l.position>=:greatherOrEqualTo and l.position <=:lessOrEqualTo");
            query.setParameter("projectID", projectID);
            query.setParameter("greatherOrEqualTo", greatherOrEqualTo);
            query.setParameter("lessOrEqualTo", lessOrEqualTo);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByFolderID(Long folderID) throws GPDAOException {
        checkArgument(folderID != null, "The Parameter folderID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.folder as f " +
                    "where f.id=:folderID");
            query.setParameter("folderID", folderID);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByProjectIDSortAscByTitle(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l FROM GPLayer l join l.project as p " +
                    "where p.id=:projectID ORDER BY l.title ASC");
            query.setParameter("projectID", projectID);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByFolderIDSortDescByPosition(Long folderID) throws GPDAOException {
        checkArgument(folderID != null, "The Parameter folderID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.folder as f " +
                    "where f.id=:folderID ORDER BY l.position DESC");
            query.setParameter("folderID", folderID);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param folderIDs
     * @return {@link List < GPLayer >}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByFolderIDsSortDescByPosition(List<Long> folderIDs) throws GPDAOException {
        checkArgument(((folderIDs != null)), "The Parameter folderIDs must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.folder as f " +
                    "where f.id IN (:folderIDs) ORDER BY l.position DESC");
            query.setParameter("folderIDs", folderIDs);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @param folderID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByProjectAndFolderID(Long projectID, Long folderID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        checkArgument(folderID != null, "The Parameter folderID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.project as p " +
                    "join l.folder as f where p.id=:projectID and f.id=:folderID");
            query.setParameter("projectID", projectID);
            query.setParameter("folderID", folderID);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List<GPLayer>}
     * @throws GPDAOException
     */
    @Override
    public List<GPLayer> searchLayersByProjectID(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            Query query = this.entityManager.createQuery("select l from GPLayer l join l.project as p " +
                    "where p.id=:projectID");
            query.setParameter("projectID", projectID);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param matchingLayers
     * @param deltaValue
     * @return {@link Boolean}
     */
    private boolean updatePositions(List<GPLayer> matchingLayers, int deltaValue) {
        // Update position of selected elements
        int[] oldPositions = new int[matchingLayers.size()];
        for (int ind = matchingLayers.size() - 1; ind >= 0; ind--) {
            GPLayer layer = matchingLayers.get(ind);
            oldPositions[ind] = layer.getPosition();
            layer.setPosition(layer.getPosition() + deltaValue);
        }
        GPLayer[] layersUpdated = super.update(matchingLayers).stream().toArray(GPLayer[]::new);
        // Check the update
        for (int ind = layersUpdated.length - 1; ind >= 0; ind--) {
            logger.trace("\n*** Position of the UPDATED GPLayer: {} ({} + {}) ***",
                    layersUpdated[ind].getPosition(), oldPositions[ind], deltaValue);
            if ((oldPositions[ind] + deltaValue) != layersUpdated[ind].getPosition()) {
                return FALSE;
            }
        }
        return TRUE;
    }
}