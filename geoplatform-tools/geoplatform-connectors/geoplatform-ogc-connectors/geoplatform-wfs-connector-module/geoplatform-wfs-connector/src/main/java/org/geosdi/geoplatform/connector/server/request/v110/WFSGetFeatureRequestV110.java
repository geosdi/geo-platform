/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110;

import org.geosdi.geoplatform.connector.bridge.implementor.GPWFSGetFeatureReader;
import org.geosdi.geoplatform.connector.bridge.store.GPWFSGetFeatureReaderStore;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.request.GPWFSGetFeatureOutputFormat;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.geosdi.geoplatform.xml.wfs.v110.QueryType;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GML_311;
import static org.geosdi.geoplatform.connector.server.request.v110.param.GPWFSGetFeatureRequestParamChain.wfsGetFeatureRequestParamChain;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.fromValue;

/**
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde - <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureRequestV110 extends AbstractGetFeatureRequest<Object, GetFeatureType> {

    private static final GPWFSGetFeatureReaderStore store = new GPWFSGetFeatureReaderStore();

    /**
     * @param server
     */
    public WFSGetFeatureRequestV110(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link GetFeatureType}
     * @throws Exception
     */
    @Override
    protected GetFeatureType createRequest() throws Exception {
        checkArgument(this.typeName != null, "The Parameter typeName must not be null.");
        GetFeatureType request = new GetFeatureType();
        QueryType query = new QueryType();
        query.setTypeName(asList(typeName));
        request.getQuery().add(query);
        wfsGetFeatureRequestParamChain().applyParam(this, query);
        request.setResultType(this.isSetResultType() ? fromValue(resultType) : RESULTS);
        request.setOutputFormat(this.isSetOutputFormat() ? outputFormat.getOutputFormat() : GML_311.getOutputFormat());
        if (this.isSetMaxFeatures()) {
            request.setMaxFeatures(maxFeatures);
        }
        return request;
    }

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    protected final Object readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        GPWFSGetFeatureOutputFormat outpuFormat = this.isSetOutputFormat() ? this.outputFormat : GML_311;
        GPWFSGetFeatureReader<?> reader = store.getImplementorByKey(outpuFormat);
        checkArgument(reader != null, "There is no GetFeatureReader for outputFormat : " + outpuFormat.getOutputFormat());
        return reader.read(inputStream);
    }
}