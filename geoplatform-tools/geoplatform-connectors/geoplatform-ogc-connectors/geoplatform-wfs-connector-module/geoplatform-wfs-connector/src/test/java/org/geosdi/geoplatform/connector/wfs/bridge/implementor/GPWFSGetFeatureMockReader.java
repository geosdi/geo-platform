/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wfs.bridge.implementor;

import com.google.common.io.CharStreams;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWFSGetFeatureReader;
import org.geosdi.geoplatform.connector.server.request.GPWFSGetFeatureOutputFormat;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWFSGetFeatureMockReader implements GPWFSGetFeatureReader<String> {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureMockReader.class);
    //
    public static final GPWFSGetFeatureOutputFormat MOCK_OUTUPUT_FORMAT = new GPWFSGetFeatureOutputFormat() {

        @Override
        public String getOutputFormat() {
            return "mock_output_format";
        }

        @Override
        public GPWFSGetFeatureOutputFormat getImplementorKey() {
            return this;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    };

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public String read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        logger.debug("##########################Executing {}#read.", this);
        return CharStreams.toString(new InputStreamReader(inputStream, UTF_8));
    }

    /**
     * @return {@link GPImplementorKey<GPWFSGetFeatureOutputFormat>}
     */
    @Override
    public GPImplementorKey<GPWFSGetFeatureOutputFormat> getKey() {
        return MOCK_OUTUPUT_FORMAT;
    }

    /**
     * <p>Specify if {@link GPImplementor} is Valid or Not.</p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return TRUE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}