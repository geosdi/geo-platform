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

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface PrintModuleConstants extends Constants {

    public static final PrintModuleConstants INSTANCE =
            GWT.create(PrintModuleConstants.class);

    @DefaultStringValue("Print")
    String printText();

    /**
     * start GPPrintWidget
     */
    @DefaultStringValue("Warning: only for 4326 base map!")
    String GPPrintWidget_checkBoxPrintBaseMapTooltipText();

    @DefaultStringValue("Print Base Map")
    String GPPrintWidget_checkBoxPrintBaseMapFieldLabelText();

    @DefaultStringValue("Base Map")
    String GPPrintWidget_checkPrintFieldSetHeadingText();

    @DefaultStringValue("Select Scale")
    String GPPrintWidget_comboScaleFieldLabelText();

    @DefaultStringValue("Scale")
    String GPPrintWidget_scaleFieldSetHeadingText();

    @DefaultStringValue("Template")
    String GPPrintWidget_templateFieldSetHeadingText();

    @DefaultStringValue("Select Template")
    String GPPrintWidget_comboTemplateFieldLabelText();

    @DefaultStringValue("Select DPI")
    String GPPrintWidget_comboDPIFieldLabelText();

    @DefaultStringValue("Choose DPI...")
    String GPPrintWidget_comboDPIEmptyText();

    @DefaultStringValue("DPI")
    String GPPrintWidget_DPIFieldSetHeadingText();

    @DefaultStringValue("Comments")
    String GPPrintWidget_commentsLabelText();

    @DefaultStringValue("Map Title")
    String GPPrintWidget_mapTitleLabelText();

    @DefaultStringValue("Title")
    String GPPrintWidget_titleLabelText();

    @DefaultStringValue("Edit Print Settings")
    String GPPrintWidget_editFieldSetHeadingText();

    @DefaultStringValue("Print Finished")
    String GPPrintWidget_infoFinishPrintBodyText();

    @DefaultStringValue("Print Started")
    String GPPrintWidget_infoStartPringBodyText();

    @DefaultStringValue("GeoPlatform Print Widget")
    String GPPrintWidget_headingText();

    @DefaultStringValue("Please Select Layers to print in GPLayerWidget")
    String GPPrintWidget_warningMessageLayersEmptyListText();

    @DefaultStringValue("GeoPlatform Print Warning")
    String GPPrintWidget_warningMessageLayersEmptyListHeaderText();

    /**
     * start PrintLayersAction
     */
    @DefaultStringValue("Print Visible Layers")
    String PrintLayersAction_tooltipText();
}
