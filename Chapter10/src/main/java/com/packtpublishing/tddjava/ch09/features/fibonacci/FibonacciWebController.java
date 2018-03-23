package com.packtpublishing.tddjava.ch09.features.fibonacci;

import com.packtpublishing.tddjava.ch09.features.fibonacci.config.FibonacciFeatureConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FibonacciWebController {
    @Autowired
    FibonacciFeatureConfig fibonacciFeatureConfig;

    @Autowired
    @Qualifier("fibonacci")
    private FibonacciService fibonacciProvider;

    @RequestMapping(value = "/", method = GET)
    public String home(Model model) {
        model.addAttribute("isWebEnabled", fibonacciFeatureConfig.isWebEnabled());
        if (fibonacciFeatureConfig.isWebEnabled()) {
                model.addAttribute("arrayOfInts", Arrays.asList(5, 7, 8, 16));
        }
        return "home";
    }

    @RequestMapping(value ="/web/fibonacci", method = GET)
    public String fibonacci(@RequestParam(value = "number") Integer number, Model model) {
        if (number != null) {
            model.addAttribute("number", number);
            model.addAttribute("value", fibonacciProvider.getNthNumber(number));
        }
        return "fibonacci";
    }
}
