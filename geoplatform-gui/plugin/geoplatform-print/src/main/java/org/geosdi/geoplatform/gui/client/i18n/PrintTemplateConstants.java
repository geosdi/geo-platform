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
package org.geosdi.geoplatform.gui.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface PrintTemplateConstants extends Constants {

    public static final PrintTemplateConstants INSTANCE =
            GWT.create(PrintTemplateConstants.class);

    @DefaultStringValue("A4 Portrait")
    String A4_Portrait();

    @DefaultStringValue("A3 Portrait")
    String A3_Portrait();

    @DefaultStringValue("A2 Portrait")
    String A2_Portrait();

    @DefaultStringValue("A1 Portrait")
    String A1_Portrait();

    @DefaultStringValue("A0 Portrait")
    String A0_Portrait();

    @DefaultStringValue("A0 plus Portrait")
    String A0P_Portrait();

    @DefaultStringValue("A4 Landscape")
    String A4_Landscape();

    @DefaultStringValue("A3 Landscape")
    String A3_Landscape();

    @DefaultStringValue("A2 Landscape")
    String A2_Landscape();

    @DefaultStringValue("A1 Landscape")
    String A1_Landscape();

    @DefaultStringValue("A0 Landscape")
    String A0_Landscape();

    @DefaultStringValue("A0 plus Landscape")
    String A0P_Landscape();
}
