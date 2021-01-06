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
package org.geosdi.geoplatform.gui.regex;

import com.google.gwt.regexp.shared.RegExp;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPRegEx {

    /**
     * Regular Expressions.
     * Note: the whitespace must not be present.
     */
    // Query String of a WMS URL - Fields required
    public static final RegExp RE_REQUEST = RegExp.compile(GetMap.REQUEST + "=GetMap", "i"); // "i" --> ignore case
    public static final RegExp RE_VERSION = RegExp.compile(GetMap.VERSION + "=1\\.(0\\.0|1\\.0|1\\.1|3\\.0)", "i");
    public static final RegExp RE_LAYERS = RegExp.compile(GetMap.LAYERS + "=\\w+(,\\w+)*", "i");
    public static final RegExp RE_SRS = RegExp.compile(GetMap.SRS + "=\\w+(-\\w+)?:\\d+", "i");
    public static final RegExp RE_BBOX = RegExp.compile(GetMap.BBOX + "=-?\\d+(\\.\\d+)?(,-?\\d+(\\.\\d+)?){3}", "i");
    public static final RegExp RE_WIDTH = RegExp.compile(GetMap.WIDTH + "=\\d+", "i");
    public static final RegExp RE_HEIGHT = RegExp.compile(GetMap.HEIGHT + "=\\d+", "i");
    public static final RegExp RE_FORMAT = RegExp.compile(GetMap.FORMAT + "=image/(png|gif|jpeg)", "i");
    // KML URL
    public static final RegExp RE_FUSION_TABLES_URL = RegExp.compile("http://www\\.google\\.com/fusiontables/exporttable\\?");
    public static final RegExp RE_FUSION_TABLES_QS_QUERY = RegExp.compile("query=select[ \\+]col\\d+[ \\+]from[ \\+]\\d+[ \\+]"); // URL Encoding replace + with a space
    public static final RegExp RE_FUSION_TABLES_QS_O = RegExp.compile("o=kmllink");
    public static final RegExp RE_FUSION_TABLES_QS_G = RegExp.compile("g=col\\d+");
    // URL
    private static final RegExp RE_FORMAT_QS = RegExp.compile("(\\?|&)", "g"); // "g" --> replaceAll
    //
    private static final RegExp RE_EMPTY_STRING = RegExp.compile("^\\s*$");
    // User properties
    public static final RegExp RE_COMPLETE_NAME = RegExp.compile("[^ ]+ [^ ]+");
    public static final RegExp RE_EMAIL = RegExp.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", "i"); // i --> case insensitive
    public static final RegExp RE_USERNAME = RegExp.compile("[a-zA-Z0-9._-]{4,}$");

    private GPRegEx() {
    }

    public static String printPrettyURL(String urlEncoding) {
        return RE_FORMAT_QS.replace(urlEncoding, "\n$1");
    }

    /**
     * Checks if value is null or empty.
     * 
     * @param value
     */
    public static boolean empty(String value) {
        if (value == null) {
            return true;
        }
        return RE_EMPTY_STRING.test(value);
    }
}
