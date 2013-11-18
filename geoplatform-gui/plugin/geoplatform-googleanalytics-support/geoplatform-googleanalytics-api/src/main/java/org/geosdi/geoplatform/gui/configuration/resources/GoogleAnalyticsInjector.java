/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.configuration.resources;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import static org.geosdi.geoplatform.gui.configuration.resources.GPAbstractInjector.createScriptElement;
import org.geosdi.geoplatform.gui.googleanalytics.builder.GoogleAnalyticsBuilder;
import org.geosdi.geoplatform.gui.googleanalytics.config.annotations.GPGoogleAnalyticAccount;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GoogleAnalyticsInjector extends GPAbstractInjector implements
        GoogleAnalyticsBuilder {

    private final String userAccount;

    @Inject
    public GoogleAnalyticsInjector(
            @GPGoogleAnalyticAccount String theUserAccount) {
        this.userAccount = theUserAccount;
    }

    @Override
    public void buildAnalytics() {
        ScriptElement analyticsElement = createScriptElement();
        analyticsElement.setText("var _gaq = _gaq || "
                + "[];_gaq.push(['_setAccount', '" + userAccount
                + "']);_gaq.push(['_trackPageview']);");

        Element firstScript = getHead().getElementsByTagName("script").getItem(
                0);

        firstScript.getParentNode().insertBefore(analyticsElement, firstScript);


        ScriptElement script = createScriptElement();

        script.setSrc(("https:".equals(Window.Location.getProtocol())
                ? "https://ssl" : "http://www")
                + ".google-analytics.com/ga.js");

        script.setType("text/javascript");
        script.setAttribute("async", "true");

        firstScript.getParentNode().insertBefore(script, firstScript);
    }

}
