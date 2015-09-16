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
import java.io.StringReader;

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
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(queryDTOAnd)
                        .build());

    }

    @Test
    public void wfsQueryRestrictionsBuilderOrTest() throws Exception {
        logger.info("##################FILTER_CREATED_OR : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(queryDTOOr)
                        .build());

    }

    @Test
    public void wfsQueryRestrictionsBuilderNotTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(queryDTONot)
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotStringTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_FROM_STRING : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>CONTAINS</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_GREATHER : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_GREATHER_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_LESS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_LESS_OR_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>WORKERS</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>double</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>LESS_OR_EQUAL</operator>\n" +
                                                "            <restriction>0.25</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>EQUAL</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_EQUAL : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>EQUAL</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_CONTAINS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>CONTAINS</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_CONTAINS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>CONTAINS</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_CONTAINS : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>CONTAINS</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderAndStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_START_WITH : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ALL</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>STARTS_WITH</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderOrStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_START_WITH : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>ANY</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>STARTS_WITH</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }

    @Test
    public void wfsQueryRestrictionsBuilderNotStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_START_WITH : {}\n",
                ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                        .withFilterType(new FilterType())
                        .withQueryDTO(GPJAXBContextBuilder.newInstance()
                                .unmarshal(new StringReader(
                                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                                "<QueryDTO>\n" +
                                                "    <matchOperator>NONE</matchOperator>\n" +
                                                "    <queryRestrictionList>\n" +
                                                "        <queryRestriction>\n" +
                                                "            <attribute>\n" +
                                                "                <maxOccurs>1</maxOccurs>\n" +
                                                "                <minOccurs>0</minOccurs>\n" +
                                                "                <name>SUB_REGION</name>\n" +
                                                "                <nillable>true</nillable>\n" +
                                                "                <type>string</type>\n" +
                                                "                <value></value>\n" +
                                                "            </attribute>\n" +
                                                "            <operator>STARTS_WITH</operator>\n" +
                                                "            <restriction>Mtn</restriction>\n" +
                                                "        </queryRestriction>\n" +
                                                "    </queryRestrictionList>\n" +
                                                "</QueryDTO>"), QueryDTO.class))
                        .build());
    }
}
