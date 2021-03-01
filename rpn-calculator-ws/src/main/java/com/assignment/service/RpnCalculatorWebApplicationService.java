package com.assignment.service;

import com.assignment.map.RpnCalculatorMapper;
import com.assignment.model.RpnCalculatorRequest;
import com.assignment.model.RpnCalculatorResponse;
import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import com.assignment.rpn.service.RpnCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RpnCalculatorWebApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorWebApplicationService.class);

    @Autowired
    private RpnStack rpnStack;

    @Autowired
    private RpnCalculatorMapper mapper;

    @Autowired
    private RpnCalculatorService rpnCalculatorService;

    public RpnCalculatorResponse evaluate(RpnCalculatorRequest request) {
        LOG.trace("In evaluate with request: {}", request);
        RpnCalculatorResult result = rpnCalculatorService.evaluate(request.getExpression(), rpnStack);
        return mapper.mapRpnCalculatorResponse(result, rpnStack);
    }
}
