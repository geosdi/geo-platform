/*
 *  GeoBatch - Open Source geospatial batch processing system
 *  http://geobatch.geo-solutions.it/
 *  Copyright (C) 2007-2013 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.geosdi.geoplatform.services.utility;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ETj (etj at geo-solutions.it)
 */
public class FeatureConfigurationUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeatureConfigurationUtil.class);

    /**
     * @return the datastore, or null if none found or exception encountered
     */
    public static DataStore createDataStore(FeatureConfiguration config) {

        Map<String, Serializable> connect = config.getDataStore();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("DataStore connection parameters:");
            for (String connectKey : connect.keySet()) {
                Serializable value = connect.get(connectKey);
                if (connectKey.equalsIgnoreCase("pw")
                        || connectKey.equalsIgnoreCase("password")
                        || connectKey.equalsIgnoreCase("passwd")) {
                    value = "***HIDDEN***";
                }
                LOGGER.info("     " + connectKey + " -> " + value);
            }
        }
        DataStore dataStore = null;
        try {
            dataStore = DataStoreFinder.getDataStore(connect);
            if (dataStore == null) {
                LOGGER.warn("Cannot connect to DataStore: wrong parameters");
            }
        } catch (IOException ex) {
            LOGGER.warn("Error searching datastore", ex);
        }

        return dataStore;
    }
}
