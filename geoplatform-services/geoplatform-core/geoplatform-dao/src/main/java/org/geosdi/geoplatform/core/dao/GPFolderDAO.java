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
package org.geosdi.geoplatform.core.dao;

import com.googlecode.genericdao.search.ISearch;
import java.util.List;
import java.util.Map;

import org.geosdi.geoplatform.core.model.GPFolder;

/**
 * @author Francesco Izzi - geoSDI
 *
 */
public interface GPFolderDAO {

    public List<GPFolder> findAll();

    public GPFolder find(Long id);

    public GPFolder[] find(Long[] ids);

    public void persist(GPFolder... folders);

    public GPFolder merge(GPFolder folder);

    public GPFolder[] merge(GPFolder... folder);

    public boolean remove(GPFolder folder);

    public boolean removeById(Long id);

    public List<GPFolder> search(ISearch search);

    public List<GPFolder> searchRootFolders(Long projectID);

    public int count(ISearch search);

    public GPFolder findByFolderName(String folderName);

    public boolean updatePositionsRangeInOppositeWay(Long projectID,
            int beginPositionFirstRange, int endPositionFirstRange,
            int beginPositionSecondRange, int endPositionSecondRange,
            int deltaValueFirstRange, int deltaValueSecondRange);

    public boolean updatePositionsRange(Long projectID,
            int beginPosition, int endPosition, int deltaValue);

    public boolean updatePositionsLowerBound(Long projectID,
            int lowerBoundPosition, int deltaValue);

    public boolean updateAncestorsDescendants(Map<Long, Integer> descendantsMap);

    public boolean persistCheckStatusFolder(Long folderID, boolean checked);

    public boolean persistCheckStatusFolders(boolean checked, Long... folderIDs);
}
