package live.soilandpimp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;
import live.soilandpimp.annotation.GoogleLogin;
import live.soilandpimp.beans.SoilAndPimpSessionBean;
import live.soilandpimp.domain.Event;
import live.soilandpimp.domain.User;
import live.soilandpimp.exception.UnauthorizedUserException;
import live.soilandpimp.service.ApiLoginService;
import live.soilandpimp.service.MainService;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private MainService eventService;
    @Autowired
    private SoilAndPimpSessionBean soilAndPimpSessionBean;
    @Autowired
    @GoogleLogin
    private ApiLoginService googleLoginService;

    @RequestMapping(value = {"", "login"})
    public ModelAndView getLoginPage(@RequestParam(value = "prevPath", required = false) String prevPath) {

        User user = soilAndPimpSessionBean.getUser();

        if (user != null)
            return new ModelAndView("redirect:/admin/maintenance");
        else if (prevPath != null)
            return new ModelAndView("redirect:" + prevPath);

        return new ModelAndView("login");

    }

    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) {

        soilAndPimpSessionBean.setUser(null);
        session.invalidate();

        return new ModelAndView("redirect:/admin");

    }

    @RequestMapping(value = "api/google-oauth-login")
    public void googleOAuthLogin(HttpServletResponse response,
            @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
            @RequestParam(value = "prevPath", required = false) String prevPath) throws IOException {

        soilAndPimpSessionBean.setRememberMe(rememberMe);
        soilAndPimpSessionBean.setPrevPath(prevPath);

        String authenticationRequestUrl = googleLoginService.getAuthenticationRequestUrl();

        response.sendRedirect(authenticationRequestUrl);

    }

    @RequestMapping(value = "api/google-oauth-verify")
    public ModelAndView googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        googleLoginService.verifyAuthenticationResponse(request);

        User soilAndPimpUser = googleLoginService.getSoilAndPimpUser();

        boolean unauthorized = soilAndPimpUser == null;
        if (unauthorized) throw new UnauthorizedUserException(request);

        soilAndPimpSessionBean.setUser(soilAndPimpUser);

        boolean rememberMe = soilAndPimpSessionBean.isRememberMe();
        if (rememberMe) googleLoginService.createUserCookies(response);

        String prevPath = soilAndPimpSessionBean.getPrevPath();
        boolean hasPrevPath = prevPath != null;

        if (hasPrevPath) {
            soilAndPimpSessionBean.setPrevPath(null);
            return new ModelAndView("redirect:" + prevPath);
        } else {
            return new ModelAndView("redirect:/admin/maintenance");
        }

    }

    @RequestMapping(value = "maintenance")
    public ModelAndView getAdminMaintenancePage() {

        ModelAndView mav = new ModelAndView("maintenance");

        List<Event> allEvents = eventService.getAllEvents();
        mav.addObject("allEvents", allEvents);

        return mav;
    }

}
