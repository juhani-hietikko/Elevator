package com.houston.elevator.controller;

import java.util.List;

import com.houston.elevator.api.ControlPanelInput;
import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorController;
import com.houston.elevator.api.ElevatorStatus;
import com.houston.elevator.api.ElevatorStatus.KinematicStatus;
import com.houston.elevator.api.Order;

public class ElevatorControllerImpl implements ElevatorController {

    private Elevator elevator;

    public void registerElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public void processInput(List<Order> queuedOrders,
            List<ControlPanelInput> queuedPanelInput) {

        ElevatorStatus status = elevator.getStatus();
//        if (status != null
//                && status.getKinematicStatus().equals(
//                        KinematicStatus.STATIONARY)) {
//            elevator.openDoors();
//        }
//
        processPanelInputs(queuedPanelInput);
//        processOrders(queuedOrders);
    }

    public void processOrders(List<Order> queuedOrders) {

        if (queuedOrders != null) {
            elevator.goToFloor(queuedOrders.get(0).getFloor());
        }
    }

    public void processPanelInputs(List<ControlPanelInput> queuedPanelInput) {
        if (queuedPanelInput != null) {
            ControlPanelInput controlPanelInput = queuedPanelInput.get(0);

//            switch (controlPanelInput.getType()) {
//            case GO_TO_FLOOR:
//                elevator.goToFloor(controlPanelInput.getSelectedFloor());
//                break;
//            case OPEN_DOORS:
//                elevator.openDoors();
//                break;
//            default:
//                elevator.closeDoors();
//            }

        }

    }

}
