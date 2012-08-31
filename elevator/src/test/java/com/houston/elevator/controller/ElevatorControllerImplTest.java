package com.houston.elevator.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.houston.elevator.api.ControlPanelInput;
import com.houston.elevator.api.ControlPanelInput.InputType;
import com.houston.elevator.api.Elevator;
import com.houston.elevator.api.ElevatorStatus;
import com.houston.elevator.api.Order;
import com.houston.elevator.api.Order.OrderType;

public class ElevatorControllerImplTest {

    private Elevator elevator;
    private ElevatorControllerImpl controller;

    @Before
    public void setUp() {
        elevator = Mockito.mock(Elevator.class);
        controller = new ElevatorControllerImpl();
        controller.registerElevator(elevator);
    }

    @Test
    public void shouldGoToFirstFloor() {
        processInput(InputType.GO_TO_FLOOR, 1);

        Mockito.verify(elevator).goToFloor(1);
    }

    @Test
    public void shouldGoToSecondFloor() {
        processInput(InputType.GO_TO_FLOOR, 2);
        Mockito.verify(elevator).goToFloor(2);
    }

    @Test
    public void openDoors() {
        processInput(InputType.OPEN_DOORS, 1);

        Mockito.verify(elevator).openDoors();
    }

    @Test
    public void closeDoors() {
        processInput(InputType.CLOSE_DOORS, 1);
        Mockito.verify(elevator).closeDoors();
    }

    @Test
    public void shouldMoveElevatorToFirstFloorWhenOrdered() {
        processOrder(1, OrderType.GOING_DOWN);
        Mockito.verify(elevator).goToFloor(1);
    }

    @Test
    public void shouldMoveElevatorToThirdFloorWhenOrdered() {
        processOrder(3, OrderType.GOING_DOWN);
        Mockito.verify(elevator).goToFloor(3);
    }

    @Test
    public void shouldOpenDoorsWhenOrderedAndInTheOrderingFloor() {
        processOrder(3, OrderType.GOING_DOWN);

        Mockito.verify(elevator, Mockito.never()).openDoors();

        ElevatorStatus isArrived = new ElevatorStatus(2, 2,
                ElevatorStatus.DoorStatus.CLOSED);
        Mockito.when(elevator.getStatus()).thenReturn(isArrived);

        controller.processInput(null, null);
        Mockito.verify(elevator).openDoors();
    }

    @Test
    public void shouldNotOpenDoorsWhenOrderedAndNotInTheOrderingFloor() {
        ElevatorStatus isArrived = new ElevatorStatus(1, 2,
                ElevatorStatus.DoorStatus.CLOSED);
        Mockito.when(elevator.getStatus()).thenReturn(isArrived);

        processOrder(3, OrderType.GOING_DOWN);

        Mockito.verify(elevator, Mockito.never()).openDoors();
        controller.processInput(null, null);
        Mockito.verify(elevator, Mockito.never()).openDoors();
    }

    private void processOrder(int floor, OrderType type) {
        Order order = new Order(floor, type);
        controller.processInput(Arrays.asList(order), null);
    }

    private void processInput(InputType type, int floor) {
        ControlPanelInput input = new ControlPanelInput(elevator, type, floor);
        controller.processInput(null, Arrays.asList(input));
    }

}
