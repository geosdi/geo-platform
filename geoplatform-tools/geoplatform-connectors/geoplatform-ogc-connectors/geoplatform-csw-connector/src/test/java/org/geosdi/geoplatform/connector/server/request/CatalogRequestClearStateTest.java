/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigInteger;
import java.net.URI;

import static org.geosdi.geoplatform.connector.GPCSWConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguage.CQL_TEXT;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion.V110;
import static org.geosdi.geoplatform.xml.csw.OutputSchema.CSW_V202;
import static org.geosdi.geoplatform.xml.csw.OutputSchema.GMD;
import static org.geosdi.geoplatform.xml.csw.TypeName.RECORD_V202;
import static org.geosdi.geoplatform.xml.csw.v202.ElementSetType.FULL;
import static org.geosdi.geoplatform.xml.csw.v202.ResultType.RESULTS;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Verifies the opt-in {@code clearState()} lifecycle method of the CSW catalog requests : after configuring a
 * request through the fluent {@code withXxx(...)} mutators, an explicit {@code clearState()} releases the
 * per-thread {@code ThreadLocal} configuration, so a shared request instance does not retain per-thread values
 * on pooled threads. {@code clearState()} is never invoked automatically (the request state must survive the
 * whole request/response lifecycle), so it is exercised here directly. No network call is performed.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogRequestClearStateTest {

    private static GPCatalogConnectorStore serverConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        serverConnector = newConnector()
                .withServerUrl(new URI("http://localhost:8080/geonetwork/srv/eng/csw").toURL())
                .build();
    }

    @Test
    public void a_getRecordsStateIsClearedByClearState() throws Exception {
        CatalogGetRecords<?, ?> request = (CatalogGetRecords<?, ?>) serverConnector.createGetRecordsRequest();
        request.withTypeName(RECORD_V202)
                .withOutputSchema(CSW_V202)
                .withElementSetName(FULL.value())
                .withResultType(RESULTS.value())
                .withConstraintLanguage(CQL_TEXT)
                .withConstraintLanguageVersion(V110)
                .withConstraint("AnyText LIKE '%clearState%'")
                .withStartPosition(BigInteger.ONE)
                .withMaxRecords(BigInteger.TEN);
        assertSame(RECORD_V202, request.getTypeName());
        assertSame(CSW_V202, request.getOutputSchema());
        assertSame(BigInteger.TEN, request.getMaxRecords());

        request.clearState();

        assertNull("typeName must be released by clearState", request.getTypeName());
        assertNull("outputSchema must be released by clearState", request.getOutputSchema());
        assertNull("elementSetName must be released by clearState", request.getElementSetName());
        assertNull("resultType must be released by clearState", request.getResultType());
        assertNull("constraintLanguage must be released by clearState", request.getConstraintLanguage());
        assertNull("constraintLanguageVersion must be released by clearState", request.getConstraintLanguageVersion());
        assertNull("constraint must be released by clearState", request.getConstraint());
        assertNull("startPosition must be released by clearState", request.getStartPosition());
        assertNull("maxRecords must be released by clearState", request.getMaxRecords());
        assertNull("catalogFinder must be released by clearState", request.getCatalogFinder());
    }

    @Test
    public void b_getRecordByIdStateIsClearedByClearState() throws Exception {
        CatalogGetRecordById<?, ?> request = (CatalogGetRecordById<?, ?>) serverConnector.createGetRecordByIdRequest();
        request.withId("7e418dac-3764-4290-b8ac-47c9ac2a12af")
                .withOutputSchema(GMD)
                .withElementSetType(FULL.value());
        assertSame(GMD, request.outputSchema.get());

        request.clearState();

        assertNull("id must be released by clearState", request.id.get());
        assertNull("outputSchema must be released by clearState", request.outputSchema.get());
        assertNull("elementSetType must be released by clearState", request.elementSetType.get());
    }

    @Test
    public void c_withXxxAfterClearStateReInitializesTheState() throws Exception {
        CatalogGetRecords<?, ?> request = (CatalogGetRecords<?, ?>) serverConnector.createGetRecordsRequest();
        request.withTypeName(RECORD_V202).withMaxRecords(BigInteger.TEN);
        request.clearState();
        request.withMaxRecords(BigInteger.ONE);

        assertNull("clearState must not be undone for the untouched properties", request.getTypeName());
        assertSame("a withXxx after clearState must re-initialize the state", BigInteger.ONE, request.getMaxRecords());
    }
}
