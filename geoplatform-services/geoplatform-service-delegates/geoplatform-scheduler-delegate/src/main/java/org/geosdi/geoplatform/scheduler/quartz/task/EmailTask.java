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
package org.geosdi.geoplatform.scheduler.quartz.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.scheduler.exception.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class EmailTask {

    private static final Logger logger = LoggerFactory.getLogger(EmailTask.class);
    //
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    //
    private String frontendLink;
    private String frontendLabel;
    //
    private String subjectRegistration;
    private String templateRegistration;
    //
    private String subjectModification;
    private String templateModification;
    //
    private String subjectCreationNotification;
    private String templateCreationNotification;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param velocityEngine the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * @param frontendLink the frontendLink to set
     */
    public void setFrontendLink(String frontendLink) {
        this.frontendLink = frontendLink;
    }

    /**
     * @param frontendLabel the frontendLabel to set
     */
    public void setFrontendLabel(String frontendLabel) {
        this.frontendLabel = frontendLabel;
    }

    /**
     * @param subjectRegistration the subjectRegistration to set
     */
    public void setSubjectRegistration(String subjectRegistration) {
        this.subjectRegistration = subjectRegistration;
    }

    /**
     * @param templateRegistration the templateRegistration to set
     */
    public void setTemplateRegistration(String templateRegistration) {
        this.templateRegistration = templateRegistration;
    }

    public void setSubjectCreationNotification(String subjectCreationNotification) {
        this.subjectCreationNotification = subjectCreationNotification;
    }

    public void setTemplateCreationNotification(String templateCreationNotification) {
        this.templateCreationNotification = templateCreationNotification;
    }

    /**
     * @param subjectModification the subjectModification to set
     */
    public void setSubjectModification(String subjectModification) {
        this.subjectModification = subjectModification;
    }

    /**
     * @param templateModification the templateModification to set
     */
    public void setTemplateModification(String templateModification) {
        this.templateModification = templateModification;
    }
    //</editor-fold>

    public void sendEmailUserCreationNotification(final List<String> emailRecipient,
            final String createdUserName) throws EmailException {
        logger.info("\n*** Sending email for user creation notification to {} ***",
                emailRecipient.size());

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setSubject(createSubject(subjectCreationNotification));

//                    String[] array = new String[emailRecipient.size()];
//                    for (int i = 0; i < emailRecipient.size(); i++) {
//                        array[i] = emailRecipient.get(i);
//                    }
                    String[] array = emailRecipient.toArray(new String[emailRecipient.size()]);

                    message.setTo(array);

                    Map model = new HashMap();
                    model.put("createdUserName", createdUserName);
                    model.put("frontendLink", frontendLink);
                    model.put("frontendLabel", frontendLabel);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateCreationNotification, "UTF-8", model);
                    message.setText(text, true);
                } catch (VelocityException ex) {
                    logger.error("\n*** VelocityException: {}", ex.getMessage());
                } catch (MessagingException ex) {
                    logger.error("\n*** MessagingException: {}", ex.getMessage());
                } catch (Exception ex) {
                    logger.error("\n*** Exception: {}", ex.getMessage());
                }
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error("\n*** MailException: {}" + ex.getStackTrace().toString());
            throw new EmailException(ex.getMessage());
        }

        logger.info("\n*** Email for user creation notification sended to {} ***", emailRecipient.toString());
    }

    public void sendEmailRegistration(final GPUser user) throws EmailException {
        String email = user.getEmailAddress();
        logger.info("\n*** Sending email for registration to {} ***", email);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setSubject(createSubject(subjectRegistration));
                    message.setTo(user.getEmailAddress());

                    Map model = new HashMap();
                    model.put("user", user);
                    model.put("frontendLink", frontendLink);
                    model.put("frontendLabel", frontendLabel);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateRegistration, "UTF-8", model);
                    message.setText(text, true);
                } catch (VelocityException ex) {
                    logger.error("\n*** VelocityException: {}", ex.getMessage());
                } catch (MessagingException ex) {
                    logger.error("\n*** MessagingException: {}", ex.getMessage());
                } catch (Exception ex) {
                    logger.error("\n*** Exception: {}", ex.getMessage());
                }
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error("\n*** MailException: {}", ex.getMessage());
            throw new EmailException(ex.getMessage());
        }

        logger.info("\n*** Email for registration sended to {} ***", email);
    }

    public void sendEmailModification(final GPUser user,
            final String previousEmail, final String newPlainPassword)
            throws EmailException {
        String email = user.getEmailAddress();
        logger.info("\n*** Sending email for registration to {} ***", email);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setSubject(createSubject(subjectModification));
                    message.setTo(user.getEmailAddress());

                    Map model = new HashMap();
                    model.put("user", user);
                    model.put("frontendLink", frontendLink);
                    model.put("frontendLabel", frontendLabel);
                    // email
                    boolean emailChanged = false;
                    if (previousEmail != null) {
                        emailChanged = true;
                        model.put("previousEmail", previousEmail);
                    }
                    model.put("emailChanged", emailChanged);
                    // password
                    boolean passwordChanged = false;
                    if (newPlainPassword != null) {
                        passwordChanged = true;
                        model.put("newPlainPassword", newPlainPassword);
                    }
                    model.put("passwordChanged", passwordChanged);

                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateModification, "UTF-8", model);
                    message.setText(text, true);
                } catch (VelocityException ex) {
                    logger.error("\n*** VelocityException: {}", ex.getMessage());
                } catch (MessagingException ex) {
                    logger.error("\n*** MessagingException: {}", ex.getMessage());
                } catch (Exception ex) {
                    logger.error("\n*** Exception: {}", ex.getMessage());
                }
            }
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error("\n*** MailException: {}", ex.getMessage());
            throw new EmailException(ex.getMessage());
        }

        logger.info("\n*** Email for modification sended to {} ***", email);
    }

    private String createSubject(String subjectEnd) {
        return "[" + frontendLabel + "] " + subjectEnd;
    }
}
