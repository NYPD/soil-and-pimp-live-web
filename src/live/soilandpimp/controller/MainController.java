package live.soilandpimp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;
import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.service.MainService;

@DefaultController
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public ModelAndView getHomePage() {

        HomeEvents homeEvents = mainService.getHomeEvents();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("homeEvents", homeEvents);

        return modelAndView;
    }

    @RequestMapping(value = "/past-events", produces = "text/html;charset=UTF-8")
    public ModelAndView getPastEventsPage() {

        List<Event> pastEvents = mainService.getPastEvents();

        ModelAndView modelAndView = new ModelAndView("past-events");
        modelAndView.addObject("pastEvents", pastEvents);

        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView getAboutPage() {
        return new ModelAndView("about");
    }

    @RequestMapping("/privacy-policy")
    public ModelAndView getPrivacyPolicyPage() {
        return new ModelAndView("privacy-policy");
    }

    @RequestMapping("/unsubscribe")
    public ModelAndView getUnsubscribePage() {
        return new ModelAndView("unsubscribe");
    }

    @RequestMapping("/unsubscribe-from-email")
    public ModelAndView getUnsubscribeFromEmailPage(@RequestParam("email") String emailAddress) {

        mainService.emailUnsubscribe(emailAddress);

        ModelAndView modelAndView = new ModelAndView("unsubscribe");
        modelAndView.addObject("emailAddress", emailAddress);

        return modelAndView;
    }

    @RequestMapping("/verify-email")
    public ModelAndView verifyEmail(@RequestParam("email") String emailAddress, @RequestParam("token") String userVerificationToken) {

        boolean emailVerified = mainService.verifyEmailSubscription(emailAddress, userVerificationToken);

        ModelAndView homePage = this.getHomePage();
        homePage.addObject("emailVerified", emailVerified);

        return homePage;
    }

}
