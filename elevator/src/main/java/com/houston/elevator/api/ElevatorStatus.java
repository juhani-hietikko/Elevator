package com.houston.elevator.api;

public class ElevatorStatus {
    public enum KinematicStatus {
        STATIONARY, MOVING_UP, MOVING_DOWN
    }
    
    public final int atFloor;
    public final int targetFloor;
    
    public ElevatorStatus(int atFloor, int targetFloor) {
        this.atFloor = atFloor;
        this.targetFloor = targetFloor;
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
