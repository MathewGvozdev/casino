package com.mgvozdev.casino.entity.enums;

public enum Chip {

    GREY(5000.0),
    ORANGE(1000.0),
    PURPLE(500.0),
    BLACK(100.0),
    GREEN(25.0),
    YELLOW(20.0),
    RED(5.0),
    PINK(2.5),
    WHITE(1.0),
    QUARTER(0.25);

    private final Double value;

    Chip(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
