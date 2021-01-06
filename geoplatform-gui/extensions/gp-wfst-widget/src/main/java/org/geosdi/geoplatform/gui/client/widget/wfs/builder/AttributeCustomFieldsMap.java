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
package org.geosdi.geoplatform.gui.client.widget.wfs.builder;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetMessages;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.validator.TypeValidator;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class AttributeCustomFieldsMap {

    private static final Map<String, AttributeCustomFields> CUSTOM_FIELD_MAP;
    @Inject
    static GPEventBus bus;
    static FeatureStatusBarEvent errorStatusBarEvent = new FeatureStatusBarEvent(
            "", FeatureStatusBarType.STATUS_ERROR);

    static {
        Map<String, AttributeCustomFields> map = Maps.<String, AttributeCustomFields>newHashMap();

        map.put("string", new AttributeCustomFields(getStringOperatorTypes(),
                attributeValuesValidator("string", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        return true;
                    }

                })));
        map.put("boolean", new AttributeCustomFields(getBooleanOperatorTypes(),
                attributeValuesValidator("boolean", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        if (!value.equalsIgnoreCase("true")
                                && !value.equalsIgnoreCase("false")) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("decimal", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("decimal", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        return value.matches("(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)");
                    }

                })));

        map.put("bigdecimal", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("bigdecimal", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        return value.matches("(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)");
                    }

                })));

        map.put("float", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("float", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Float.parseFloat(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("double", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("double", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Double.parseDouble(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        //
        map.put("duration", new AttributeCustomFields(getDateOperatorTypes(),
                attributeValuesValidator("duration", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
                        if (value.matches(
                                "-?P[0-9]+Y?([0-9]+M)?([0-9]+D)?(T([0-9]+H)?([0-9]+M)?([0-9]+(\\.[0-9]+)?S)?)?")
                                && value.matches("'.*[YMDHS].*'") && value.matches(
                                "'.*[^T]'")) {
                            result = true;
                        }
                        return result;
                    }

                })));
        map.put("dateTime", new AttributeCustomFields(getDateOperatorTypes(),
                attributeValuesValidator("dateTime", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
//                Pattern patter = Pattern.compile("", Pattern.CASE_INSENSITIVE);
//                patter.pattern()
                        if (value.matches("-?([1-9][0-9]{3,}|0[0-9]{3})"
                                + "-(0[1-9]|1[0-2])"
                                + "-(0[1-9]|[12][0-9]|3[01])"
                                + "T(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?|(24:00:00(\\.0+)?))"
                                + "(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?")) {
                            result = true;
                        }
                        return result;
                    }

                })));
        map.put("date", new AttributeCustomFields(getDateOperatorTypes(),
                attributeValuesValidator("date", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
//                Pattern patter = Pattern.compile("", Pattern.CASE_INSENSITIVE);
//                patter.pattern()
                        if (value.matches("-?([1-9][0-9]{3,}|0[0-9]{3})"
                                + "-(0[1-9]|1[0-2])"
                                + "-(0[1-9]|[12][0-9]|3[01])"
                                + "T(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?|(24:00:00(\\.0+)?))"
                                + "(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?")) {
                            result = true;
                        }
                        return result;
                    }

                })));
        map.put("integer", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("integer", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Integer.parseInt(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("nonPositiveInteger", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("nonPositiveInteger",
                        new TypeValidator() {

                            @Override
                            public boolean validateType(String value) {
                                boolean result = false;
                                try {
                                    int intValue = Integer.parseInt(value);
                                    result = intValue < 1;
                                } catch (NumberFormatException nfe) {
                                }
                                return result;
                            }

                        })));
        map.put("negativeInteger", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("negativeInteger",
                        new TypeValidator() {

                            @Override
                            public boolean validateType(String value) {
                                boolean result = false;
                                try {
                                    int intValue = Integer.parseInt(value);
                                    result = intValue < 0;
                                } catch (NumberFormatException nfe) {
                                }
                                return result;
                            }

                        })));
        map.put("nonNegativeInteger", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("nonNegativeInteger",
                        new TypeValidator() {

                            @Override
                            public boolean validateType(String value) {
                                boolean result = false;
                                try {
                                    int intValue = Integer.parseInt(value);
                                    result = intValue >= 0;
                                } catch (NumberFormatException nfe) {
                                }
                                return result;
                            }

                        })));
        map.put("unsignedInt", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("unsignedInt", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
                        try {
                            int intValue = Integer.parseInt(value);
                            result = intValue >= 0;
                        } catch (NumberFormatException nfe) {
                        }
                        return result;
                    }

                })));
        map.put("positiveInteger", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("positiveInteger",
                        new TypeValidator() {

                            @Override
                            public boolean validateType(String value) {
                                boolean result = false;
                                try {
                                    int intValue = Integer.parseInt(value);
                                    result = intValue > 0;
                                } catch (NumberFormatException nfe) {
                                }
                                return result;
                            }

                        })));
        map.put("unsignedShort", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("unsignedShort", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
                        try {
                            short shortValue = Short.parseShort(value);
                            result = shortValue >= 0;
                        } catch (NumberFormatException nfe) {
                        }
                        return result;
                    }

                })));
        map.put("unsignedLong", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("unsignedLong", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
                        try {
                            long longValue = Long.parseLong(value);
                            result = longValue >= 0;
                        } catch (NumberFormatException nfe) {
                        }
                        return result;
                    }

                })));
        map.put("long", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("long", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Long.parseLong(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("int", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("int", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Integer.parseInt(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("short", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("short", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Short.parseShort(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));
        map.put("byte", new AttributeCustomFields(getNumberOperatorTypes(),
                attributeValuesValidator("byte", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        boolean result = false;
                        try {
                            byte byteValue = Byte.parseByte(value);
                            result = byteValue >= 0;
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return result;
                    }

                })));
        map.put("unsignedByte", new AttributeCustomFields(
                getNumberOperatorTypes(),
                attributeValuesValidator("unsignedByte", new TypeValidator() {

                    @Override
                    public boolean validateType(String value) {
                        try {
                            Byte.parseByte(value);
                        } catch (NumberFormatException nfe) {
                            return false;
                        }
                        return true;
                    }

                })));


        CUSTOM_FIELD_MAP = Collections.<String, AttributeCustomFields>unmodifiableMap(map);
    }

    private AttributeCustomFieldsMap() {
    }

    public static AttributeCustomFields getAttributeCustomFields(
            String attributeType) {
        return CUSTOM_FIELD_MAP.get(attributeType);
    }

    public static Validator getValidatorForAttributeType(String attributeType) {
        return CUSTOM_FIELD_MAP.get(attributeType).getValidator();
    }

    public static Map<String, AttributeCustomFields> getAll() {
        return Maps.<String, AttributeCustomFields>newHashMap(CUSTOM_FIELD_MAP);
    }

    private static List<OperatorType> getStringOperatorTypes() {
        List<OperatorType> operatorTypeList = Lists.<OperatorType>newArrayList();
        operatorTypeList.add(OperatorType.EQUAL);
        operatorTypeList.add(OperatorType.STARTS_WITH);
        operatorTypeList.add(OperatorType.ENDS_WITH);
        operatorTypeList.add(OperatorType.CONTAINS);
        operatorTypeList.add(OperatorType.LIKE);
        operatorTypeList.add(OperatorType.NOT_EQUAL);
        return operatorTypeList;
    }

    private static List<OperatorType> getNumberOperatorTypes() {
        List<OperatorType> operatorTypeList = Lists.<OperatorType>newArrayList();
        operatorTypeList.add(OperatorType.EQUAL);
        operatorTypeList.add(OperatorType.GREATER);
        operatorTypeList.add(OperatorType.GREATER_OR_EQUAL);
        operatorTypeList.add(OperatorType.LESS);
        operatorTypeList.add(OperatorType.LESS_OR_EQUAL);
        operatorTypeList.add(OperatorType.NOT_EQUAL);
        return operatorTypeList;
    }

    private static List<OperatorType> getBooleanOperatorTypes() {
        List<OperatorType> operatorTypeList = Lists.<OperatorType>newArrayList();
        operatorTypeList.add(OperatorType.EQUAL);
        return operatorTypeList;
    }

    private static List<OperatorType> getDateOperatorTypes() {
        List<OperatorType> operatorTypeList = Lists.<OperatorType>newArrayList();
        operatorTypeList.add(OperatorType.LESS);
        operatorTypeList.add(OperatorType.EQUAL);
        operatorTypeList.add(OperatorType.GREATER);
        return operatorTypeList;
    }

    private static Validator attributeValuesValidator(final String dataType, final TypeValidator validator) {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                String typeName = dataType.substring(
                        dataType.lastIndexOf(".") + 1);
                if (!validator.validateType(value)) {
                    String errorValidation = WFSTWidgetMessages.INSTANCE.
                            attributeValuesErrorValidatoMessage(typeName);
                    errorStatusBarEvent.setText(errorValidation);
                    bus.fireEvent(errorStatusBarEvent);

                    return errorValidation;
                }

                return null;
            }

        };
    }
}