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
package org.geosdi.geoplatform.connector.wps;

import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.xml.wps.v100.ProcessDescriptions;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.BufferedWriter;

import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class WPSDescribeProcessTest extends WPSTestConfigurator {

    @Test
    public void a_describeProcessVecTransformV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Transform").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecTransform.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void b_describeProcessRasBandSelectformV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("ras:BandSelect").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessRasBandSelect.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void c_describeProcessRasBandMergeV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("ras:BandMerge").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessRasBandMerge.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void d_describeProcessRasStyelCoverageV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("ras:StyleCoverage").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessRasStyleCoverage.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void e_describeProcessVecBufferFeatureCollectionV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:BufferFeatureCollection").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecBufferFeatureCollection.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void f_describeProcessVecPointBuffersV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:PointBuffers").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecPointBuffers.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void g_describeProcessGSVectorZonalStatisticsV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("gs:VectorZonalStatistics").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessGSVectorZonalStatistics.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void h_describeProcessVecPointStackerV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:PointStacker").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecPointStacker.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void i_describeProcessVecQueryV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Query").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecQuery.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void l_describeProcessVecRectangularClipV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:RectangularClip").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecRectangularClip.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void m_describeProcessVecReprojectV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Reproject").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecReproject.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void n_describeProcessVecSimplifyV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Simplify").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecSimplify.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void o_describeProcessVecSnapV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Snap").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecSnap.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void p_describeProcessVecToRasterV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:VectorToRaster").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcessVecToRaster.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void q_wpsDescribeMultiProcessesV100Test() throws Exception {
        WPSDescribeProcessRequest<ProcessDescriptions> describeProcessRequest = wpsServerConnector.createDescribeProcessRequest();
        describeProcessRequest.setProcessIdentifiers(of("vec:Transform", "ras:BandSelect", "ras:BandMerge",
                "ras:StyleCoverage", "vec:BufferFeatureCollection", "gs:VectorZonalStatistics",
                "vec:PointBuffers", "vec:PointStacker", "vec:Query",
                "vec:RectangularClip", "vec:Reproject", "vec:Simplify",
                "vec:Snap", "vec:VectorToRaster").collect(toList()));
        logger.debug("####################WPS_DESCRIBE_PROCESS_REUEST_V100_AS_STRING : \n{}\n", describeProcessRequest.showRequestAsString());

        String responseAsString = describeProcessRequest.formatResponseAsString(2);
        logger.debug("@@@@@@@@@@@@@@@@@@@@WPS_DESCRIBE_PROCESS_RESPONSE_V100_AS_STRING : \n{}\n", responseAsString);
        try (BufferedWriter writer = newBufferedWriter(get("target/WPSDescribeProcesses.xml"))) {
            writer.write(responseAsString);
        }
    }
}