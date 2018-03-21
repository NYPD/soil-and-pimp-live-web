package live.soilandpimp.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import live.soilandpimp.beans.SoilAndPimpSessionBean;
import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.UserRole;

/**
 * Security interceptor that checks to see if current user is allowed to continue with the request
 * based on the {@link UserRole} passed in during class instantiation. If there is no current user
 * in the session It checks and see if the user has any cookies created from previous sessions. If
 * so, it attempts to use the appropriate third party API to re authenticate the user. If no cookies
 * are found, the user is redirected to an error page.
 * <br><br>
 * Keep in mind if for some reason the user does have the appropriate cookies, but does not have the
 * correct access the particular part in the system, this security filter should intercept the
 * request again and verify.
 * 
 * @author NYPD
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SoilAndPimpSessionBean soilAndPimpSessionBean;

    private UserRole allowedRole;

    public SecurityInterceptor(UserRole allowedRole) {
        this.allowedRole = allowedRole;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {

        if (request == null) return false;

        User user = soilAndPimpSessionBean.getUser();
        boolean isAllowedUser = user != null && user.getUserRole() == this.allowedRole;
        if (isAllowedUser) return true;

        response.sendRedirect("/admin");
        return false;

    }

}
