package live.soilandpimp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;

@DefaultController
public class FooterController {

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
}
