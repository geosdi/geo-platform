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
package org.geosdi.geoplatform.jasypt.support;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-TEST.xml"})
public class GPDigesterTest {

    private final static String plainText = "geosdi";
    private final static String encryptedTextMD5 = "c5e78595a2c9c515c6d218549bc6873d";
    private final static String encryptedTextSHA1 = "87f969449681b7674a7bda1a8988f413c16a16a7";
    //
    @Resource(name = "gpDigesterSHA1")
    private GPDigesterConfigurator gpDigesterSHA1;
    //
    @Resource(name = "gpDigesterMD5")
    private GPDigesterConfigurator gpDigesterMD5;

    @Test
    public void testMD5() {
        String encrypted = gpDigesterMD5.digest(plainText).toLowerCase();

        Assert.assertEquals(32, encrypted.length());
        Assert.assertEquals(encryptedTextMD5, encrypted);
    }

    @Test
    public void testSHA1() {
        String encrypted = gpDigesterSHA1.digest(plainText).toLowerCase();

        Assert.assertEquals(40, encrypted.length());
        Assert.assertEquals(encryptedTextSHA1, encrypted);
    }

}
