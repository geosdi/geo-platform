package org.geosdi.geoplatform.hibernate.validator.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPUser implements Serializable {

    private static final long serialVersionUID = 4494526504319235140L;
    //
    @NotNull(message = "{gp_username_not_null}")
    @Size(min = 1, message = "{gp_username_not_empty}")
    private String userName;
    @NotNull(message = "{gp_password_not_null}")
    @Size(min = 1, message = "{gp_password_not_empty}")
    private String password;

    /**
     * @return {@link String}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return {@link String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " userName = '" + userName + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }
}
