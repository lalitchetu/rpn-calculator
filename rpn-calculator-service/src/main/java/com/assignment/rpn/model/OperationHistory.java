package com.assignment.rpn.model;

import com.assignment.rpn.model.enums.InputCharacterSet;

import java.time.ZonedDateTime;
import java.util.List;

public class OperationHistory {

    private InputCharacterSet inputCharacterSet;
    private List<Double> listOfOperands;
    private Double result;
    private ZonedDateTime timestamp;

    public OperationHistory() {

    }

    public OperationHistory(InputCharacterSet inputCharacterSet, List<Double> listOfOperands) {
        this.inputCharacterSet = inputCharacterSet;
        this.listOfOperands = listOfOperands;
        this.timestamp = ZonedDateTime.now();
    }

    public OperationHistory(InputCharacterSet inputCharacterSet, List<Double> listOfOperands, Double result) {
        this.inputCharacterSet = inputCharacterSet;
        this.listOfOperands = listOfOperands;
        this.result = result;
        this.timestamp = ZonedDateTime.now();
    }

    public InputCharacterSet getInputCharacterSet() {
        return inputCharacterSet;
    }

    public void setInputCharacterSet(InputCharacterSet inputCharacterSet) {
        this.inputCharacterSet = inputCharacterSet;
    }

    public List<Double> getListOfOperands() {
        return listOfOperands;
    }

    public void setListOfOperands(List<Double> listOfOperands) {
        this.listOfOperands = listOfOperands;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OperationHistory{" +
                "inputCharacterSet=" + inputCharacterSet +
                ", listOfOperands=" + listOfOperands +
                ", result=" + result +
                ", timestamp=" + timestamp +
                '}';
    }
}
