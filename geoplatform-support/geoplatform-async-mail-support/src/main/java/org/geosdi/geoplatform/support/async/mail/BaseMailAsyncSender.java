/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.async.mail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class BaseMailAsyncSender implements GPMailAsyncSender {

    private final JavaMailSender mailSender;

    /**
     * @param theMailSender
     */
    public BaseMailAsyncSender(@Nonnull(when = NEVER) JavaMailSender theMailSender) {
        checkNotNull(theMailSender, "Mail Sender must not be null");
        this.mailSender = theMailSender;
    }

    /**
     * @param mm
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) MimeMessage mm) throws MailException {
        checkNotNull(mm != null, "The Parameter MimeMessage must not be null");
        this.mailSender.send(mm);
        return new AsyncResult(TRUE);
    }

    /**
     * @param mms
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) MimeMessage[] mms) throws MailException {
        checkNotNull(mms != null, "The Parameter MimeMessage[] must not be null");
        this.mailSender.send(mms);
        return new AsyncResult(TRUE);
    }

    /**
     * @param mmp
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) MimeMessagePreparator mmp) throws MailException {
        checkNotNull(mmp != null, "The Parameter MimeMessagePreparator must not be null");
        this.mailSender.send(mmp);
        return new AsyncResult(TRUE);
    }

    /**
     * @param mmps
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) MimeMessagePreparator[] mmps) throws MailException {
        checkNotNull(mmps != null, "The Parameter MimeMessagePreparator[] must not be null");
        this.mailSender.send(mmps);
        return new AsyncResult(TRUE);
    }

    /**
     * @param smm
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) SimpleMailMessage smm) throws MailException {
        checkNotNull(smm != null, "The Parameter SimpleMailMessage must not be null");
        this.mailSender.send(smm);
        return new AsyncResult(TRUE);
    }

    /**
     * @param smms
     * @return {@link Future<Boolean>}
     * @throws MailException
     */
    @Override
    public Future<Boolean> send(@Nonnull(when = NEVER) SimpleMailMessage[] smms) throws MailException {
        checkNotNull(smms != null, "The Parameter SimpleMailMessage[] must not be null");
        this.mailSender.send(smms);
        return new AsyncResult(TRUE);
    }

    @Override
    public MimeMessage createMimeMessage() {
        return this.mailSender.createMimeMessage();
    }

    /**
     * @param in
     * @return {@link MimeMessage}
     * @throws MailException
     */
    @Override
    public MimeMessage createMimeMessage(@Nonnull(when = NEVER) InputStream in) throws MailException {
        checkNotNull(in != null, "The Parameter InputStream must not be null");
        return this.mailSender.createMimeMessage(in);
    }
}