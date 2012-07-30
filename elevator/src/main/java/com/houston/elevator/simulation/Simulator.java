package com.houston.elevator.simulation;

import java.util.ArrayList;
import java.util.List;

import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorController;
import com.houston.elevator.api.ElevatorInput;
import com.houston.elevator.api.ElevatorInput.InputType;
import com.houston.elevator.api.ElevatorOrder;
import com.houston.elevator.api.ElevatorOrder.OrderType;
import com.houston.elevator.controller.ElevatorControllerImpl;

public class Simulator {
    private List<SimulatedElevator> elevators = new ArrayList<SimulatedElevator>();
    private ElevatorController elevatorController;
    
    public Simulator(int numberOfElevators) {
        elevatorController = new ElevatorControllerImpl();
        for (int i = 0; i < numberOfElevators; i++) {
            SimulatedElevator elevator = new SimulatedElevator();
            elevators.add(elevator);
            elevatorController.registerElevator(elevator);
        }
    }
    
    public void elevatorOrdered(int floor, String command) {
        OrderType orderType = "/\\".equals(command) ? OrderType.GOING_UP : OrderType.GOING_DOWN;
        elevatorController.receiveOrder(new ElevatorOrder(floor, orderType));
    }
    
    public void panelCommandPressed(String command) {
        Elevator elevator = elevators.get(0);
        if ("< >".equals(command)) {
            elevatorController.receiveInput(new ElevatorInput(elevator, InputType.OPEN_DOORS, 0));
        } else if ("> <".equals(command)) {
            elevatorController.receiveInput(new ElevatorInput(elevator, InputType.CLOSE_DOORS, 0));
        } else {
            int floor = Integer.parseInt(command);
            elevatorController.receiveInput(new ElevatorInput(elevator, InputType.GO_TO_FLOOR, floor));
        }
    }

    public void advance() {
        elevatorController.work();
        for (SimulatedElevator elevator : elevators) {
            elevator.work();
        }
    }
}
