/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.validator;

import com.google.common.collect.Maps;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TypeValidatorController {

    public static final Map<String, TypeValidator> MAP_VALIDATOR;

    private TypeValidatorController() {
    }

    static {
        Map<String, TypeValidator> map = Maps.newHashMap();

        map.put("object", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                return true;
            }
        });
        map.put("string", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                return true;
            }
        });
//        map.put("java.math.BigInteger", new TypeValidator() {
//            @Override
//            public boolean validateType(String value) {
//                try {
//                    new BigInteger(value);
//                } catch (NumberFormatException nfe) {
//                    return false;
//                }
//                return true;
//            }
//        });
        map.put("int", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("long", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Long.parseLong(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("short", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Short.parseShort(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("bigDecimal", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    new BigDecimal(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("float", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Float.parseFloat(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("double", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Double.parseDouble(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });
        map.put("boolean", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                if (!value.equalsIgnoreCase("true")
                        && !value.equalsIgnoreCase("false")) {
                    return false;
                }
                return true;
            }
        });
        map.put("byte", new TypeValidator() {
            @Override
            public boolean validateType(String value) {
                try {
                    Byte.parseByte(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                return true;
            }
        });

        MAP_VALIDATOR = Collections.unmodifiableMap(map);
    }
}
