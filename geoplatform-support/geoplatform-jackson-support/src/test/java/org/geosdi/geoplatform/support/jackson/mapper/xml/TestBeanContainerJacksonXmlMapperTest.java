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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.TestBean;
import org.geosdi.geoplatform.support.jackson.model.TestBeanContainer;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.StringReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestBeanContainerJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<TestBeanContainer> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(TestBeanContainer.class, new GPJacksonXmlSupport(JAXB));

    @Test
    @Order(value = 0)
    @DisplayName(value = "readTestBeanContainerFromString")
    public void unmarshallTestBeanContainerFromStringTest() throws Exception {
        logger.info("############################TEST_BEAN_CONTAINER_FROM_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version=\"1.0\"?>\n"
                + "<Tests xmlns=\"http://www.adatum.com\">\n"
                + "    <Test TestId=\"0001\" TestType=\"CMD\">\n"
                + "        <Name>Convert number to string</Name>\n"
                + "        <CommandLine>Examp1.EXE</CommandLine>\n"
                + "        <Input>1</Input>\n"
                + "        <Output>One</Output>\n"
                + "    </Test>\n"
                + "    <Test TestId=\"0002\" TestType=\"CMD\">\n"
                + "        <Name>Find succeeding characters</Name>\n"
                + "        <CommandLine>Examp2.EXE</CommandLine>\n"
                + "        <Input>abc</Input>\n"
                + "        <Output>def</Output>\n"
                + "    </Test>\n"
                + "    <Test TestId=\"0003\" TestType=\"GUI\">\n"
                + "        <Name>Convert multiple numbers to strings</Name>\n"
                + "        <CommandLine>Examp2.EXE /Verbose</CommandLine>\n"
                + "        <Input>123</Input>\n"
                + "        <Output>One Two Three</Output>\n"
                + "    </Test>\n"
                + "    <Test TestId=\"0004\" TestType=\"GUI\">\n"
                + "        <Name>Find correlated key</Name>\n"
                + "        <CommandLine>Examp3.EXE</CommandLine>\n"
                + "        <Input>a1</Input>\n"
                + "        <Output>b1</Output>\n"
                + "    </Test>\n"
                + "    <Test TestId=\"0005\" TestType=\"GUI\">\n"
                + "        <Name>Count characters</Name>\n"
                + "        <CommandLine>FinalExamp.EXE</CommandLine>\n"
                + "        <Input>This is a test</Input>\n"
                + "        <Output>14</Output>\n"
                + "    </Test>\n"
                + "    <Test TestId=\"0006\" TestType=\"GUI\">\n"
                + "        <Name>Another Test</Name>\n"
                + "        <CommandLine>Examp2.EXE</CommandLine>\n"
                + "        <Input>Test Input</Input>\n"
                + "        <Output>10</Output>\n"
                + "    </Test>\n"
                + "</Tests>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallTestBeanContainerFromFileTest() throws Exception {
        logger.info("#######################TEST_BEAN_CONTAINER_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "tests.xml").collect(joining(separator)))));
    }

    @Test
    @Order(value = 2)
    public void marshallTestBeanContainerAsStringTest() throws Exception {
        logger.info("###################TEST_BEAN_CONTAINER_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(TestBeanContainerJacksonXmlMapperTest::testBeanContainer));
    }

    /**
     * @return {@link TestBeanContainer}
     */
    public static TestBeanContainer testBeanContainer() {
        return new TestBeanContainer() {
            {
                super.setTests(prepareTestBean(50));
            }
        };
    }

    /**
     * @param numbers
     * @return {@link List<TestBean>}
     */
    static List<TestBean> prepareTestBean(int numbers) {
        checkArgument((numbers > 0), "The Number of Cd must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(numbers)
                .boxed()
                .map(TestBeanContainerJacksonXmlMapperTest::toTestBean)
                .collect(toList());
    }

    /**
     * @param theValue
     * @return {@link TestBean}
     */
    static TestBean toTestBean(@Nonnull(when = NEVER) Integer theValue) {
        checkArgument(theValue != null, "The Parameter value must not be null");
        return new TestBean() {
            {
                super.setTestId("TEST_ID#".concat(theValue.toString()));
                super.setTestType("TEST_TYPE#".concat(theValue.toString()));
                super.setName("NAME#".concat(theValue.toString()));
                super.setCommandLine("COMMAND_LINE#".concat(theValue.toString()));
                super.setInput("INPUT#".concat(theValue.toString()));
                super.setOutput("OUTPUT#".concat(theValue.toString()));
            }
        };
    }
}