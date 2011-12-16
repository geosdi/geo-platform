/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform.services;

import it.geosolutions.geonetwork.GNClient;
import it.geosolutions.geonetwork.exception.GNLibException;
import it.geosolutions.geonetwork.exception.GNServerException;
import it.geosolutions.geonetwork.util.GNInsertConfiguration;
import it.geosolutions.geonetwork.util.GNPrivConfiguration;
import it.geosolutions.geonetwork.util.GNSearchRequest;
import it.geosolutions.geonetwork.util.GNSearchResponse;
import java.io.File;
import javax.jws.WebService;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPCatalogFinderService")
public class GPCatalogFinderServiceImpl implements GPCatalogFinderService {

    protected Logger logger = LoggerFactory.getLogger(
            GPCatalogFinderServiceImpl.class);
    private static final String gnServiceURL = "http://localhost:8080/geonetwork";
    private static final String gnUsername = "admin";
    private static final String gnPassword = "admin";

    @Override
    public void searchMetadata() {
        try {
            logger.debug("Searching metadata on geoNetwork");

            GNSearchRequest searchRequest = new GNSearchRequest();

            // add a predefined search field
            searchRequest.addParam(GNSearchRequest.Param.any, "your search string");
            // add a custom param
            searchRequest.addParam("customParam", "custom");

            // only local results
            searchRequest.addConfig(GNSearchRequest.Config.remote, "off");

            // Init your GN client (and perform login if required)
            GNClient client = new GNClient(gnServiceURL);

            // do the search!
            GNSearchResponse searchResponse = client.search(searchRequest);

            if (searchResponse.getCount() != 0) {
                // loop on all metadata
                for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                    Long id = metadata.getId();
                    // and this is the full metadata document, as a JDOM element.
                    Element md = client.get(id);
                    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
                    logger.info("Metadata -> " + out.outputString(md));
                }
            }
        } catch (GNLibException ex) {
            logger.error(ex.getMessage());
        } catch (GNServerException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void insertMetadata() {
        try {
            // Create a GeoNetwork client pointing to the GeoNetwork service
            GNClient client = new GNClient(gnServiceURL);

            // Perform a login into GN
            boolean logged = client.login(gnUsername, gnPassword);

            if (!logged) {
                throw new RuntimeException("Could not log in");
            }

            // Create a configuration for the metadata.
            // These params are the ones needed by the GN service.
            GNInsertConfiguration cfg = new GNInsertConfiguration();
            cfg.setCategory("datasets");
            cfg.setGroup("1"); // group 1 is usually "all"
            cfg.setStyleSheet("_none_");
            cfg.setValidate(Boolean.FALSE);

            File file = new File("/your/path/here/metadata.xml");

            long id = client.insertMetadata(cfg, file);

            logger.info("Metadata created with id " + id);
        } catch (GNLibException ex) {
            logger.error(ex.getMessage());
        } catch (GNServerException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void resetPrivileges() {
        try {
            GNClient client = new GNClient(gnServiceURL);
            if (!client.login(gnUsername, gnPassword)) {
                throw new RuntimeException("Could not log in");
            }

            long metadataID = -1;

            GNPrivConfiguration pcfg = new GNPrivConfiguration();
            pcfg.addPrivileges(0, "012356");
            pcfg.addPrivileges(1, "012356");
            pcfg.addPrivileges(2, "012356");
            pcfg.addPrivileges(3, "012356");
            pcfg.addPrivileges(4, "012356");

            client.setPrivileges(metadataID, pcfg);
        } catch (GNLibException ex) {
            logger.error(ex.getMessage());
        } catch (GNServerException ex) {
            logger.error(ex.getMessage());
        }
    }
}
