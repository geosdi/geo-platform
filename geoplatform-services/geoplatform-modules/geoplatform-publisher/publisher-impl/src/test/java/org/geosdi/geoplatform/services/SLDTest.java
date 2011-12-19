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

import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.SLDParser;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.filter.FilterFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath:applicationContext.xml"})
public class SLDTest {

    protected Logger logger = LoggerFactory.getLogger(SLDTest.class);
    static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();
    static FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();
    @Autowired
    private GeoServerRESTReader restReader;

    @Test
    public void testSLD() {
        String sldBody = this.restReader.getSLD("capitals");
        try {
            Reader reader = new StringReader(sldBody);
            Style style = this.createFromSLD(reader);
            Symbolizer sym = style.getDefaultSpecification();
            Map<String, String> options = sym.getOptions();
            System.out.println("SLD Body: " + sym.hasOption("Opacity"));
        } catch (Exception nep) {
            logger.error("Error on performing SLD Test: " + nep + " maybe the "
                    + "Geoserver is unavailable?");
        }
    }

    /**
     * Figure out if a valid SLD file is available.
     */
    private File toSLDFile(File file) {
        String path = file.getAbsolutePath();
        String base = path.substring(0, path.length() - 4);
        String newPath = base + ".sld";
        File sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        newPath = base + ".SLD";
        sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        return null;
    }

    /**
     * Create a Style object from a definition in a SLD document
     */
    private Style createFromSLD(Reader sld) {
        try {
            SLDParser stylereader = new SLDParser(styleFactory, sld);
//            SLDParser stylereader = new SLDParser(styleFactory, sld.toURI().toURL());
            Style[] style = stylereader.readXML();
            return style[0];

        } catch (Exception e) {
            System.out.println("Exception on creating sld: " + e.toString());
//            JExceptionReporter.showDialog(e, "Problem creating style");
        }
        return null;
    }
}
