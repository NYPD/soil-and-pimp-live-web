package live.soilandpimp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import live.soilandpimp.domain.enums.ApiType;

@Entity
@Table(name = "user_api_identity")
public class UserApiIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_api_identity_id")
    private int userApiIdentityId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "api_type")
    private ApiType apiType;
    @Column(name = "api_user_id")
    private String apiUserId;

    // Default Accessors **************
    public int getUserApiIdentityId() {
        return userApiIdentityId;
    }

    public int getUserId() {
        return userId;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public String getApiUserId() {
        return apiUserId;
    }

    // For MyBatis ********************
    protected UserApiIdentity() {}

    @Override
    public String toString() {
        return "UserApiIdentity [userApiIdentityId=" + userApiIdentityId + ", userId=" + userId + ", apiType=" + apiType
                + ", apiUserId=" + apiUserId + "]";
    }

}
