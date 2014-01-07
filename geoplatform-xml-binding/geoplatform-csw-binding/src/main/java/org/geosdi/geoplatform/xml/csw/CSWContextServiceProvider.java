/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.xml.csw;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CSWContextServiceProvider {

    /**
     * Load All Classes for CSW JAXB Context
     *
     * @return Classes[]
     */
    public static Class[] loadClasses() {
        return getAllClasses();
    }

    /**
     *
     * @return Context Path for CSW Context
     */
    public static String loadContextPath() {
        return getFullContextPath();
    }

    private static String getFullContextPath() {
        StringBuilder builder = new StringBuilder();

        builder.append("org.geosdi.geoplatform.xml.ows.v100");
        builder.append(":");
        builder.append("org.geosdi.geoplatform.xml.filter.v110");
        builder.append(":");
        builder.append("org.geosdi.geoplatform.xml.gml.v311").append(":");
        builder.append("org.geosdi.geoplatform.xml.gml.w3._2001.smil20").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language");
        builder.append(":");
        builder.append("org.geosdi.geoplatform.xml.csw.v202").append(":");
        builder.append("org.geosdi.geoplatform.xml.csw.v202.dc.elements").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.csw.v202.dc.terms");
        builder.append(":");
        builder.append("org.geosdi.geoplatform.xml.gml.v321");
        builder.append(":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gco").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gmd").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gmx").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gsr").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gss").append(
                ":");
        builder.append("org.geosdi.geoplatform.xml.iso19139.v20070417.gts");
        builder.append(":");
        // ISO 19110
        builder.append("org.geosdi.geoplatform.xml.gfc");

        return builder.toString();
    }

    private static Class[] getAllClasses() {
        List<Class> allClasses = new ArrayList<Class>();
        allClasses.add(org.geosdi.geoplatform.xml.ows.v100.ObjectFactory.class);

        allClasses.add(
                org.geosdi.geoplatform.xml.filter.v110.ObjectFactory.class);

        allClasses.add(org.geosdi.geoplatform.xml.gml.v311.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.gml.w3._2001.smil20.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.ObjectFactory.class);

        allClasses.add(org.geosdi.geoplatform.xml.csw.v202.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.csw.v202.dc.elements.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.csw.v202.dc.terms.ObjectFactory.class);

        allClasses.add(org.geosdi.geoplatform.xml.gml.v321.ObjectFactory.class);

        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gco.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gsr.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gss.ObjectFactory.class);
        allClasses.add(
                org.geosdi.geoplatform.xml.iso19139.v20070417.gts.ObjectFactory.class);

        allClasses.add(org.geosdi.geoplatform.xml.gfc.ObjectFactory.class);

        return allClasses.toArray(new Class[allClasses.size()]);
    }
}
