package pl.gren.oze_app.controller.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.gren.oze_app.exception.Error404;

@ControllerAdvice(basePackages = "pl.gren.oze_app.controller")
public class GlobalControllerAdvice {
    @ExceptionHandler(Error404.class)
    public String handleInternalException(Error404 error404) {
        return "forward:/error";
    }
}
