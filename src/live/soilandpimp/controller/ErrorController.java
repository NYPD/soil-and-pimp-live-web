package live.soilandpimp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.annotation.DefaultController;

@DefaultController
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "404")
    public ModelAndView get404Page() {
        return new ModelAndView("error/404");
    }

}
