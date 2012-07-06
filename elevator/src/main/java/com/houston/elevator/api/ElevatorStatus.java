package com.houston.elevator.api;

public class ElevatorStatus {
    public enum DoorStatus {
        CLOSED, OPEN
    }

    public enum KinematicStatus {
        STATIONARY, MOVING_UP, MOVING_DOWN
    }
    
    public final int atFloor;
    public final int targetFloor;
    public final DoorStatus doorStatus;
    
    public ElevatorStatus(int atFloor, int targetFloor, DoorStatus doorStatus) {
        this.atFloor = atFloor;
        this.targetFloor = targetFloor;
        this.doorStatus = doorStatus;
    }
    
    public KinematicStatus getKinematicStatus() {
        if (atFloor == targetFloor)
            return KinematicStatus.STATIONARY;
        else if (atFloor < targetFloor)
            return KinematicStatus.MOVING_UP;
        else
            return KinematicStatus.MOVING_DOWN;
    }
}
