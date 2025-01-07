/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.jwt.support.service;

import org.geosdi.geoplatform.experimental.jwt.support.claims.GPJwtRoleClaim;
import org.geosdi.geoplatform.experimental.jwt.support.claims.JwtUserClaim;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static java.util.Map.of;
import static java.util.UUID.randomUUID;
import static org.geosdi.geoplatform.experimental.jwt.support.claims.GPJwtRoleClaim.toRoleClaim;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-JWT-Service-Test.xml"})
@FixMethodOrder(value = NAME_ASCENDING)
public class GPJwtServiceSupportConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "jwtServiceSupport")
    private IGPJwtServiceSupport jwtServiceSupport;

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.jwtServiceSupport);
    }

    @Test
    public void a_generateJwtTokenTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@JWT_TOKEN : {}\n", this.jwtServiceSupport.generateToken("mario.rossi@gmail.com",
                of("name", "Mario Rossi", "test", 123456L, "jwt_user", new JwtUserClaim(randomUUID().toString(), "Mario Rossi"))));
    }

    @Test
    public void b_extractUsernameFromJwtTokenTest() throws Exception {
        String token = this.jwtServiceSupport.generateToken("mario.rossi@gmail.com",
                of("name", "Mario Rossi", "test", 123456L, "jwt_user", new JwtUserClaim(randomUUID().toString(), "Mario Rossi")));
        String username = this.jwtServiceSupport.extractUsername(token);
        assertTrue(username.equals("mario.rossi@gmail.com"));
    }

    @Test
    public void c_extractClaimsTest() throws Exception {
        String token = this.jwtServiceSupport.generateToken("mario.rossi@gmail.com",
                of("name", "Mario Rossi", "test", 123456L, "jwt_user", new JwtUserClaim(randomUUID().toString(), "Mario Rossi")));
        JwtUserClaim jwtUser = this.jwtServiceSupport.extractClaim(token, JwtUserClaim.class, "jwt_user");
        logger.info("{}\n", jwtUser);
    }

    @Test
    public void d_tokenIsExpiredTest() throws Exception {
        logger.info("########################EXPIRED : {}\n", this.jwtServiceSupport.isTokenExpired("eyJ6aXAiOiJHWklQIiwiYWxnIjoiSFMzODQifQ.H4sIAAAAAAAA_6tWKi5NUrJSyk0syszXK8ovLs50SM9NzMzRS87PVdJRykwsUbIyNDe0sDQ3Njaz1FFKrSiACZhZmlnWAgAYExl6QQAAAA.P7ee1BOrId2dOr8Ddq665sre_rgvhH0XFfMJVPSoeahWeDDA7bK1T38RbMoq_Bns"));
    }

    @Test
    public void e_extractJwtRoleClaimTest() throws Exception {
        String token = this.jwtServiceSupport.generateToken("mario.rossi@gmail.com",
                of("name", "Mario Rossi", "test", 123456L, "jwt_user", new JwtUserClaim(randomUUID().toString(), "Mario Rossi"), "jwt_role", toRoleClaim("ADMIN")));
        logger.info("######################JWT_TOKEN : {}\n", token);
        JwtUserClaim jwtUser = this.jwtServiceSupport.extractClaim(token, JwtUserClaim.class, "jwt_user");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@JWT_USER_CLAIN : {}\n", jwtUser);
        GPJwtRoleClaim roleClaim = this.jwtServiceSupport.extractClaim(token, GPJwtRoleClaim.class, "jwt_role");
        logger.info("######################JWT_ROLE_CLAIM : {}\n", roleClaim);
    }

    @Test
    public void f_extractJwtRoleClaimTest() throws Exception {
        String token = this.jwtServiceSupport.generateToken("mario.rossi@gmail.com",
                of("name", "Mario Rossi", "test", 123456L, "jwt_user", new JwtUserClaim(randomUUID().toString(), "Mario Rossi"), "jwt_role_claim", toRoleClaim("ADMIN")));
        logger.info("######################JWT_TOKEN : {}\n", token);
        JwtUserClaim jwtUser = this.jwtServiceSupport.extractClaim(token, JwtUserClaim.class, "jwt_user");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@JWT_USER_CLAIN : {}\n", jwtUser);
        GPJwtRoleClaim roleClaim = this.jwtServiceSupport.extractClaim(token, GPJwtRoleClaim.class, "jwt_role");
        assertNull(roleClaim);
    }
}