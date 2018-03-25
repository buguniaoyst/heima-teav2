package com.heima.tea.page;

public enum Direction {
    ASC("asc"),
    DESC("desc");

    private String direction;

    private Direction(String direction) {
        this.direction = direction;
    }

    public String getDirectionString() {
        return this.direction;
    }

    public static Direction getDirectionByString(String direction) {
        return "desc".equalsIgnoreCase(direction) ? DESC : ASC;
    }
}
