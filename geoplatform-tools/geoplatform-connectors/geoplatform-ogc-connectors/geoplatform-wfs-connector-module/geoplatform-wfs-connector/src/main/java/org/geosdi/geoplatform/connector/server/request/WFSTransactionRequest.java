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
package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * WFS_110 Transaction compose of a single operation.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface WFSTransactionRequest<T> extends IGPostConnectorRequest<T> {

    /**
     * Gets the value of the transaction operation.
     */
    TransactionOperation getOperation();

    /**
     * Sets the value of the transaction operation.
     */
    void setOperation(TransactionOperation operation);

    /**
     * Sets the value of the {@link TransactionIdGen} idGen.
     */
    void setTransactionIdGen(TransactionIdGen transactionIdGen);

    /**
     * Gets the value of the {@link TransactionIdGen} idGen.
     *
     * Default Value is GenerateNew.
     */
    TransactionIdGen getTransactionIdGen();

    /**
     * Gets the value of the type name query property.
     */
    QName getTypeName();

    /**
     * Sets the value of the type name query property.
     */
    void setTypeName(QName typeName);

    /**
     * Gets the value of the SRS query property.
     */
    String getSRS();

    /**
     * Sets the value of the SRS query property.
     */
    void setSRS(String srs);

    /**
     * Gets the value of the inputFormat property.
     */
    String getInputFormat();

    /**
     * Sets the value of the inputFormat property.
     *
     * <p>Default value is "x-application/gml:3".</p>
     */
    void setInputFormat(String inputFormat);

    /**
     * Gets the value of the feature ID property.
     */
    String getFID();

    /**
     * Sets the value of the feature ID property.
     */
    void setFID(String fid);

    /**
     * Gets the value of the attributes property.
     */
    List<? extends AttributeDTO> getAttributes();

    /**
     * Sets the values of the attributes property.
     */
    void setAttributes(List<? extends AttributeDTO> attributes);
}
