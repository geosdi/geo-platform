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
package org.geosdi.geoplatform.connector.server.request.v100;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WPSBaseDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.v100.function.ProcessIdentifierFunction;
import org.geosdi.geoplatform.xml.ows.v110.CodeType;
import org.geosdi.geoplatform.xml.wps.v100.DescribeProcess;
import org.geosdi.geoplatform.xml.wps.v100.ProcessDescriptions;

import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSDescribeProcessRequestV100 extends WPSBaseDescribeProcessRequest<ProcessDescriptions, DescribeProcess> {

    private static Function<String, CodeType> PROCESS_IDENTIFIER = new ProcessIdentifierFunction();

    /**
     * @param server
     */
    public WPSDescribeProcessRequestV100(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link DescribeProcess}
     * @throws Exception
     */
    @Override
    protected DescribeProcess createRequest() throws Exception {
        checkArgument(this.isSetProcessIdentifiers(),
                "The Parameter ProcessIdentifiers must not be null or Empty.");
        List<CodeType> processCodeTypes = this.processIdentifiers.stream()
                .filter(value -> ((value != null) && !(value.trim().isEmpty())))
                .map(PROCESS_IDENTIFIER)
                .collect(toList());
        checkArgument((processCodeTypes != null) && !(processCodeTypes.isEmpty()),
                "The Parameter ProcessCodeTypes must not be null or an Empty List.");
        DescribeProcess describeProcess = new DescribeProcess();
        describeProcess.setIdentifier(processCodeTypes);
        if (this.isLanguageSet())
            describeProcess.setLanguage(this.language);
        return describeProcess;
    }
}
