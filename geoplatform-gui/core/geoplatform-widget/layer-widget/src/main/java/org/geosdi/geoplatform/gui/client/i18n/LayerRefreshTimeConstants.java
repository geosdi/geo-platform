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
public interface LayerRefreshTimeConstants extends Constants {

    public final static LayerRefreshTimeConstants INSTANCE = 
            GWT.create(LayerRefreshTimeConstants.class);

    @DefaultStringValue("NO REFRESH")
    String NO_REFRESH();

    @DefaultStringValue("5 SECONDS")
    String FIVE_SECONDS();

    @DefaultStringValue("10 SECONDS")
    String TEN_SECONDS();
    
    @DefaultStringValue("20 SECONDS")
    String TWENTY_SECONDS();

    @DefaultStringValue("30 SECONDS")
    String THIRTY_SECONDS();

    @DefaultStringValue("45 SECONDS")
    String FORTY_FIVE_SECONDS();
    
    @DefaultStringValue("1 MINUTE")
    String ONE_MINUTE();

    @DefaultStringValue("5 MINUTES")
    String FIVE_MINUTES();

    @DefaultStringValue("15 MINUTES")
    String FIFTEEN_MINUTES();

    @DefaultStringValue("30 MINUTES")
    String THIRTY_MINUTES();

    @DefaultStringValue("1 HOUR")
    String ONE_HOUR();

    @DefaultStringValue("3 HOURS")
    String THREE_HOURS();

    @DefaultStringValue("6 HOURS")
    String SIX_HOURS();
}
