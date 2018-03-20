package live.soilandpimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.service.EventService;

@DefaultController
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    public ModelAndView getHomePage() {

        HomeEvents homeEvents = eventService.getHomeEvents();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("homeEvents", homeEvents);

        return modelAndView;
    }

    @RequestMapping(value = "/past-events", produces = "text/html;charset=UTF-8")
    public ModelAndView getPastEventsPage() {

        HomeEvents homeEvents = eventService.getHomeEvents();

        ModelAndView modelAndView = new ModelAndView("past-events");
        modelAndView.addObject("homeEvents", homeEvents);

        return modelAndView;
    }

}
