package com.houston.elevator.api;

public class Order {

    public enum OrderType {
        GOING_UP, GOING_DOWN
    }
    
    private final int floor;
    private final OrderType type;
    
    public Order(int floor, OrderType type) {
        this.floor = floor;
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public OrderType getType() {
        return type;
    }
}
