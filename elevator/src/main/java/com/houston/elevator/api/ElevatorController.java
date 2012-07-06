package com.houston.elevator.api;

public interface ElevatorController {

    void handleOrder(ElevatorOrder order);
    
    void handleInput(ElevatorInput input);
}
