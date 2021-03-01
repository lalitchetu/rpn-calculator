package com.assignment.ws;

import com.assignment.model.RpnCalculatorRequest;
import com.assignment.model.RpnCalculatorResponse;
import com.assignment.service.RpnCalculatorWebApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.assignment.ws.RpnCalculatorController.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class RpnCalculatorController {

    public static final String BASE_PATH = "/rpn-calculator/v1";
    public static final String EVALUATE_EXPRESSION_PATH = "/evaluate";

    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorController.class);

    @Autowired
    private RpnCalculatorWebApplicationService applicationService;

    @PostMapping(
            path = EVALUATE_EXPRESSION_PATH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RpnCalculatorResponse evaluateExpression(@RequestBody RpnCalculatorRequest request) {
        LOG.trace("In evaluateExpression with request: {}", request);
        return applicationService.evaluate(request);
    }
}
