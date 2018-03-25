package live.soilandpimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.service.MainService;

@RestController
public class MainRestController {

    @Autowired
    private MainService mainService;

    @RequestMapping("subscribe")
    public ModelAndView getSubscribeModal() {
        return new ModelAndView("modal/subscribe");
    }

    @RequestMapping(value = "submit-email", method = RequestMethod.POST)
    public void submitEmail(@RequestParam("email") String emailAddress) {
        mainService.addEmailSubscription(emailAddress);
    }
}
