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

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;

import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Francesco Izzi - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * 
 */
@Transactional
public class GPLayerDAOImpl extends BaseDAO<GPLayer, Long>
        implements GPLayerDAO {

    @Override
    public void persist(GPLayer... layers) {
        super.persist(layers);
    }

    @Override
    public GPLayer merge(GPLayer layer) {
        return super.merge(layer);
    }

    @Override
    public GPLayer[] merge(GPLayer... layers) {
        return super.merge(layers);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GPLayer> search(ISearch search) {
        return super.search(search);
    }

    @Override
    public boolean remove(GPLayer layer) {
        return super.remove(layer);
    }

    @Override
    public GPLayer findByLayerName(String layerName) {
        Search search = new Search();
        search.addFilterEqual("name", layerName);
        return searchUnique(search);
    }

    @Override
    public ArrayList<String> findDistinctDataSourceByProjectId(Long projectID) {
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addField("urlServer");
        search.setDistinct(true);
        return (ArrayList) search(search);
    }

    @Override
    public boolean updatePositionsRangeInOppositeWay(Long projectID,
            int beginPositionFirstRange, int endPositionFirstRange,
            int beginPositionSecondRange, int endPositionSecondRange,
            int deltaValueFirstRange, int deltaValueSecondRange) {
        assert (projectID != null) : "projectID must be not null";
        assert (projectID > 0) : "projectID must be greater than zero";
        assert (beginPositionFirstRange > 0) : "beginPositionFirstRange must be greater than zero";
        assert (beginPositionSecondRange > 0) : "beginPositionSecondRange must be greater than zero";
        assert (beginPositionFirstRange < endPositionFirstRange) : "beginPositionFirstRange must be lesser than endPositionFirstRange";
        assert (beginPositionSecondRange < endPositionSecondRange) : "beginPositionSecondRange must be lesser than endPositionSecondRange";
        assert (endPositionFirstRange > beginPositionSecondRange) : "endPositionFirstRange must be greater than beginPositionSecondRange";
        assert (deltaValueFirstRange != 0) : "deltaValueFirstRange does not be 0";
        assert (deltaValueSecondRange != 0) : "deltaValueSecondRange does not be 0";
        // Select the layers of interest (first range)
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterGreaterOrEqual("position", beginPositionFirstRange).
                addFilterLessOrEqual("position", endPositionFirstRange);
        List<GPLayer> matchingLayerFirstRange = super.search(search);

        logger.debug("\n*** UPDATE First Range Layer with Position: {} to {} [# {}] *** deltaValue = {} ***",
                beginPositionFirstRange, endPositionFirstRange, endPositionFirstRange - beginPositionFirstRange + 1, deltaValueFirstRange);
        logger.debug("\n*** Matching First Range Layer count: {} ***", matchingLayerFirstRange.size());

        // TODO DEL
        for (GPLayer layer : matchingLayerFirstRange) {
            logger.trace("\n*** First Range\n{}\n***", layer);
        }

        // Select the layers of interest (second range)
        search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterGreaterOrEqual("position", beginPositionSecondRange).
                addFilterLessOrEqual("position", endPositionSecondRange);
        List<GPLayer> matchingLayerSecondRange = super.search(search);

        logger.debug("\n*** UPDATE Second Range Layer with Position: {} to {} [# {}] *** deltaValue = {} ***",
                beginPositionSecondRange, endPositionSecondRange, endPositionSecondRange - beginPositionSecondRange + 1, deltaValueSecondRange);
        logger.debug("\n*** Matching Second Range Layer count: {} ***", matchingLayerSecondRange.size());

        // TODO DEL
        for (GPLayer layer : matchingLayerSecondRange) {
            logger.trace("\n*** Second Range\n{}\n***", layer);
        }

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

        List<GPLayer> matchingLayers = new ArrayList<GPLayer>();
        matchingLayers.addAll(matchingLayerFirstRange);
        matchingLayers.addAll(matchingLayerSecondRange);
        GPLayer[] layersUpdated = merge(matchingLayers.toArray(new GPLayer[matchingLayers.size()]));

        // TODO DEL
        for (GPLayer layer : layersUpdated) {
            logger.trace("\n*** GPLayer merged\n{}\n***", layer);
        }

        // Check the update
        for (int ind = 0; ind < matchingLayerFirstRange.size(); ind++) {
            logger.trace("\n*** Check Position - First Range: {} ({} + {}) ***", 
                        layersUpdated[ind].getPosition(), oldPositionsFirstRange[ind], deltaValueFirstRange);
            if ((oldPositionsFirstRange[ind] + deltaValueFirstRange) != layersUpdated[ind].getPosition()) {
                return false;
            }
        }
        int indSplit = matchingLayerFirstRange.size();
        for (int ind = 0; ind < matchingLayerSecondRange.size(); ind++) {
            logger.trace("\n*** Check Position - Second Range: {} ({} + {}) ***", 
                        layersUpdated[ind + indSplit].getPosition(), oldPositionsSecondRange[ind], deltaValueSecondRange);
            if ((oldPositionsSecondRange[ind] + deltaValueSecondRange) != layersUpdated[ind + indSplit].getPosition()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updatePositionsRange(Long projectID,
            int beginPosition, int endPosition, int deltaValue) {
        assert (projectID != null) : "projectID must be not null";
        assert (projectID > 0) : "projectID must be greater than zero";
        assert (beginPosition <= endPosition) : "beginPosition must be lesser than or equal endPosition";
        assert (deltaValue != 0) : "deltaValue does not be 0";
        // Select the layers of interest
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterGreaterOrEqual("position", beginPosition).
                addFilterLessOrEqual("position", endPosition);
        List<GPLayer> matchingLayers = super.search(search);

        logger.debug("\n*** Layers with Position in range: {} to {} [# {}] *** deltaValue = {} ***",
                beginPosition, endPosition, endPosition - beginPosition + 1, deltaValue);
        logger.debug("\n*** Matching Layers count: {} ***", matchingLayers.size());

        // No updates (select 0 layers)
        if (matchingLayers.isEmpty()) {
            return true;
        }
        return this.updatePositions(matchingLayers, deltaValue);
    }

    @Override
    public boolean updatePositionsLowerBound(Long projectID,
            int lowerBoundPosition, int deltaValue) {
        assert (projectID != null) : "projectID must be not null";
        assert (projectID > 0) : "projectID must be greater than zero";
        assert (deltaValue != 0) : "deltaValue does not be 0";
        // Select the layers of interest
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterGreaterOrEqual("position", lowerBoundPosition);
        List<GPLayer> matchingLayers = super.search(search);

        logger.debug("\n*** UPDATE Layers with Position from {} *** deltaValue = {} ***",
                lowerBoundPosition, deltaValue);
        logger.debug("\n*** Matching Layers count: {} ***", matchingLayers.size());

        // No updates (select 0 layers)
        if (matchingLayers.isEmpty()) {
            return true;
        }
        return this.updatePositions(matchingLayers, deltaValue);
    }

    private boolean updatePositions(List<GPLayer> matchingLayers, int deltaValue) {
        // Update position of selected elements
        int[] oldPositions = new int[matchingLayers.size()];
        for (int ind = matchingLayers.size() - 1; ind >= 0; ind--) {
            GPLayer layer = matchingLayers.get(ind);
            oldPositions[ind] = layer.getPosition();
            layer.setPosition(layer.getPosition() + deltaValue);
        }
        GPLayer[] layersUpdated = merge(matchingLayers.toArray(new GPLayer[matchingLayers.size()]));

        // Check the update
        for (int ind = layersUpdated.length - 1; ind >= 0; ind--) {
            logger.trace("\n*** Position of the UPDATED GPLayer: {} ({} + {}) ***", 
                        layersUpdated[ind].getPosition(), oldPositions[ind], deltaValue);
            if ((oldPositions[ind] + deltaValue) != layersUpdated[ind].getPosition()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean persistCheckStatusLayer(Long layerID, boolean checked) {
        // Retrieve the layer
        GPLayer layer = this.find(layerID);
        if (layer == null) {
            logger.debug("\n*** The Layer with ID \"{}\" does NOT exist into DB ***", layerID);
            return false;
        }
        logger.trace("\n*** Layer RETRIEVED:\n{}\n*** MOD checked to {} ***", layer, checked);

        // Merge iff the check status is different
        if (layer.isChecked() != checked) {
            layer.setChecked(checked);

            GPLayer layerUpdated = this.merge(layer);

            if (layerUpdated.isChecked() != checked) {
                return false;
            }
        }
        return true;
    }
}
