package live.soilandpimp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;
import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.service.MainService;

@DefaultController
public class MainController {

    @Autowired
    private MainService eventService;

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public ModelAndView getHomePage() {

        HomeEvents homeEvents = eventService.getHomeEvents();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("homeEvents", homeEvents);

        return modelAndView;
    }

    @RequestMapping(value = "/past-events", produces = "text/html;charset=UTF-8")
    public ModelAndView getPastEventsPage() {

        List<Event> pastEvents = eventService.getPastEvents();

        ModelAndView modelAndView = new ModelAndView("past-events");
        modelAndView.addObject("pastEvents", pastEvents);

        return modelAndView;
    }

}
