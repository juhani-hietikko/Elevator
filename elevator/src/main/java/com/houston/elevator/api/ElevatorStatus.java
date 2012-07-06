package com.houston.elevator.api;

public class ElevatorStatus {
    public enum DoorStatus {
        CLOSED, OPEN
    }

    public enum KinematicStatus {
        STATIONARY, MOVING_UP, MOVING_DOWN
    }
    
    public final int currentFloor;
    public final int targetFloor;
    public final DoorStatus doorStatus;
    
    public ElevatorStatus(int currentFloor, int targetFloor, DoorStatus doorStatus) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.doorStatus = doorStatus;
    }
    
    public KinematicStatus getKinematicStatus() {
        if (currentFloor == targetFloor)
            return KinematicStatus.STATIONARY;
        else if (currentFloor < targetFloor)
            return KinematicStatus.MOVING_UP;
        else
            return KinematicStatus.MOVING_DOWN;
    }
}
