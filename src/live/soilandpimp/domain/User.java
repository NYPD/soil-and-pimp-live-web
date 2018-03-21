package live.soilandpimp.domain;

import live.soilandpimp.domain.enums.UserRole;


/**
 * Represents a user for the Soil and Pimp Live web application. Primary use is for admin verification.
 * 
 * @author NYPD
 */
public class User {

    private int userId;
    private String nickname;
    private UserRole userRole;
    private String userProfilePicture;

    // Default Accessors *********************************
    public int getUserId() {
        return userId;
    }
    public String getNickname() {
        return nickname;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public String getUserProfilePicture() {
        return userProfilePicture;
    }
    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    // For Cassandra ***************************************
    protected User() {}

    @Override
    public String toString() {
        return "User [userId=" + userId + ", nickname=" + nickname + ", googleId=" + userRole + "]";
    }

}
