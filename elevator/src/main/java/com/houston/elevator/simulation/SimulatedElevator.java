package com.houston.elevator.simulation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Label;

import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorStatus;
import com.houston.elevator.api.ElevatorStatus.DoorStatus;
import com.houston.elevator.api.ElevatorStatus.KinematicStatus;

public class SimulatedElevator implements Elevator {

    private DoorStatus currentDoorStatus;
    private DoorStatus targetDoorStatus;
    private int currentFloor; 
    private int targetFloor;
    private Map<Integer, Label> statusLabels = new HashMap<Integer, Label>();
    
    public SimulatedElevator(int floors) {
        currentFloor = targetFloor = floors;
        currentDoorStatus = DoorStatus.CLOSED;
    }

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
        
        Label currentFloorLabel = statusLabels.get(currentFloor);
        if (currentFloorLabel != null) {
            currentFloorLabel.setText(statusText());
            currentFloorLabel.update();
        }
        for (Label floorLabel : statusLabels.values()) {
            if (floorLabel != currentFloorLabel) {
                floorLabel.setText("<empty>");
            }
        }
    }

    private String statusText() {
        String statusText = "";
        if (getStatus().getKinematicStatus() == KinematicStatus.STATIONARY) {
            statusText += "Elevator stationary.";
        } else if (getStatus().getKinematicStatus() == KinematicStatus.MOVING_DOWN) {
            statusText += "Elevator descending.";
        } else {
            statusText += "Elevator ascending.";
        }
        statusText += "\n";
        if (currentDoorStatus == DoorStatus.CLOSED) {
            statusText += "Doors closed.";
        } else {
            statusText += "Doors open.";
        }
        return statusText;
    }
    
    public void registerStatusLabel(Label label, int floor) {
        statusLabels.put(floor, label);
    }
}
