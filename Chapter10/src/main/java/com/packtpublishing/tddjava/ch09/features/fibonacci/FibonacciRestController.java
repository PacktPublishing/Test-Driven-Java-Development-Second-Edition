package com.packtpublishing.tddjava.ch09.features.fibonacci;

import com.packtpublishing.tddjava.ch09.features.fibonacci.config.FibonacciFeatureConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FibonacciRestController {
    @Autowired
    FibonacciFeatureConfig fibonacciFeatureConfig;

    @Autowired
    @Qualifier("fibonacci")
    private FibonacciService fibonacciProvider;

    @RequestMapping(value = "/fibonacci", method = GET)
    public FibonacciNumber fibonacci(@RequestParam(value = "number", defaultValue = "0") int number) {
        if (fibonacciFeatureConfig.isRestEnabled()) {
            int fibonacciValue = fibonacciProvider.getNthNumber(number);
            return new FibonacciNumber(number, fibonacciValue);
        } else throw new UnsupportedOperationException();
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public void unsupportedException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), "This feature is currently unavailable");
    }

    @ExceptionHandler(Exception.class)
    public void handleGenericException(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "There was an error processing your request: " + e.getMessage());
    }
}
