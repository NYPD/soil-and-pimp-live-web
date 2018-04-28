package live.soilandpimp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import live.soilandpimp.exception.NoUserInSessionException4Ajax;
import live.soilandpimp.model.AjaxError;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(RestControllerAdvisor.class);

    @ExceptionHandler(value = NoUserInSessionException4Ajax.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public void handleFileReadException(NoUserInSessionException4Ajax exception) {

        logger.info("User not found in session for AJAX call, redirecting to login page ", exception);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(Exception exception) {

        logger.error("Something goofed up somewhere", exception);

        ModelAndView modelAndView = new ModelAndView("/modal-content/error/ajax-error");

        String modalTitle = "RIP";
        String modalMessage = "Something went wrong back here, please try again later. If this keeps happening submit a report";

        AjaxError ajaxError = new AjaxError(modalTitle, modalMessage);

        modelAndView.addObject("ajaxError", ajaxError);

        return modelAndView;
    }


}
