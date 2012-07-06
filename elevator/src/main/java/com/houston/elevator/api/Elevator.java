package com.houston.elevator.api;

public interface Elevator {

    ElevatorStatus getStatus();
    
    void goToFloor(int floor);
    
    void openDoors();
    
    void closeDoors();
}
