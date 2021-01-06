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
package org.geosdi.geoplatform.xml.csw;

import org.geosdi.geoplatform.xml.context.provider.GPContextServiceProvider;

import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CSWContextServiceProviderV202 extends GPContextServiceProvider {

    public static final GPContextServiceProvider CSW_CONTEXT_SERVICE_PROVIDER = new CSWContextServiceProviderV202();

    protected CSWContextServiceProviderV202() {
        super(String.join(":", "org.geosdi.geoplatform.xml.ows.v100",
                "org.geosdi.geoplatform.xml.filter.v110", "org.geosdi.geoplatform.xml.gml.v311",
                "org.geosdi.geoplatform.xml.gml.w3._2001.smil20", "org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language",
                "org.geosdi.geoplatform.xml.csw.v202", "org.geosdi.geoplatform.xml.csw.v202.dc.elements",
                "org.geosdi.geoplatform.xml.csw.v202.dc.terms", "org.geosdi.geoplatform.xml.gml.v321",
                "org.geosdi.geoplatform.xml.iso19139.v20070417.gco", "org.geosdi.geoplatform.xml.iso19139.v20070417.gmd",
                "org.geosdi.geoplatform.xml.iso19139.v20070417.gmx", "org.geosdi.geoplatform.xml.iso19139.v20070417.gsr",
                "org.geosdi.geoplatform.xml.iso19139.v20070417.gss", "org.geosdi.geoplatform.xml.iso19139.v20070417.gts",
                "org.geosdi.geoplatform.xml.gfc"), of(org.geosdi.geoplatform.xml.ows.v100.ObjectFactory.class,
                org.geosdi.geoplatform.xml.filter.v110.ObjectFactory.class,
                org.geosdi.geoplatform.xml.gml.v311.ObjectFactory.class,
                org.geosdi.geoplatform.xml.gml.w3._2001.smil20.ObjectFactory.class,
                org.geosdi.geoplatform.xml.gml.w3._2001.smil20.language.ObjectFactory.class,
                org.geosdi.geoplatform.xml.csw.v202.ObjectFactory.class,
                org.geosdi.geoplatform.xml.csw.v202.dc.elements.ObjectFactory.class,
                org.geosdi.geoplatform.xml.csw.v202.dc.terms.ObjectFactory.class,
                org.geosdi.geoplatform.xml.gml.v321.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gco.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gsr.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gss.ObjectFactory.class,
                org.geosdi.geoplatform.xml.iso19139.v20070417.gts.ObjectFactory.class,
                org.geosdi.geoplatform.xml.gfc.ObjectFactory.class).toArray(size -> new Class[size]));
    }

    /**
     * @return {@link GPContextProviderType}
     */
    @Override
    public final GPContextProviderType getType() {
        return CSWContextProviderTypeV202.CSW_202;
    }
}
