package live.soilandpimp.domain;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;

import live.soilandpimp.domain.enums.ApiType;
import live.soilandpimp.domain.enums.UserRole;


/**
 * Represents a user for the Soil and Pimp Live web application. Primary use is for admin verification.
 * 
 * @author NYPD
 */
@Table("users")
public class User {

    @PrimaryKey
    private UUID id;
    private String nickname;
    @Column("user_role")
    private UserRole userRole;

    @Column("api_identities")
    @CassandraType(type = Name.MAP, typeArguments = {Name.TEXT, Name.TEXT})
    private Map<ApiType, String> apiIdentities;

    @Transient
    private String userProfilePicture;

    // Default Accessors *********************************
    public UUID getUserId() {
        return id;
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
    public Map<ApiType, String> getApiIdentities() {
        return apiIdentities;
    }

    // For Cassandra ***************************************
    protected User() {}

    @Override
    public String toString() {
        return "User [id=" + id + ", nickname=" + nickname + ", userRole=" + userRole + ", apiIdentities=" + apiIdentities
                + ", userProfilePicture=" + userProfilePicture + "]";
    }

}
