package live.soilandpimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.EventForm;
import live.soilandpimp.service.MainService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/get-edit-add-event-modal-content")
    public ModelAndView getEditAddEventModalContent(@RequestParam(value = "eventKey", required = false) String eventKey) {

        Event event = mainService.getEvent(eventKey);

        ModelAndView modelAndView = new ModelAndView("modal/edit-add-event");
        modelAndView.addObject("event", event);

        return modelAndView;
    }

    @RequestMapping("/get-add-schedule-row-content")
    public ModelAndView getAddScheduleRowContent(@RequestParam("index") int index) {

        ModelAndView modelAndView = new ModelAndView("fragment/schedule-modal-row");
        modelAndView.addObject("index", index);

        return modelAndView;
    }

    @RequestMapping("/save-event")
    public ModelAndView saveEvent(EventForm eventForm) {

        Event event = mainService.saveEvent(eventForm);

        ModelAndView modelAndView = new ModelAndView("fragment/maintanence-event-row");
        modelAndView.addObject("event", event);

        return modelAndView;

    }

    @RequestMapping("/get-delete-event-modal-content")
    public ModelAndView getDeleteEventModalContent(@RequestParam("eventKey") String eventKey) {

        Event event = mainService.getEvent(eventKey);

        ModelAndView modelAndView = new ModelAndView("modal/delete-event");
        modelAndView.addObject("event", event);

        return modelAndView;

    }

    @RequestMapping("/delete-event")
    public void deleteEvent(@RequestParam("eventKey") String eventKey) {
        mainService.deleteEvent(eventKey);
    }
}
