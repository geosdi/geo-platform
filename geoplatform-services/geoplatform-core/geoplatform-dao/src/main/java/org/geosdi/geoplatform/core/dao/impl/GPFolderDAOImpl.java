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
package org.geosdi.geoplatform.core.dao.impl;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toList;

/**
 * @author Francesco Izzi - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Repository(value = "folderDAO")
@Profile(value = "jpa")
class GPFolderDAOImpl extends GPAbstractJpaDAO<GPFolder, Long> implements GPFolderDAO {

    public GPFolderDAOImpl() {
        super(GPFolder.class);
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
     * @param folderName
     * @return {@link GPFolder}
     * @throws GPDAOException
     */
    @Override
    public GPFolder findByFolderName(String folderName) throws GPDAOException {
        checkArgument((folderName != null) && !(folderName.isEmpty()),
                "The Parameter folderName must not be null or an empty string.");
        try {
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(root.get("name"), folderName));
            List<GPFolder> folders = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((folders != null) && !(folders.isEmpty()) ? folders.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchRootFolders(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            CriteriaBuilder builder = super.criteriaBuilder();
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("project").get("id"), projectID),
                    builder.isNull(root.get("parent")));
            criteriaQuery.orderBy(builder.desc(root.get("position")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List < GPFolder >}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchSubFoders(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            CriteriaBuilder builder = super.criteriaBuilder();
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("project").get("id"), projectID),
                    builder.isNotNull(root.get("parent")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
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
    public Boolean updatePositionsRangeInOppositeWay(Long projectID, int beginPositionFirstRange,
            int endPositionFirstRange, int beginPositionSecondRange, int endPositionSecondRange,
            int deltaValueFirstRange, int deltaValueSecondRange) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        checkArgument((beginPositionFirstRange > 0), "The Parameter beginPositionFirstRange must be greater than zero");
        checkArgument((beginPositionSecondRange > 0), "The Parameter beginPositionSecondRange must be greater than zero");
        checkArgument((beginPositionFirstRange < endPositionFirstRange),
                "The Parameter beginPositionFirstRange must be lesser than endPositionFirstRange");
        checkArgument((beginPositionSecondRange < endPositionSecondRange),
                "The Parameter beginPositionSecondRange must be lesser than endPositionSecondRange");
        checkArgument((endPositionFirstRange > beginPositionSecondRange),
                "The Parameter endPositionFirstRange must be greater than beginPositionSecondRange");
        checkArgument((deltaValueFirstRange != 0), "The Parameter deltaValueFirstRange must not be 0");
        checkArgument((deltaValueSecondRange != 0), "The Parameter deltaValueSecondRange must not be 0");
        try {
            // Select the folders of interest (first range)
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQueryFirstRange = super.createCriteriaQuery();
            Root<GPFolder> rootFirstRange = criteriaQueryFirstRange.from(this.persistentClass);
            criteriaQueryFirstRange.select(rootFirstRange);
            criteriaQueryFirstRange.where(criteriaBuilder.equal(rootFirstRange.join("project").get("id"), projectID),
                    criteriaBuilder.greaterThanOrEqualTo(rootFirstRange.get("position"), beginPositionFirstRange),
                    criteriaBuilder.lessThanOrEqualTo(rootFirstRange.get("position"), endPositionFirstRange));
            List<GPFolder> matchingFoldersFirstRange = this.entityManager.createQuery(criteriaQueryFirstRange).getResultList();
            logger.debug("\n*** UPDATE First Range Folders with Position: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPositionFirstRange, endPositionFirstRange, endPositionFirstRange - beginPositionFirstRange + 1,
                    deltaValueFirstRange);
            logger.debug("\n*** Matching First Range Folders count: {} ***", matchingFoldersFirstRange.size());
            // Select the folders of interest (second range)
            CriteriaQuery<GPFolder> criteriaQuerySecondRange = super.createCriteriaQuery();
            Root<GPFolder> rootSecondRange = criteriaQuerySecondRange.from(this.persistentClass);
            criteriaQuerySecondRange.select(rootSecondRange);
            criteriaQuerySecondRange.where(criteriaBuilder.equal(rootSecondRange.join("project").get("id"), projectID),
                    criteriaBuilder.greaterThanOrEqualTo(rootSecondRange.get("position"), beginPositionSecondRange),
                    criteriaBuilder.lessThanOrEqualTo(rootSecondRange.get("position"), endPositionSecondRange));
            List<GPFolder> matchingFoldersSecondRange = this.entityManager.createQuery(criteriaQuerySecondRange).getResultList();
            logger.debug("\n*** UPDATE Second Range Folders with Position: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPositionSecondRange, endPositionSecondRange, endPositionSecondRange - beginPositionSecondRange + 1, deltaValueSecondRange);
            logger.debug("\n*** Matching Second Range Folders count: {} ***", matchingFoldersSecondRange.size());
            // Update (first range)
            int[] oldPositionsFirstRange = new int[matchingFoldersFirstRange.size()];
            for (int ind = matchingFoldersFirstRange.size() - 1; ind >= 0; ind--) {
                GPFolder folder = matchingFoldersFirstRange.get(ind);
                oldPositionsFirstRange[ind] = folder.getPosition();
                folder.setPosition(folder.getPosition() + deltaValueFirstRange);
                logger.trace("\n*** Position of the UPDATED GPFolder First Range: {} ({} + {}) ***",
                        folder.getPosition(), oldPositionsFirstRange[ind], deltaValueFirstRange);
            }

            // Update (second range)
            int[] oldPositionsSecondRange = new int[matchingFoldersSecondRange.size()];
            for (int ind = matchingFoldersSecondRange.size() - 1; ind >= 0; ind--) {
                GPFolder folder = matchingFoldersSecondRange.get(ind);
                oldPositionsSecondRange[ind] = folder.getPosition();
                folder.setPosition(folder.getPosition() + deltaValueSecondRange); // (shift in opposite way)
                logger.trace("\n*** Position of the UPDATED GPFolder Second Range: {} ({} + {}) ***",
                        folder.getPosition(), oldPositionsSecondRange[ind], deltaValueSecondRange);
            }

            List<GPFolder> matchingFolders = new ArrayList<GPFolder>();
            matchingFolders.addAll(matchingFoldersFirstRange);
            matchingFolders.addAll(matchingFoldersSecondRange);
            GPFolder[] foldersUpdated = super.update(matchingFolders).stream().toArray(GPFolder[]::new);
            // Check the update
            for (int ind = 0; ind < matchingFoldersFirstRange.size(); ind++) {
                logger.trace("\n*** Check Position - First Range: {} ({} + {}) ***",
                        foldersUpdated[ind].getPosition(), oldPositionsFirstRange[ind], deltaValueFirstRange);
                if ((oldPositionsFirstRange[ind] + deltaValueFirstRange) != foldersUpdated[ind].getPosition()) {
                    return FALSE;
                }
            }
            int indSplit = matchingFoldersFirstRange.size();
            for (int ind = 0; ind < matchingFoldersSecondRange.size(); ind++) {
                logger.trace("\n*** Check Position - Second Range: {} ({} + {}) ***",
                        foldersUpdated[ind + indSplit].getPosition(), oldPositionsSecondRange[ind], deltaValueSecondRange);
                if ((oldPositionsSecondRange[ind] + deltaValueSecondRange) != foldersUpdated[ind + indSplit].getPosition()) {
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
        checkArgument((projectID != null), "The Parameter projectID must be not null");
        checkArgument((beginPosition < endPosition), "The Parameter beginPosition must be lesser than endPosition");
        checkArgument((deltaValue != 0), "The Parameter deltaValue must not be 0");
        try {
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.join("project").get("id"), projectID),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("position"), beginPosition),
                    criteriaBuilder.lessThanOrEqualTo(root.get("position"), endPosition));
            List<GPFolder> fodersFound = this.entityManager.createQuery(criteriaQuery).getResultList();
            logger.debug("\n*** UPDATE Folders with Position: {} to {} [# {}] *** deltaValue = {} ***",
                    beginPosition, endPosition, endPosition - beginPosition + 1, deltaValue);
            logger.debug("\n*** Folders Found count: {} ***", fodersFound.size());
            return (fodersFound.isEmpty() ? TRUE : this.updatePositions(fodersFound, deltaValue));
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
        checkArgument((projectID != null), "The Parameter projectID must be not null");
        checkArgument((deltaValue != 0), "The Parameter deltaValue must not be 0");
        try {
            // Select the folders of interest (position >= lowerBoundPosition)
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.join("project").get("id"), projectID),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("position"), lowerBoundPosition));
            List<GPFolder> foldersFound = this.entityManager.createQuery(criteriaQuery).getResultList();
            logger.debug("\n*** UPDATE Folders with Position from {} *** deltaValue = {} ***",
                    lowerBoundPosition, deltaValue);
            logger.debug("\n*** Folders Found count: {} ***", foldersFound.size());
            return (foldersFound.isEmpty() ? TRUE : this.updatePositions(foldersFound, deltaValue));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param descendantsMap
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean updateAncestorsDescendants(Map<Long, Integer> descendantsMap) throws GPDAOException {
        checkArgument((descendantsMap != null), "The Parameter descendantsMap must not be null.");
        if (descendantsMap.isEmpty())
            return TRUE;
        try {
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(root.get("id").in(descendantsMap.keySet()));
            List<GPFolder> fodersFound = this.entityManager.createQuery(criteriaQuery).getResultList();
            logger.debug("\n\n**************** Folders Found count: {} ***", fodersFound.size());
            // No updates (select 0 folders)
            if (fodersFound.isEmpty()) {
                return TRUE;
            }
            int[] oldDescendants = new int[fodersFound.size()]; // Only for log
            for (int ind = 0; ind < fodersFound.size(); ind++) {
                GPFolder folder = fodersFound.get(ind);
                oldDescendants[ind] = folder.getNumberOfDescendants();
                int newNumberOfDescendants = descendantsMap.get(folder.getId());
                folder.setNumberOfDescendants(newNumberOfDescendants);
            }
            GPFolder[] foldersUpdated = super.update(fodersFound).stream().toArray(GPFolder[]::new);
            // Check the update
            for (int ind = 0; ind < foldersUpdated.length; ind++) {
                Long id = foldersUpdated[ind].getId();
                int numberOfDescendants = foldersUpdated[ind].getNumberOfDescendants();
                logger.debug("\n*** Number of Descentans of the UPDATED GPFolder \"{}\": {} (OLD Descentans = {}) ***",
                        foldersUpdated[ind].getName(), numberOfDescendants, oldDescendants[ind]);
                if (descendantsMap.get(id) != numberOfDescendants) {
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
     * @param folderID
     * @param checked
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean persistCheckStatusFolder(Long folderID, boolean checked) throws GPDAOException {
        checkArgument(folderID != null, "The Parameter folderID must not be null.");
        GPFolder folder = super.find(folderID);
        if (folder == null) {
            logger.debug("\n*** The Folder with ID \"{}\" is NOT exist into DB ***", folderID);
            return FALSE;
        }
        logger.trace("\n*** Folder RETRIEVED:\n{}\n*** MOD checked to {} ***", folder, checked);
        // Merge iff the check status is different
        if (folder.isChecked() != checked) {
            folder.setChecked(checked);
            GPFolder folderUpdated = super.update(folder);
            if (folderUpdated.isChecked() != checked) {
                return FALSE;
            }
        }
        return TRUE;
    }

    /**
     * @param checked
     * @param folderIDs
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean persistCheckStatusFolders(boolean checked, Long... folderIDs) throws GPDAOException {
        checkArgument(folderIDs != null, "The Parameter folderIDs must not be null.");
        for (Long longIth : folderIDs) {
            boolean checkSave = this.persistCheckStatusFolder(longIth, checked);
            if (!checkSave) {
                logger.debug("\n*** The Folder with ID \"{}\" is has NOT changed the check***", longIth);
                return FALSE;
            }
        }
        return TRUE;
    }

    /**
     * @param projectID
     * @param lessOrEqualTo
     * @param greatherOrEqualTo
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> findByPositionAndProjectID(Long projectID, Integer lessOrEqualTo, Integer greatherOrEqualTo)
            throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        checkArgument(lessOrEqualTo != null, "The Parameter lessOrEqualTo must not be null.");
        checkArgument(greatherOrEqualTo != null, "The Parameter greatherOrEqualTo must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("project").get("id"), projectID),
                    builder.lessThanOrEqualTo(root.get("position"), lessOrEqualTo),
                    builder.greaterThanOrEqualTo(root.get("position"), greatherOrEqualTo));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param page
     * @param size
     * @param name
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchPagebleFolders(Integer page, Integer size, String name) throws GPDAOException {
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            if ((name != null) && !(name.isEmpty())) {
                criteriaQuery.where(builder.like(builder.lower(root.get("name")), name.toLowerCase()));
            }
            criteriaQuery.orderBy(builder.asc(root.get("name")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param name
     * @return {@link Number}
     * @throws GPDAOException
     */
    @Override
    public Number countFolders(String name) throws GPDAOException {
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(builder.count(root));
            if ((name != null) && !(name.isEmpty())) {
                criteriaQuery.where(builder.like(builder.lower(root.get("name")), name.toLowerCase()));
            }
            return this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param page
     * @param size
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchPagebleFoldersByParentID(Integer page, Integer size, Long parentID) throws GPDAOException {
        checkArgument(parentID != null, "The Parameter parentID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("parent").get("id"), parentID))
                    .orderBy(builder.asc(root.get("name")));
            TypedQuery<GPFolder> typedQuery = this.entityManager.createQuery(criteriaQuery);
            Integer firstResult = (page == 0) ? 0 : ((page * size));
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(size);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchFoldersByParentID(Long parentID) throws GPDAOException {
        checkArgument(parentID != null, "The Parameter parentID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("parent").get("id"), parentID))
                    .orderBy(builder.asc(root.get("name")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param ids
     * @return {@link }
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> find(Long... ids) throws GPDAOException {
        checkArgument(ids != null, "The Parameter ids must not be null.");
        try {
            List<Long> values = Stream.of(ids).filter(id -> id != null).collect(toList());
            checkArgument((values != null) && !(values.isEmpty()),
                    "The Parameter ids must contains element.");
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(root.get("id").in(values));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @param parentID
     * @return {@link List<GPFolder>}
     * @throws GPDAOException
     */
    @Override
    public List<GPFolder> searchFoldersByProjectAndParentID(Long projectID, Long parentID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        checkArgument(parentID != null, "The Parameter parentID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPFolder> criteriaQuery = super.createCriteriaQuery();
            Root<GPFolder> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("project").get("id"), projectID),
                    builder.equal(root.join("parent").get("id"), parentID));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param matchingFolders
     * @param deltaValue
     * @return {@link Boolean}
     */
    private Boolean updatePositions(List<GPFolder> matchingFolders, int deltaValue) {
        int[] oldPositions = new int[matchingFolders.size()];
        for (int ind = matchingFolders.size() - 1; ind >= 0; ind--) {
            GPFolder folder = matchingFolders.get(ind);
            oldPositions[ind] = folder.getPosition();
            folder.setPosition(folder.getPosition() + deltaValue);
        }
        GPFolder[] foldersUpdated = super.update(matchingFolders).stream().toArray(GPFolder[]::new);

        // Check the update
        for (int ind = foldersUpdated.length - 1; ind >= 0; ind--) {
            logger.info("\n*** Position of the UPDATED GPFolder: {} ({} + {}) ***",
                    foldersUpdated[ind].getPosition(), oldPositions[ind], deltaValue);
            if ((oldPositions[ind] + deltaValue) != foldersUpdated[ind].getPosition()) {
                return FALSE;
            }
        }
        return TRUE;
    }
}