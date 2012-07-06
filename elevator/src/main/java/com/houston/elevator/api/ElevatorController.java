package com.houston.elevator.api;

public interface ElevatorController {

    void registerElevator(Elevator elevator);
    
    void receiveOrder(ElevatorOrder order);
    
    void receiveInput(ElevatorInput input);
    
    void work();
}
