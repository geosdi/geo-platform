/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.jobs;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class EmailTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private String subject;
    private String template;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param mailSender
     *          the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param velocityEngine
     *          the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * @param subject
     *          the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @param template
     *          the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }
    //</editor-fold>

    public void sendConfirmationEmail(final GPUser user) throws EmailException {
        String email = user.getEmailAddress();
        logger.info("\n*** Sending email to " + email + " ***");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setSubject(subject);
                    message.setTo(user.getEmailAddress());

                    Map model = new HashMap();
                    model.put("user", user);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, template, model);
                    message.setText(text, true);
                } catch (VelocityException ex) {
                    logger.error("\n*** VelocityException: " + ex.getMessage());
                } catch (MessagingException ex) {
                    logger.error("\n*** MessagingException: " + ex.getMessage());
                } catch (Exception ex) {
                    logger.error("\n*** Exception: " + ex.getMessage());
                }
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error("\n*** MailException: " + ex.getMessage());
            throw new EmailException(ex.getMessage());
        }

        logger.info("\n*** Email send to " + email + " ***");
    }
}
