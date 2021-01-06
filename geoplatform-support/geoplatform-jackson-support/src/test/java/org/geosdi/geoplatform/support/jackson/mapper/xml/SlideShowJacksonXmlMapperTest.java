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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.Slide;
import org.geosdi.geoplatform.support.jackson.model.SlideShow;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SlideShowJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(SlideShowJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<SlideShow> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(SlideShow.class,
            new GPJacksonXmlSupport().configure(NON_NULL));

    @Test
    public void a_writeSlideShowAsXmlStringTest() throws Exception {
        logger.info("\n{}\n", GP_JACKSON_XML_MAPPER.writeAsString(SlideShowJacksonXmlMapperTest::slideShow));
    }

    @Test
    public void b_readSlideShowFromXmlStringTest() throws Exception {
        SlideShow slideShow = GP_JACKSON_XML_MAPPER.read("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<slideshow title=\"Sample Slide Show\" date=\"Date of publication\" author=\"Yours Truly\">\n"
                + "   <!--  TITLE SLIDE  -->\n"
                + "   <slide type=\"all\">\n"
                + "      <title>Wake up to WonderWidgets!</title>\n"
                + "   </slide>\n"
                + "   <!--  OVERVIEW  -->\n"
                + "   <slide type=\"all\">\n"
                + "      <title>Overview</title>\n"
                + "      <item>\n"
                + "         Why\n"
                + "         WonderWidgets"
                + "         are great\n"
                + "      </item>\n"
                + "      <item />\n"
                + "      <item>\n"
                + "         Who\n"
                + "         buys"
                + "         WonderWidgets\n"
                + "      </item>\n"
                + "   </slide>\n"
                + "</slideshow>");
        assertNotNull(slideShow);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@SLIDE_SHOW_FROM_XML_STRING : {}\n", slideShow);
    }

    @Test
    public void c_readSlideShowXmlFromFileTest() throws Exception {
        SlideShow slideShow = GP_JACKSON_XML_MAPPER.read(new File(of("src", "test", "resources", "slide_show.xml")
                .collect(joining(separator))));
        assertNotNull(slideShow);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@SLIDE_SHOW_FROM_URL : {}\n", slideShow);
    }

    /**
     * @return {@link SlideShow}
     */
    public static SlideShow slideShow() {
        SlideShow slideShow = new SlideShow();
        slideShow.setAuthor("Test");
        slideShow.setDate("DATE_TEST");
        slideShow.setTitle("TITLE_TEST");
        Slide slide = new Slide();
        slide.setType("ALL");
        slide.setTitle("Wake up to WonderWidgets!");
        Slide slide1 = new Slide();
        slide1.setType("ALL");
        slide1.setTitle("Overview");
        slide1.setItem(asList("pippo", "pluto"));
        slideShow.setSlide(asList(slide, slide1));
        return slideShow;
    }
}