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
package org.geosdi.geoplatform.services;

import it.geosolutions.geonetwork.GNClient;
import it.geosolutions.geonetwork.exception.GNLibException;
import it.geosolutions.geonetwork.exception.GNServerException;
import it.geosolutions.geonetwork.util.GNSearchRequest;
import it.geosolutions.geonetwork.util.GNSearchResponse;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.geosdi.geoplatform.exception.GPCatalogException;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.services.util.GPCatalogClient;
import org.geosdi.geoplatform.services.util.GPCatalogMetadataLoader;
import org.jdom.Element;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPCatalogFinderService")
public class GPCatalogFinderServiceImpl implements GPCatalogFinderService {

    @Autowired
    private GPCatalogClient gpCatalogClient;
    //
    @Autowired
    private GPCatalogMetadataLoader gpCatalogMetadataLoader;

    @Override
    public List<GPCatalogMetadataDTO> searchPublicMetadata(String searchText) throws GPCatalogException {
        try {
            GNClient client = gpCatalogClient.createClientWithoutCredentials();

            GNSearchResponse searchResponse = searchMetadata(client, searchText);

            // loop on all metadata
            List<GPCatalogMetadataDTO> catalogMetadataDTOList = new ArrayList<GPCatalogMetadataDTO>();
            for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                Long id = metadata.getId();

                // and this is the full metadata document, as a JDOM element.
                Element metadataElement = client.get(id);

                GPCatalogMetadataDTO gpCatalogMetadataDTO = this.gpCatalogMetadataLoader.getGPCatalogMetadataDTO(metadataElement);
                catalogMetadataDTOList.add(gpCatalogMetadataDTO);
            }

            return catalogMetadataDTOList;
        } catch (GNLibException ex) {
            throw new GPCatalogException(ex.getMessage());
        } catch (GNServerException ex) {
            throw new GPCatalogException(ex.getMessage());
        }
    }

    @Override
    public List<GPCatalogMetadataDTO> searchPrivateMetadataWithCredentials(
            String username, String password, String searchText) throws GPCatalogException {
        try {
            GNClient client = gpCatalogClient.createClientWithCredentials();

            GNSearchResponse searchResponse = searchMetadata(client, searchText);

            // loop on all metadata
            List<GPCatalogMetadataDTO> catalogMetadataDTOList = new ArrayList<GPCatalogMetadataDTO>();
            for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                Long id = metadata.getId();

                // and this is the full metadata document, as a JDOM element.
                Element metadataElement = client.get(id);

                GPCatalogMetadataDTO gpCatalogMetadataDTO = this.gpCatalogMetadataLoader.getGPCatalogMetadataDTO(metadataElement);
                catalogMetadataDTOList.add(gpCatalogMetadataDTO);
            }

            return catalogMetadataDTOList;
        } catch (GNLibException ex) {
            throw new GPCatalogException(ex.getMessage());
        } catch (GNServerException ex) {
            throw new GPCatalogException(ex.getMessage());
        }
    }

    private GNSearchResponse searchMetadata(GNClient client, String searchText)
            throws GNLibException, GNServerException {
        GNSearchRequest searchRequest = new GNSearchRequest();

        // add a predefined search field
        searchRequest.addParam(GNSearchRequest.Param.any, searchText);
        // add a custom param
//            searchRequest.addParam("customParam", "custom");

        // only local results
//            searchRequest.addConfig(GNSearchRequest.Config.remote, "off");

        // do the search!
        GNSearchResponse searchResponse = client.search(searchRequest);
        return searchResponse;
    }
}
