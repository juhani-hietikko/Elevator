package com.houston.elevator.api;

public class ElevatorOrder {

    public enum OrderType {
        GOING_UP, GOING_DOWN
    }
    
    public final int floor;
    public final OrderType type;
    
    public ElevatorOrder(int floor, OrderType type) {
        this.floor = floor;
        this.type = type;
    }
}
