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
package org.geosdi.geoplatform.exception;

import javax.xml.ws.WebFault;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@WebFault(name = "AccountLoginFault",
          faultBean = "org.geosdi.exception.AccountLoginFault")
public class AccountLoginFault extends Exception {

    private static final long serialVersionUID = -9167519878278175760L;
    //
    private LoginFaultType faultType;
    private Long accountID;
    private String naturalID;

    public enum LoginFaultType {

        ACCOUNT_DISABLED("Account disabled"), ACCOUNT_EXPIRED("Account exiperd");
        //
        private String faultType;

        private LoginFaultType(String faultType) {
            this.faultType = faultType;
        }

        @Override
        public String toString() {
            return faultType;
        }
    }

    public AccountLoginFault() {
    }

    public AccountLoginFault(LoginFaultType faultType) {
        super(faultType.toString());
        this.faultType = faultType;
    }

    public AccountLoginFault(LoginFaultType faultType, Long accountID) {
        super(faultType.toString());
        this.faultType = faultType;
        this.accountID = accountID;
    }

    public AccountLoginFault(LoginFaultType faultType, String naturalID) {
        super(faultType.toString());
        this.faultType = faultType;
        this.naturalID = naturalID;
    }

    public AccountLoginFault(LoginFaultType faultType, Throwable cause) {
        super(faultType.toString(), cause);
        this.faultType = faultType;
    }

    public LoginFaultType getFaultType() {
        return faultType;
    }

    public void setFaultType(LoginFaultType faultType) {
        this.faultType = faultType;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getNaturalID() {
        return naturalID;
    }

    public void setNaturalID(String naturalID) {
        this.naturalID = naturalID;
    }

    @Override
    public String getMessage() {
        if (accountID != null) {
            return faultType + " (ID = " + accountID + ")";
        } else if (naturalID != null) {
            return faultType + " (Natural ID = " + naturalID + ")";
        }
        return super.getMessage();
    }
}
