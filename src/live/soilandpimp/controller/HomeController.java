package live.soilandpimp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

}
