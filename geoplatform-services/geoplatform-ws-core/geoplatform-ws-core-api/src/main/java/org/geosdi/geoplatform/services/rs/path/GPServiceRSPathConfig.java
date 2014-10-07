/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.rs.path;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPServiceRSPathConfig {

    public static final String DEFAULT_RS_SERVICE_PATH = "/";

    public static final String GP_CORE_SERVICE_RS_PATH = "/jsonCore";
    /**
     * ORGANIZATIONS PATH
     */
    private static final String ORGANIZATION_BASE_PATH = "/organizations/";
    public static final String INSERT_ORGANIZATION_PATH = ORGANIZATION_BASE_PATH
            + "insertOrganization";
    public static final String DELETE_ORGANIZATION_PATH = ORGANIZATION_BASE_PATH
            + "deleteOrganization/{organizationID}";
    /**
     * ACCOUNTS PATH
     */
    private static final String ACCOUNTS_PATH = "/accounts/";
    public static final String GET_ALL_ORGANIZATION_ACCOUNTS = ACCOUNTS_PATH
            + "getAllOrganizationAccount/{organization}";
    public static final String GET_ALL_ACCOUNTS = ACCOUNTS_PATH + "getAllAccounts";
    public static final String GET_USER_DETAIL_BY_ID = ACCOUNTS_PATH
            + "getUserDetail/{userID}";
    public static final String GET_USER_DETAIL = ACCOUNTS_PATH
            + "getUserDetail";
    public static final String GET_USER_DETAIL_BY_USERNAME_AND_PASSWORD = GET_USER_DETAIL
            + "/{username}/{plainPassword}";
    public static final String GET_ACCOUNTS_COUNT = ACCOUNTS_PATH
            + "getAccountsCount";
    public static final String GET_SHORT_USER_BY_ID = ACCOUNTS_PATH
            + "getShortUser/{userID}";
    public static final String GET_SHORT_USER_BY_USERNAME = ACCOUNTS_PATH
            + "getShortUserByUsername";
    public static final String INSERT_ACCOUNT_PATH = ACCOUNTS_PATH + "insertAccount";
    public static final String UPDATE_USER_PATH = ACCOUNTS_PATH + "updateUser";
    public static final String DELETE_ACCOUNT_PATH = ACCOUNTS_PATH
            + "deleteAccount/{accountID}";

    /**
     * AUTHORITIES PATH
     */
    private static final String AUTHORITIES_PATH = "/authorities/";
    public static final String GET_AUTHORITIES_BY_ACCOUNT_NATURAL_ID = AUTHORITIES_PATH
            + "getAuthoritiesByAccountNaturalID/{accountNaturalID}";

    /**
     * ACCOUNT PROJECTS PATH *
     */
    private static final String ACCOUNT_PROJECTS = "/accountprojects/";
    public static final String GET_ACCOUNT_PROJECTS_BY_ACCOUNT_ID = ACCOUNT_PROJECTS
            + "{accountID}";

    /**
     * WMS CAPABILITIES PATH
     */
    public static final String WMS_SERVICE_RS_PATH = "/jsonWMS";
    public static final String GET_WMS_SERVER_BY_URL = "/getServerByUrl";
    public static final String GET_WMS_CAPABILITIES = "/getCapabilities";

    private GPServiceRSPathConfig() {
    }

}
