package live.soilandpimp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainRestController {

    @RequestMapping("/subscribe")
    public ModelAndView getSubscribeModal() {
        return new ModelAndView("modal/global/subscribe-modal");
    }
}
