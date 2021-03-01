package com.assignment.map;

import com.assignment.model.RpnCalculatorResponse;
import com.assignment.rpn.model.RpnCalculatorResult;
import com.assignment.rpn.model.RpnStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

@Component
public class RpnCalculatorMapper {
    private static final Logger LOG = LoggerFactory.getLogger(RpnCalculatorMapper.class);

    public RpnCalculatorResponse mapRpnCalculatorResponse(RpnCalculatorResult result, RpnStack rpnStack) {
        LOG.trace("In mapRpnCalculatorResponse with result: {}", result);
        RpnCalculatorResponse response = new RpnCalculatorResponse();
        response.setSuccess(result.isSuccess());
        response.setResponseMessage(result.getResponseMessage());
        response.setStackElements(toList(rpnStack.getNumberStack()));
        return response;
    }

    private List<Double> toList(Deque<Double> stack) {
        List<Double> listOfElements = new ArrayList<>(stack.size());

        for (Iterator<Double> itr = stack.descendingIterator(); itr.hasNext(); ) {
            listOfElements.add(itr.next());
        }
        return listOfElements;
    }
}
