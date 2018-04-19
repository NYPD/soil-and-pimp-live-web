package live.soilandpimp.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import live.soilandpimp.domain.enums.UserRole;


/**
 * Represents a user for the Soil and Pimp Live web application. Primary use is for admin verification.
 * 
 * @author NYPD
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Collection<UserApiIdentity> apiIdentities;

    @Transient
    private String userProfilePicture;

    // JPA Constructor
    protected User() {}

    public User(int userId, String nickname, UserRole userRole) {
        this.userId = userId;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    // Default Accessors *********************************
    public Integer getUserId() {
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
    public Collection<UserApiIdentity> getApiIdentities() {
        return apiIdentities;
    }

    @Override
    public String toString() {
        return "User [id=" + userId + ", nickname=" + nickname + ", userRole=" + userRole + ", apiIdentities="
               + apiIdentities
               + ", userProfilePicture=" + userProfilePicture + "]";
    }

}
