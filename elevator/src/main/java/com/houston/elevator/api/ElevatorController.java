package com.houston.elevator.api;

import java.util.List;

public interface ElevatorController {

    void registerElevator(Elevator elevator);
    
    void processInput(List<Order> queuedOrders, List<ControlPanelInput> queuedPanelInput);
}
