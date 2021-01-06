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
package org.geosdi.geoplatform.gui.client.configuration.getmap.choise.widget.kvp;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.dispatcher.IWmsGetMapDispatcher;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.encoder.GetMapUrlEncoder;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.widget.kvp.responsibility.BasicKvpUrlManagerHandler;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapDisableStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapIncorrectStatusEvent;
import org.geosdi.geoplatform.gui.client.widget.form.LoadWmsGetMapFromUrlWidget;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class KvpUrlTextField extends GPSecureStringTextField {

    @Inject
    private GetMapUrlEncoder urlEncoder;
    @Inject
    private IWmsGetMapDispatcher wmsGetMapDispatcher;
    @Inject
    private BasicKvpUrlManagerHandler kvpUrlManagerHandler;
    @Inject
    private WmsGetMapIncorrectStatusEvent incorrectStatusEvent;
    @Inject
    private WmsGetMapDisableStatusEvent disableStatusEvent;
    private final NotificationPlugin notificationPlugin;

    @Inject
    public KvpUrlTextField(KvpUrlNotificationPlugin theNotificationPlugin) {
        this.notificationPlugin = theNotificationPlugin;

        super.addPlugin(notificationPlugin);
        setWidgetProperties();
    }

    protected final void setWidgetProperties() {
        setFieldLabel(LayerModuleConstants.INSTANCE.urlLabelText());

        sinkEvents(Event.ONPASTE);

        addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if ((getValue() == null) && (event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                        || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                    resetField();
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && checkUrl()) {
                    wmsGetMapDispatcher.verifyUrl(true,
                            urlEncoder.getUrlEncoding());
                }
            }

        });
    }

    private void resetField() {
        this.clear();
        LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                disableStatusEvent);
        notificationPlugin.resetPlugin();
    }

    private Boolean checkUrl() {
        return this.kvpUrlManagerHandler.checkKvpUrl(this.getValue());
    }

    @Override
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONPASTE:
                event.stopPropagation();
                fireDelayed();
                break;
        }
    }

    private void fireDelayed() {
        Timer t = new Timer() {

            @Override
            public void run() {
                if (checkUrl()) {
                    wmsGetMapDispatcher.verifyUrl(false,
                            urlEncoder.getUrlEncoding());
                } else {
                    LoadWmsGetMapFromUrlWidget.fireWmsGetMapFromUrlEvent(
                            incorrectStatusEvent);
                }
            }

        };
        t.schedule(2);
    }

}
