package com.houston.elevator.api;

public class ElevatorInput {
    public enum InputType {
        GO_TO_FLOOR, CLOSE_DOORS, OPEN_DOORS
    }
    
    public final Elevator elevator;
    public final InputType type;
    public final int selectedFloor;
    
    public ElevatorInput(Elevator elevator, InputType type, int selectedFloor) {
        this.elevator = elevator;
        this.type = type;
        this.selectedFloor = selectedFloor;
    }
}
