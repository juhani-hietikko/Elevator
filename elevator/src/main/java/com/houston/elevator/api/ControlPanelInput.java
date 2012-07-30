package com.houston.elevator.api;

public class ControlPanelInput {
    
    public enum InputType {
        GO_TO_FLOOR, CLOSE_DOORS, OPEN_DOORS
    }
    
    private final Elevator elevator;
    private final InputType type;
    private final int selectedFloor;
    
    public ControlPanelInput(Elevator elevator, InputType type, int selectedFloor) {
        this.elevator = elevator;
        this.type = type;
        this.selectedFloor = selectedFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public InputType getType() {
        return type;
    }

    public int getSelectedFloor() {
        return selectedFloor;
    }
}
