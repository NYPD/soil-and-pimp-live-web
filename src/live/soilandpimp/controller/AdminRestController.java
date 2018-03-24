package live.soilandpimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.domain.Event;
import live.soilandpimp.service.MainService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private MainService eventService;

    @RequestMapping("/get-edit-event-modal-content")
    public ModelAndView getEditEventModalContent(@RequestParam("eventKey") String eventKey) {

        Event event = eventService.getEvent(eventKey);

        ModelAndView modelAndView = new ModelAndView("modal/maintenace/edit-event");
        modelAndView.addObject("event", event);

        return modelAndView;
    }
}
