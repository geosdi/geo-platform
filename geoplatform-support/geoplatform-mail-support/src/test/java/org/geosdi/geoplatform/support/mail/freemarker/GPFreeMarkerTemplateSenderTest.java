package org.geosdi.geoplatform.support.mail.freemarker;

import freemarker.template.Configuration;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.mail.configuration.detail.GPMailDetail;
import org.geosdi.geoplatform.support.mail.loader.GPMailLoader;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPMailLoader.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(profiles = {"GPMailFreeMarkerSupport"})
public class GPFreeMarkerTemplateSenderTest {

    @GeoPlatformLog
    static Logger logger;
    //
    static final String GP_MAIL_KEY = "GP_MAIL_FILE_PROP";

    @Resource(name = "gpMailSpringDetail")
    private GPMailDetail gpMailSpringDetail;
    @Resource(name = "gpMailSpringSender")
    private JavaMailSender gpMailSpringSender;
    @Resource(name = "gpFreeMarkerConfiguration")
    private Configuration freeMarkerConfiguration;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_MAIL_KEY, "gp-mail-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_MAIL_KEY);
    }

    @Before
    public void setUp() {
        Assert.assertNotNull(logger);
        Assert.assertNotNull(gpMailSpringDetail);
        Assert.assertNotNull(gpMailSpringSender);
        Assert.assertNotNull(freeMarkerConfiguration);
    }

    @Test
    public void gpMailDetailTest() {
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@Mail Detail : {}",
                gpMailSpringDetail);
    }

    @Test
    @Ignore(value = "Too Mails from Hudson / Jenkins")
    public void sendMailWithFreeMarkerSupport() throws InterruptedException {
        this.gpMailSpringSender.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setSubject("geoSDI Notification FreeMarker Support");
                message.setTo("core@geosdi.com");
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfiguration
                        .getTemplate("geoPlatformMailSupport.fm"), null);
                message.setText(text, Boolean.TRUE);
                message.setFrom(gpMailSpringDetail.getFrom(),
                        gpMailSpringDetail.getFromName());
                message.setReplyTo(gpMailSpringDetail.getReplayTo(),
                        gpMailSpringDetail.getReplayToName());
            }

        });
    }
}
