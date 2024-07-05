package ci.smile.system.manager.obf.utils.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Smtp.
 *
 * @author PaBeu
 */

@ConfigurationProperties(prefix = "smtp.mail")
@Component
public class SmtpProperties {


    /**
     * Smtp host.
     */
    private String host;
    /**
     * Smtp port.
     */
    private Integer port;
    /**
     * Smtp login.
     */
    private String login;
    /**
     * Smtp password.
     */
    private String password;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
