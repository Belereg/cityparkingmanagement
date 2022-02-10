package com.example.cityparkingmanagement.dto;

public abstract class Constants {
    public static final String INVALID_VALUE = "Invalid value";
    public static final String BLANK_VALUE = "Blank value not allowed";
    public static final String NULL_VALUE = "Null value not allowed";
    public static final String CODE_LENGTH_EXCEEDED = "Code cannot have more than 4 digits";
    public static final String UUID_REGEX = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
}
