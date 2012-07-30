package com.houston.elevator.simulation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Label;

import com.houston.elevator.api.ControlPanelInput;
import com.houston.elevator.api.ControlPanelInput.InputType;
import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorController;
import com.houston.elevator.api.Order;
import com.houston.elevator.api.Order.OrderType;
import com.houston.elevator.controller.ElevatorControllerImpl;

public class Simulator {
    private final List<SimulatedElevator> elevators = new ArrayList<SimulatedElevator>();
    private final List<Order> pendingOrders = new ArrayList<Order>();
    private final List<ControlPanelInput> pendingPanelInput = new ArrayList<ControlPanelInput>();
    private final ElevatorController elevatorController;
    
    public Simulator(int numberOfElevators, int numberOfFloors) {
        elevatorController = new ElevatorControllerImpl();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new SimulatedElevator());
        }
    }
    
    public void elevatorOrdered(int floor, String command) {
        OrderType orderType = "/\\".equals(command) ? OrderType.GOING_UP : OrderType.GOING_DOWN;
        pendingOrders.add(new Order(floor, orderType));
    }
    
    public void panelCommandPressed(String command, int elevatorIndex) {
        Elevator elevator = elevators.get(elevatorIndex);
        if ("< >".equals(command)) {
            pendingPanelInput.add(new ControlPanelInput(elevator, InputType.OPEN_DOORS, -1));
        } else if ("> <".equals(command)) {
            pendingPanelInput.add(new ControlPanelInput(elevator, InputType.CLOSE_DOORS, -1));
        } else {
            int floor = Integer.parseInt(command);
            pendingPanelInput.add(new ControlPanelInput(elevator, InputType.GO_TO_FLOOR, floor));
        }
    }

    public void advance() {
        elevatorController.processInput(pendingOrders, pendingPanelInput);
        pendingOrders.clear();
        pendingPanelInput.clear();
        for (SimulatedElevator elevator : elevators) {
            elevator.work();
        }
    }
    
    public void registerStatusLabel(Label label, int elevatorIndex, int floor) {
        elevators.get(elevatorIndex).registerStatusLabel(label, floor);
    }

    public void initialize() {
        for (SimulatedElevator elevator : elevators) {
            elevator.work();
            elevatorController.registerElevator(elevator);
        }
    }
}
