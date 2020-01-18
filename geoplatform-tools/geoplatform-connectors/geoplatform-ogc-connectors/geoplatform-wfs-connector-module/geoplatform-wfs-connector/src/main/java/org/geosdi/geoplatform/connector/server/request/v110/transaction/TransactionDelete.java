/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110.transaction;

import org.geosdi.geoplatform.connector.server.request.ITransactionOperationStrategy;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.xml.filter.v110.AbstractIdType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.GmlObjectIdType;
import org.geosdi.geoplatform.xml.wfs.v110.DeleteElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.util.Arrays;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class TransactionDelete implements ITransactionOperationStrategy {

    static {
        filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    }

    static final org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory;
    //
    private static final Logger logger = LoggerFactory.getLogger(
            TransactionDelete.class);

    @Override
    public Object getOperation(WFSTransactionRequest request)
            throws Exception {
        logger.debug("\n\n###################Called getOperation for WFS "
                + "Transaction Delete Operation with Param : {}", request);

        assert (request.getTypeName() != null) : "Feature Type Name must "
                + "not be null.";

        assert (request.getFID() != null) : "Feature FID must not be null.";

        DeleteElementType elementType = new DeleteElementType();
        elementType.setTypeName(request.getTypeName());

        GmlObjectIdType fid = new GmlObjectIdType();
        fid.setId(request.getFID());

        JAXBElement<GmlObjectIdType> fidElement = filterFactory.createGmlObjectId(
                fid);

        FilterType filter = new FilterType();
        filter.setId(Arrays.<JAXBElement<? extends AbstractIdType>>asList(
                fidElement));

        elementType.setFilter(filter);

        return elementType;
    }

}
