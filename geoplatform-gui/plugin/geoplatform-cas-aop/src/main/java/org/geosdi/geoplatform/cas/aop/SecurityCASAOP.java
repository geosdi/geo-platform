package org.geosdi.geoplatform.cas.aop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.geosdi.geoplatform.gui.server.gwt.GeoPlatformOGCRemoteImpl;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
/**
 * @Aspect tells the Spring framework that this class contains advice that
 * should be applied to one or more specified Pointcuts at runtime
 * @Component is necessary to support the spring auto-scan
 */
@Aspect
@Component
public class SecurityCASAOP {

    private static final long serialVersionUID = 1842587872500206755L;
    private @Value("configurator{geoserver_url}")
    String geoserverUrl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Before("execution(* org.geosdi.geoplatform.services.GPWMSService.getCapabilities(..))")
//    public void getCapabilities(JoinPoint joinPoint) {
//        logger.info("AOP getCapabilities is running...");
////        System.out.println("Beccato: " + joinPoint.getSignature().getName());
////        System.out.println("Beccato: " + joinPoint.getSignature().toString());
////        for (Object obj : joinPoint.getArgs()) {
////            System.out.println("Object arg: " + obj);
////        }
//        logger.info("****************");
//    }
    @Around("execution(* org.geosdi.geoplatform.services.GPWMSService.getCapabilities(..))"
            + "&& args(serverUrl, ..)")
    public Object getWMSCapabilities(ProceedingJoinPoint proceedingJoinPoint, String serverUrl) throws Throwable {
        logger.info("AOP getWMSCapabilities is running...");
        Object[] args = proceedingJoinPoint.getArgs();
        if (serverUrl.contains(geoserverUrl)) {
            HttpServletRequest request = GeoPlatformOGCRemoteImpl.getRequest();
            Assertion casAssertion = (AssertionImpl) request.
                    getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
            AttributePrincipal attributePrincipal = casAssertion.getPrincipal();
//            String proxyTicket = attributePrincipal.getProxyTicketFor("https://localhost:6443/geoserver/wms?request=GetCapabilities");
            String proxyTicket = attributePrincipal.getProxyTicketFor(geoserverUrl + "/wms?wmtver=1.0.0&request=capabilities");
            logger.info("Proxy ticket ***************: " + proxyTicket);
            try {
                serverUrl += "?ticket=" + URLEncoder.encode(proxyTicket, "UTF-8");
                args[0] = serverUrl;
            } catch (UnsupportedEncodingException ex) {
                logger.error("Error on encoding CAS ticket", ex);
            }
            logger.info("serverURL: " + serverUrl);
            logger.debug("Intercepting method: " + proceedingJoinPoint.getSignature().toString());
        }
        return proceedingJoinPoint.proceed(args);
    }
}
