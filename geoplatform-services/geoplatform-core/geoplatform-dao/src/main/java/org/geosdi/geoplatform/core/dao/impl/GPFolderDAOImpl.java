//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.core.dao.impl;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.model.GPFolder;

/**
 * @author Francesco Izzi - geoSDI
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 * 
 */
@Transactional
public class GPFolderDAOImpl extends BaseDAO<GPFolder, Long> implements
        GPFolderDAO {

    @Override
    public GPFolder[] find(Long[] ids) {
        return super.find(ids);
    }

    @Override
    public void persist(GPFolder... folders) {
        super.persist(folders);
    }

    @Override
    public GPFolder merge(GPFolder folder) {
        return super.merge(folder);
    }

    @Override
    public GPFolder[] merge(GPFolder... folders) {
        return super.merge(folders);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GPFolder> search(ISearch search) {
        return super.search(search);
    }

    @Override
    public boolean remove(GPFolder folder) {
        return super.remove(folder);
    }

    @Override
    public GPFolder findByFolderName(String folderName) {
        Search search = new Search();
        search.addFilterEqual("name", folderName);
        return searchUnique(search);
    }

    @Override
    public boolean updatePositionsRange(int beginPosition, int endPosition,
            int deltaValue) {
        assert (beginPosition < endPosition) : "beginPosition must be lesser than endPosition";
        assert (deltaValue != 0) : "deltaValue does not be 0";
        // Select the folders of interest (position >= beginP && position <= endP)
        Search search = new Search();
        search.addFilterGreaterOrEqual("position", beginPosition).
                addFilterLessOrEqual("position", endPosition);
        List<GPFolder> matchingFolders = super.search(search);

        logger.debug("\n*** UPDATE Folders with Position: {} to {} [# {}] *** deltaValue = {} ***",
                new Object[]{beginPosition, endPosition, endPosition - beginPosition + 1, deltaValue});
        logger.debug("\n*** Matching Folders count: {} ***", matchingFolders.size());

        // No updates (select 0 folders)
        if (matchingFolders.isEmpty()) {
            return false;
        }
        return this.updatePositions(matchingFolders, deltaValue);
    }

    @Override
    public boolean updatePositionsLowerBound(int lowerBoundPosition, int deltaValue) {
        assert (deltaValue != 0) : "deltaValue does not be 0";
        // Select the folders of interest (position >= lowerBoundP)
        Search search = new Search();
        search.addFilterGreaterOrEqual("position", lowerBoundPosition);
        List<GPFolder> matchingFolders = super.search(search);

        logger.debug("\n*** UPDATE Folders with Position from {} *** deltaValue = {} ***",
                new Object[]{lowerBoundPosition, deltaValue});
        logger.debug("\n*** Matching Folders count: {} ***", matchingFolders.size());

        // No updates (select 0 folders)
        if (matchingFolders.isEmpty()) {
            return false;
        }
        return this.updatePositions(matchingFolders, deltaValue);
    }

    private boolean updatePositions(List<GPFolder> matchingFolders, int deltaValue) {
        // Update
        int[] oldPositions = new int[matchingFolders.size()];
        for (int ind = matchingFolders.size() - 1; ind >= 0; ind--) {
            GPFolder folder = matchingFolders.get(ind);
            oldPositions[ind] = folder.getPosition();
            folder.setPosition(folder.getPosition() + deltaValue);
        }
        GPFolder[] foldersUpdated = merge(matchingFolders.toArray(new GPFolder[matchingFolders.size()]));

        // Check the update
        for (int ind = foldersUpdated.length - 1; ind >= 0; ind--) {
            logger.trace("\n*** Position of the UPDATED GPFolder: {} ({} + {}) ***", new Object[]{
                        foldersUpdated[ind].getPosition(), oldPositions[ind], deltaValue});
            if ((oldPositions[ind] + deltaValue) != foldersUpdated[ind].getPosition()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateAncestorsDescendants(Map<Long, Integer> descendantsMap) {
        assert (descendantsMap != null) : "descendantsMap does not be NULL";
        // No descendants to update // TODO: assert?
        if (descendantsMap.isEmpty()) {
            return false;
        }
        // Select the folders of interest (wrt the set od ID)
        Search search = new Search();
        search.addFilterIn("id", descendantsMap.keySet());
        List<GPFolder> matchingFolders = super.search(search);
        logger.debug("\n*** Matching Folders count: {} ***", matchingFolders.size());

        // No updates (select 0 folders)
        if (matchingFolders.isEmpty()) {
            return false;
        }

        int[] oldDescendants = new int[matchingFolders.size()]; // Only for log
        for (int ind = 0; ind < matchingFolders.size(); ind++) {
            GPFolder folder = matchingFolders.get(ind);
            oldDescendants[ind] = folder.getNumberOfDescendants();
            int newNumberOfDescendants = descendantsMap.get(folder.getId());
            folder.setNumberOfDescendants(newNumberOfDescendants);
        }
        GPFolder[] foldersUpdated = merge(matchingFolders.toArray(new GPFolder[matchingFolders.size()]));

        // Check the update
        for (int ind = 0; ind < foldersUpdated.length; ind++) {
            long id = foldersUpdated[ind].getId();
            int numberOfDescendants = foldersUpdated[ind].getNumberOfDescendants();
            logger.trace("\n*** Number of Descentans of the UPDATED GPFolder: {} (OLD Descentans = {}) ***",
                    numberOfDescendants, oldDescendants[ind]);
            if (descendantsMap.get(id) != numberOfDescendants) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean persistCheckStatusFolder(long idFolder, boolean isChecked) {
        // Retrieve the folder
        GPFolder folder = this.find(idFolder);
        if (folder == null) {
            logger.debug("\n*** The Folder with ID \"{}\" is NOT exist into DB ***", idFolder);
            return false;
        }
        logger.trace("\n*** Folder RETRIEVED:\n{}\n*** MOD checked to {} ***", folder, isChecked);

        // Merge iff the check status is different
        if (folder.isChecked() != isChecked) {
            folder.setChecked(isChecked);

            GPFolder folderUpdated = this.merge(folder);

            if (folderUpdated.isChecked() != isChecked) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean persistCheckStatusFolders(boolean isChecked, Long... idFolders) {
        for (Long longIth : idFolders) {
            boolean checkSave = this.persistCheckStatusFolder(longIth, isChecked);
            if (!checkSave) {
                logger.debug("\n*** The Folder with ID \"{}\" is has NOT changed the check***", longIth);
                return false;
            }
        }
        return true;
    }
}