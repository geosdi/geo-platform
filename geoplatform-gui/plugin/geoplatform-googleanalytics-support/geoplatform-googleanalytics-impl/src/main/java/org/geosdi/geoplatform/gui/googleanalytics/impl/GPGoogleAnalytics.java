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
package org.geosdi.geoplatform.gui.googleanalytics.impl;

import com.google.inject.Inject;
import org.geosdi.geoplatform.gui.googleanalytics.api.GoogleAnalyticsSupport;
import org.geosdi.geoplatform.gui.googleanalytics.builder.GoogleAnalyticsBuilder;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGoogleAnalytics implements GoogleAnalyticsSupport {

    @Inject
    private GoogleAnalyticsBuilder analyticsBuilder;

    @Override
    public void bind() {
        this.analyticsBuilder.buildAnalytics();
    }

    @Override
    public native void addAccount(String trackerName, String userAccount) /*-{
     $wnd._gaq.push([ '" + trackerName + "._setAccount', '" + userAccount + "' ]);
     }-*/;

    @Override
    public native void trackPageview(String pageName) /*-{
     if (!pageName.match("^/") == "/") {
     pageName = "/" + pageName;
     }

     $wnd._gaq.push([ '_trackPageview', pageName ]);
     }-*/;

    @Override
    public native void trackPageview() /*-{
     $wnd._gaq.push([ '_trackPageview' ]);
     }-*/;

    @Override
    public native void trackPageview(String trackerName, String pageName) /*-{
     if (!pageName.match("^/") == "/") {
     pageName = "/" + pageName;
     }

     $wnd._gaq.push([ '" + trackerName + "._trackPageview', pageName ]);
     }-*/;

    @Override
    public native void trackEvent(String category, String action) /*-{
     $wnd._gaq.push([ '_trackEvent', category, action ]);
     }-*/;

    @Override
    public native void trackEvent(String category, String action,
            String optLabel) /*-{
     $wnd._gaq.push([ '_trackEvent', category, action, optLabel ]);
     }-*/;

    @Override
    public native void trackEvent(String category, String action,
            String optLabel, int optValue) /*-{
     $wnd._gaq.push([ '_trackEvent', category, action, optLabel, optValue ]);
     }-*/;

    @Override
    public native void trackEvent(String category, String action,
            String optLabel, int optValue,
            boolean optNonInteraction) /*-{
     $wnd._gaq.push([ '_trackEvent', category, action, optLabel, optValue, optNonInteraction ]);
     }-*/;

    @Override
    public native void trackEventWithTracker(String trackerName, String category,
            String action) /*-{
     $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action ]);
     }-*/;

    @Override
    public native void trackEventWithTracker(String trackerName, String category,
            String action, String optLabel) /*-{
     $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel ]);
     }-*/;

    @Override
    public native void trackEventWithTracker(String trackerName, String category,
            String action,
            String optLabel, int optValue) /*-{
     $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel, optValue ]);
     }-*/;

    @Override
    public native void trackEventWithTracker(String trackerName, String category,
            String action, String optLabel,
            int optValue, boolean optNonInteraction) /*-{
     $wnd._gaq.push([ '" + trackerName + "._trackEvent', category, action, optLabel, optValue, optNonInteraction ]);
     }-*/;

}
