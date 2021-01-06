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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import static org.junit.Assert.assertEquals;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.StandardStringDigester;
import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPHashFunctionTest {

    private final static String plainText = "geosdi";
    private final static String encryptedTextMD5 = "c5e78595a2c9c515c6d218549bc6873d";
    private final static String encryptedTextSHA1 = "87f969449681b7674a7bda1a8988f413c16a16a7";

    /**********
     * Java
     **********/
    @Test
    public void generateJavaMD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] plainBytes = plainText.getBytes();
        byte[] digest = md.digest(plainBytes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        final String encrypted = sb.toString();

        assertEquals(32, encrypted.length());
        assertEquals(encryptedTextMD5, encrypted);
    }

    @Test
    public void generateJavaSHA1() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] plainBytes = plainText.getBytes();
        byte[] digest = md.digest(plainBytes);

        Formatter formatter = new Formatter();
        for (byte b : digest) {
            formatter.format("%02x", b);
        }

        String encrypted = formatter.toString();

        assertEquals(40, encrypted.length());
        assertEquals(encryptedTextSHA1, encrypted);
    }

    /**********
     * Spring
     **********/
    @Test
    public void generateSpringMD5() {
        byte[] plainBytes = plainText.getBytes();
        byte[] digest = DigestUtils.md5Digest(plainBytes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        final String encrypted = sb.toString();

        assertEquals(32, encrypted.length());
        assertEquals(encryptedTextMD5, encrypted);
    }

    /**********
     * Jasypt
     **********/
    @Test
    public void generateJasyptMD5() {
        StandardStringDigester digester = new StandardStringDigester();
        digester.setAlgorithm("MD5");
        digester.setSaltSizeBytes(0);
        digester.setIterations(1);
        digester.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_HEXADECIMAL);

        String encrypted = digester.digest(plainText).toLowerCase();

        assertEquals(32, encrypted.length());
        assertEquals(encryptedTextMD5, encrypted);
    }

    @Test
    public void generateJasyptSHA1() {
        StandardStringDigester digester = new StandardStringDigester();
        digester.setAlgorithm("SHA-1");
        digester.setSaltSizeBytes(0);
        digester.setIterations(1);
        digester.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_HEXADECIMAL);

        String encrypted = digester.digest(plainText).toLowerCase();

        assertEquals(40, encrypted.length());
        assertEquals(encryptedTextSHA1, encrypted);
    }
}
