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
package org.geosdi.geoplatform.gui.client.widget.security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.img.BasicWidgetImage;
import org.geosdi.geoplatform.gui.client.widget.progressbar.ProgressBar;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */
public abstract class GPAdvancedSecurityWidget extends Composite {

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

    //
    @UiTemplate("GPAdvancedSecurityWidget.ui.xml")
    interface LoginUiBinder extends UiBinder<Widget, GPAdvancedSecurityWidget> {
    }
    @UiField(provided = true)
    final BasicWidgetImage resources = BasicWidgetImage.INSTANCE;
    //
    @UiField
    protected TextBox userName;
    //
    @UiField
    protected PasswordTextBox password;
    //
    @UiField
    protected Label loginError;
    //
    @UiField
    protected SubmitButton login;
    protected ProgressBar progressBar = new ProgressBar();
    private double progres = 0;
    private Timer timer;

    //
    public GPAdvancedSecurityWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        login.addStyleName("g-button g-button-submit");
        login.getElement().setId("signIn");
        userName.setFocus(true);
        this.addStatusComponent();
        this.addKeyHandler();
    }

    private void addKeyHandler() {
        userName.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && login.isEnabled()) {
                    onSubmit();
                }

            }
        });
        userName.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                validate();
            }
        });
        password.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && login.isEnabled()) {
                    onSubmit();
                }

            }
        });
        password.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                validate();
            }
        });
    }

    @UiHandler("login")
    void handleClick(ClickEvent e) {
        onSubmit();
    }

    protected boolean hasValue(TextBox field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }

    protected void validate() {
        login.setEnabled(hasValue(userName) && hasValue(password));
    }

    protected void showProgressBar() {
        this.getParent().getElement().getStyle().setDisplay(Display.NONE);
//        this.getElement().getStyle().setDisplay(Display.NONE);

        progressBar.setRunProgress(0.0, "");
        progressBar.setLoadProgress(1.0);

        timer = new Timer() {
            @Override
            public void run() {
                progres += 0.1;
                progressBar.setRunProgress(progres, BasicGinInjector.MainInjector.
                        getInstance().getBasicWidgetMessages().
                        GPAdvancedSecurityWidget_loginMessage(userName.getValue()));
                if (progres > 1.0) {
                    progressBar.setRunProgress(1.0, ButtonsConstants.INSTANCE.doneText());
                    timer.cancel();
                    loginDone();
                }
            }
        };
        timer.scheduleRepeating(300);
        progressBar.setWidth("40%");
        progressBar.setHeight("10px");
        RootPanel.get().add(progressBar);
    }

    public abstract void onSubmit();

    protected abstract void addStatusComponent();

    public abstract void reset();

    public abstract void loginDone();
}
