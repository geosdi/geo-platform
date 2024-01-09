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
package org.geosdi.geoplatform.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.geosdi.geoplatform.persistence.demo.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static java.lang.String.valueOf;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibernateJpaNativeTest {

    private static final Logger logger = LoggerFactory.getLogger(HibernateJpaNativeTest.class);
    //
    private static EntityManager entityManager;
    private final static String PART_NAME = "Gearbox";

    @Before
    public void setUp() throws Exception {
        entityManager = Persistence.createEntityManagerFactory("geoplatform-persistence-layer-test").createEntityManager();
    }

    @Test
    public void a_simpleTest() throws Exception {
        Transaction transaction;
        Session session = (Session) entityManager.getDelegate();
        // start a transaction
        transaction = session.beginTransaction();
        try {
            for (int i = 0; i < 100; i++) {
                Car car = new Car();
                car.setPlate("AR793" + i);
                car.setModel("Fiat Model " + i);
                CarPart carPart = new CarPart();
                carPart.setPartName(PART_NAME + i);
                carPart.setCar(car);
                session.save(car);
                session.save(carPart);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Test
    public void b_simpleTest() throws Exception {
        Transaction transaction;
        Session session = (Session) entityManager.getDelegate();
        // start a transaction
        transaction = session.beginTransaction();
        Organization organization = new Organization();
        organization.setName("SIMPLE_TEST");
        try {
            session.persist(organization);
            for (int i = 0; i < 100; i++) {
                DebitAccount debitAccount = new DebitAccount();
                debitAccount.setBalance(BigDecimal.valueOf(34));
                debitAccount.setOwner("owner_test".concat(valueOf(i)));
                debitAccount.setOverdraftFee(BigDecimal.valueOf(12));
                debitAccount.setOrganization(organization);
                CreditAccount creditAccount = new CreditAccount();
                creditAccount.setCreditLimit(BigDecimal.valueOf(1000));
                creditAccount.setOwner("simple_owner_test");
                creditAccount.setOrganization(organization);
                session.persist(debitAccount);
                session.persist(creditAccount);
            }
            // commit transaction
            session.remove(organization);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
        }
    }
}