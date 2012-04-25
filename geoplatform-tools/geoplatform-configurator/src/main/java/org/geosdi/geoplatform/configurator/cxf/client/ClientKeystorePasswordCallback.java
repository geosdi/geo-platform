package org.geosdi.geoplatform.configurator.cxf.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientKeystorePasswordCallback implements CallbackHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Map<String, String> passwords = 
        new HashMap<String, String>();
    
    public ClientKeystorePasswordCallback() {
        passwords.put("client", "clientpwd");
        passwords.put("server", "serverstorepwd");
//        passwords.put("alice", "password");
//        passwords.put("bob", "password");
        passwords.put("Alice", "ecilA");
        passwords.put("abcd", "dcba");
        passwords.put("clientx509v1", "storepassword");
        passwords.put("serverx509v1", "storepassword");
        //
        passwords.put("gpagent", "gpagent");
    }

    /**
     * It attempts to get the password from the private 
     * alias/passwords map.
     */
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
    }
    
    /**
     * Add an alias/password pair to the callback mechanism.
     */
    public void setAliasPassword(String alias, String password) {
        passwords.put(alias, password);
    }
    
}
