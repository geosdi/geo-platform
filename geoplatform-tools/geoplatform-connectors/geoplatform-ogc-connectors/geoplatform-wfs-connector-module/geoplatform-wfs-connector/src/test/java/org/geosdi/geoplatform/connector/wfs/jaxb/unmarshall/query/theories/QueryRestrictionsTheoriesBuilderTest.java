package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall.query.theories;

import org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class QueryRestrictionsTheoriesBuilderTest {

    private final static Logger logger = LoggerFactory.getLogger(QueryRestrictionsTheoriesBuilderTest.class);
    //
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = new File(".").getCanonicalPath() + File.separator + "src/test/resources/unmarshall/query/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"wfsQueryAnd.xml", "wfsQueryOr.xml", "wfsQueryNot.xml"};
    }

    @Theory
    public void wfsQueryRestrictionsBuilderTest(String file) throws Exception {
        String queryRestrictionsStringFile = dirFiles + file;
        File queryRestrictionsFile = new File(queryRestrictionsStringFile);
        logger.info("##################FILTER_CREATED : \n{}\n \n :: for File : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(queryRestrictionsFile, QueryDTO.class))
                        .build(), file);

    }
}
