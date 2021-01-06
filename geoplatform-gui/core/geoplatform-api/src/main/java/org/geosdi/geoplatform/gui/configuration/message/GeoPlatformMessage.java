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
package org.geosdi.geoplatform.gui.configuration.message;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.grid.IGeoPlatformGrid;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author giuseppe
 *
 */
public class GeoPlatformMessage {

    /**
     * Display a confirmation Message in the Application
     *
     * @param title
     * @param message
     */
    public static void okMessage(String title, String message) {
        MessageBox.info(title, message, new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent be) {
            }
        });
    }
    
    /**
     * Display an Alert Message in the Application
     *
     * @param title
     * @param message
     */
    public static void alertMessage(String title, String message) {
        MessageBox.alert(title, message, new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent be) {
            }
        });
    }

    /**
     * Display an Error Message in the Application
     *
     * @param title
     * @param message
     */
    public static void errorMessage(String title, String message) {
        MessageBox box = new MessageBox();
        box.setIcon(MessageBox.ERROR);
        box.setTitleHtml(title);
        box.setMessage(message);
        box.show();
    }

    /**
     * Display an Info Message in the Application
     *
     * @param title
     * @param message
     */
    public static void infoMessage(String title, String message) {
        Info.display(title, message);
    }

    /**
     * Check GeoPlatformGridWidget Status
     *
     * @param widget
     * @param title
     * @param message
     */
    public static void checkGridWidgetStatus(
            final IGeoPlatformGrid<GeoPlatformBeanModel> widget,
            final String title, final String message) {

        Timer timer = new Timer() {

            @Override
            public void run() {
                if (widget.getGrid().getView().getBody().isMasked()) {
                    MessageBox.confirm(title, message,
                            new Listener<MessageBoxEvent>() {

                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                                        widget.getGrid().getView().getBody().unmask();
                                        widget.getGrid().getView().refresh(false);
                                    } else {
                                        schedule(15000);
                                    }
                                }
                            });
                }
            }
        };
        timer.schedule(15000);
    }

    /**
     * Display a Confirmation Message in the Application
     *
     * @param title
     * @param message
     * @param callback
     */
    public static MessageBox confirmMessage(String title, String message,
            Listener<MessageBoxEvent> callback) {
        return MessageBox.confirm(title, message, callback);
    }

    /**
     * Display a Prompting Message to enter some text in the Application. The OK
     * button will be enabled iff the text is not empty or blank.
     *
     * @param title
     * @param message
     * @param callback
     */
    public static MessageBox promptMessage(String title, String message,
            Listener<MessageBoxEvent> callback) {
        final MessageBox box = MessageBox.prompt(title, message, callback);

        final Button okButton = box.getDialog().getButtonById(Dialog.OK);
        okButton.disable();

        final TextField<String> textBox = box.getTextBox();
        textBox.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (okButton.isEnabled()
                        && event.getKeyCode() == KeyCodes.KEY_ENTER) {
                    box.getDialog().hide(okButton);
                }
            }
        });

        box.addListener(Events.OnKeyUp, new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent be) {
                String value = GPSecureStringTextField.sanitizeString(textBox.getValue());
                if (value == null || value.trim().equals("")) {
                    okButton.disable();
                } else {
                    okButton.enable();
                }
            }
        });
        return box;
    }
}
