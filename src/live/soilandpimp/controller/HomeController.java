package live.soilandpimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = {"", "/home"})
    public ModelAndView getHomePage() {

        HomeEvents homeEvents = homeService.getHomeEvents();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("homeEvents", homeEvents);

        return modelAndView;
    }

}
