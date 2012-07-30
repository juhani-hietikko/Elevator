package com.houston.elevator.simulation;

import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorStatus;
import com.houston.elevator.api.ElevatorStatus.DoorStatus;

public class SimulatedElevator implements Elevator {

    private DoorStatus currentDoorStatus;
    private DoorStatus targetDoorStatus;
    private int currentFloor; 
    private int targetFloor;
    
    public ElevatorStatus getStatus() {
        return new ElevatorStatus(currentFloor, targetFloor, currentDoorStatus);
    }

    public void goToFloor(int floor) {
        targetFloor = floor;
    }

    public void openDoors() {
        targetDoorStatus = DoorStatus.OPEN;
    }

    public void closeDoors() {
        targetDoorStatus = DoorStatus.CLOSED;
    }
    
    public void work() {
        if (targetDoorStatus == DoorStatus.OPEN && currentDoorStatus == DoorStatus.CLOSED) {
            currentDoorStatus = DoorStatus.OPEN;
        } else if (targetDoorStatus == DoorStatus.CLOSED && currentDoorStatus == DoorStatus.OPEN) {
            currentDoorStatus = DoorStatus.CLOSED;
        } else if (targetFloor > currentFloor) {
            currentFloor++;
        } else if (targetFloor < currentFloor) {
            currentFloor--;
        } 
    }
}
