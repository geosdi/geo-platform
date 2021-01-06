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
package org.geosdi.geoplatform.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.csw.v202.dc.elements.SimpleLiteral;
import org.geosdi.geoplatform.xml.ows.v100.BoundingBoxType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class BindingUtility {

    private BindingUtility() {
    }

    public static String convertJaxbLiteralListToString(List<JAXBElement<SimpleLiteral>> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }

        final StringBuilder str = new StringBuilder();
        for (JAXBElement<SimpleLiteral> elem : values) {
            SimpleLiteral sl = elem.getValue();
            String value = BindingUtility.convertStringListToString(sl.getContent());
            str.append(value).append(",");
        }

        BindingUtility.cleanStringBuilder(str);

        return str.toString();
    }

    // TODO Tokenizer a single value wrt "," charachter
    public static List<String> convertLiteralListToList(List<SimpleLiteral> values) {
        List<String> stringList = new ArrayList<String>(values.size());

        for (SimpleLiteral sl : values) {
            String value = BindingUtility.convertStringListToString(sl.getContent());
            stringList.add(value);
        }

        return stringList;
    }

    public static String convertLiteralListToString(List<SimpleLiteral> values) {
        StringBuilder str = new StringBuilder();

        for (SimpleLiteral sl : values) {
            String value = BindingUtility.convertStringListToString(sl.getContent());
            str.append(value);
        }

        BindingUtility.cleanStringBuilder(str);

        return str.toString();
    }

    public static String convertStringListToString(List<String> values) {
        final StringBuilder str = new StringBuilder();

        for (String string : values) {
            str.append(string).append(",");
        }

        BindingUtility.cleanStringBuilder(str);

        return str.toString();
    }

    private static StringBuilder cleanStringBuilder(StringBuilder str) {
        if (str.length() == 0) {
            return str;
        }

        while (str.lastIndexOf(",") == str.length() - 1
                || str.lastIndexOf(" ") == str.length() - 1) {
            str.deleteCharAt(str.length() - 1);
        }

        return str;
    }

    public static BBox convertBBoxTypeToBBox(BoundingBoxType bBoxType) {
        if (bBoxType == null) {
            return null;
        }

        BBox bBox = new BBox();

        bBox.setMaxX(bBoxType.getLowerCorner().get(0));
        bBox.setMinY(bBoxType.getLowerCorner().get(1));

        bBox.setMinX(bBoxType.getUpperCorner().get(0));
        bBox.setMaxY(bBoxType.getUpperCorner().get(1));

        return bBox;
    }

    /**
     * Convert the encoded CRS "urn:ogc:def:crs:EPSG:6.6:4326" 
     * to standard CRS "EPSG:4326".
     */
    public static String convertEncodedCRS(String encodedCRS) {
        if (encodedCRS == null || "".equals(encodedCRS.trim())) {
            return null;
        }
        String[] split = encodedCRS.split(":");

        int length = split.length;
        if (length < 3) {
            throw new IllegalArgumentException("The encoded CRS isn't valid");
        }

        String crs = split[length - 3] + ":" + split[length - 1];

        return crs;
    }
}
