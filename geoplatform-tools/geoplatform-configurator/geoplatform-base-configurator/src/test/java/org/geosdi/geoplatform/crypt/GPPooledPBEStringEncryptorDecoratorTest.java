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
package org.geosdi.geoplatform.crypt;

import org.geosdi.geoplatform.configurator.crypt.GPPooledPBEStringEncryptorDecorator;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Michele Santomauro
 * @email michele.santomauro@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-jasypt.xml"})
public class GPPooledPBEStringEncryptorDecoratorTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPPooledPBEStringEncryptorDecorator pooledPBEStringEncryptorDecorator;
    //
    private final String plainText = "anonymous";

    @Test
    public void testEncryptedStringMatch1() {
        logger.trace("\n\t@@@ testEncryptedStringMatch1 @@@");
        String encryptedText = pooledPBEStringEncryptorDecorator.encrypt(plainText);
        boolean result = pooledPBEStringEncryptorDecorator.matches(encryptedText, this.plainText);
        Assert.assertTrue("Error because decrypted and plain texts does not match", result);
    }

    @Test
    public void testEncryptedStringMatch2() {
        logger.trace("\n\t@@@ testEncryptedStringMatch2 @@@");
        String encryptedTextFirstStep = pooledPBEStringEncryptorDecorator.encrypt(plainText);
        String encryptedTextSecondStep = pooledPBEStringEncryptorDecorator.encrypt(plainText);
        Assert.assertFalse("Error because encryptedTextFirstStep match encryptedTextSecondStep",
                encryptedTextFirstStep.equalsIgnoreCase(encryptedTextSecondStep));

        boolean resultFirstStep = pooledPBEStringEncryptorDecorator.matches(
                encryptedTextFirstStep, this.plainText);
        Assert.assertTrue(
                "Error because decrypted and plain texts for first step does not match",
                resultFirstStep);

        boolean resultSecondStep = pooledPBEStringEncryptorDecorator.matches(
                encryptedTextSecondStep, this.plainText);
        Assert.assertTrue(
                "Error because decrypted and plain texts for second step does not match",
                resultSecondStep);
    }

    @Test
    public void testEncryptedStringNotMatch1() {
        logger.trace("\n\t@@@ testEncryptedStringNotMatch1 @@@");
        boolean result = true;
        try {
            result = pooledPBEStringEncryptorDecorator.matches(this.plainText, this.plainText);
            Assert.fail("Error because is possible to decrypt a plain text");
        } catch (EncryptionOperationNotPossibleException e) {
            Assert.assertTrue("Error because decrypted and plain text match",
                    result);
        }
    }

    @Test
    public void testEncryptedStringNotMatch2() {
        logger.trace("\n\t@@@ testEncryptedStringNotMatch2 @@@");
        String otherPlainText = "guest";
        String otherEncryptedText = this.pooledPBEStringEncryptorDecorator.encrypt(otherPlainText);

        boolean result = pooledPBEStringEncryptorDecorator.matches(otherEncryptedText, this.plainText);
        Assert.assertFalse("Error because decrypted and plain text match", result);
    }

    @Test
    public void decryptTest() throws Exception {
        logger.info("#########################ECCOLA : {}\n", this.pooledPBEStringEncryptorDecorator.encrypt("admin"));
    }
}