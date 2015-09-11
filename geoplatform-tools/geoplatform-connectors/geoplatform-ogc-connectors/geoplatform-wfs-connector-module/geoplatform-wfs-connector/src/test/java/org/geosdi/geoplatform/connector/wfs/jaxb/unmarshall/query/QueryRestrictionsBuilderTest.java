package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall.query;

import org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionsBuilderTest {

    private final static Logger logger = LoggerFactory.getLogger(QueryRestrictionsBuilderTest.class);
    //
    private static QueryDTO queryDTOAnd;
    private static QueryDTO queryDTOOr;
    private static QueryDTO queryDTONot;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String queryDTOAndStringFileName = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/query/wfsQueryAnd.xml";
        File queryDTOAndFile = new File(queryDTOAndStringFileName);
        queryDTOAnd = GPJAXBContextBuilder.newInstance().unmarshal(queryDTOAndFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_AND : {}\n\n", queryDTOAnd);
        String queryDTOOrStringFileName = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/query/wfsQueryOr.xml";
        File queryDTOOrFile = new File(queryDTOOrStringFileName);
        queryDTOOr = GPJAXBContextBuilder.newInstance().unmarshal(queryDTOOrFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_OR : {}\n\n", queryDTOOr);
        String queryDTONotStringFileName = new File(".").getCanonicalPath() + File.separator +
                "src/test/resources/unmarshall/query/wfsQueryNot.xml";
        File queryDTONotFile = new File(queryDTONotStringFileName);
        queryDTONot = GPJAXBContextBuilder.newInstance().unmarshal(queryDTONotFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_NOT : {}\n\n", queryDTOOr);
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndTest() throws Exception {
        logger.info("##################FILTER_CREATED_AND : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder().withFilterType(
                        new FilterType()).withQueryDTO(queryDTOAnd).build());

    }

    @Test
    public void wfsQueryRestrictionsBuilderOrTest() throws Exception {
        logger.info("##################FILTER_CREATED_OR : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder().withFilterType(
                        new FilterType()).withQueryDTO(queryDTOOr).build());

    }

    @Test
    public void wfsQueryRestrictionsBuilderNotTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder().withFilterType(
                        new FilterType()).withQueryDTO(queryDTONot).build());
    }
}
