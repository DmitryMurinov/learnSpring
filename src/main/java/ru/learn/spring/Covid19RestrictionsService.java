package ru.learn.spring;

import java.util.UUID;

public class Covid19RestrictionsService {

    private final UUID uuid = UUID.randomUUID();

    public String getAllRestrictions(){
        return "All restrictions. You should wear mask and medical gloves during travel.";
    }

    public String getUUID() {
        return uuid.toString();
    }

}
