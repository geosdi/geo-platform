package org.geosdi.geoplatform.scheduler.sender;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class SchedulerSenderMailTest {

    @Resource(name = "mailSender")
    private JavaMailSender mailSender;
    @Resource(name = "velocityEngine")
    private VelocityEngine velocityEngine;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.mailSender);
    }

    @Ignore
    @Test
    public void sendMailTest() throws Exception {
        this.mailSender.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setSubject("Notification");
                message.setTo("glascaleia@gmail.com");
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "template/"
                                + "registration.html.vm",
                        "UTF-8", null);
                message.setText(text, Boolean.TRUE);
                message.setFrom("glascaleia@gmail.com");
                message.setReplyTo("glascaleia@gmail.com");
            }

        });
    }
}
