package live.soilandpimp.beans;

import live.soilandpimp.configuration.ApplicationConfiguration;
import live.soilandpimp.domain.User;

/**
 * Session bean containing all the relevant information needed for the Moe Sounds application and
 * the currently logged in user.
 * 
 * This is set up in {@link ApplicationConfiguration}
 * 
 * @author NYPD
 */
public class SoilAndPimpSessionBean {

    private User user;
    private boolean rememberMe;
    private String prevPath;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    public String getPrevPath() {
        return prevPath;
    }
    public void setPrevPath(String prevPath) {
        this.prevPath = prevPath;
    }

}
