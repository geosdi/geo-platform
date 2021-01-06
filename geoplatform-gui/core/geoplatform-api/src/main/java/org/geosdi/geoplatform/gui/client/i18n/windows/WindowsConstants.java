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
package org.geosdi.geoplatform.gui.client.i18n.windows;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface WindowsConstants extends Constants {

    public final static WindowsConstants INSTANCE = GWT.create(WindowsConstants.class);

    @DefaultStringValue("please wait...")
    String pleaseWaitText();

    @DefaultStringValue("Loading Layers.....")
    String loadingLayersText();

    @DefaultStringValue("Loading Data...")
    String loadingDataText();

    @DefaultStringValue("WARNING")
    String warningTitleText();

    @DefaultStringValue("Label")
    String labelText();

    @DefaultStringValue("Function is not yet implemented")
    String functionNotYetImplementedText();

    @DefaultStringValue("Results loaded with success")
    String resultsLoadedWithSuccessText();

    @DefaultStringValue("Operation completed with success")
    String operationCompletedWithSuccessText();

    @DefaultStringValue("No Results found")
    String noResultsFoundText();

    @DefaultStringValue("Info")
    String infoTitleText();
    
    @DefaultStringValue("ERROR")
    String errorTitleText();

    @DefaultStringValue("Error Reloading")
    String errorReloadingTitleText();

    @DefaultStringValue("Error Loading")
    String errorLoadingTitleText();

    @DefaultStringValue("Error Saving")
    String errorSavingTitleText();

    @DefaultStringValue("An Error occurred while dispatching request")
    String errorDispatchingRequestBodyText();

    @DefaultStringValue("An error occurred while making the requested connection.\n"
            + "Verify network connections and try again.\n"
            + "If the problem persists contact your system administrator.")
    String errorMakingConnectionBodyText();

    @DefaultStringValue("You can put layers into Folders only.\n"
            + "Please select the correct node from the tree.")
    String warningLayerInToFolderText();

    @DefaultStringValue("There are unsaved changes, save or reset before exit.")
    String unsavedChangesToSaveOrResetText();

    @DefaultStringValue("Warning: There are unsaved operations on the tree. ")
    String unsavedOperationsOnTreeText();
    
    @DefaultStringValue("Do you really want to leave the application?")
    String leaveApplicationQuestionText();
}
