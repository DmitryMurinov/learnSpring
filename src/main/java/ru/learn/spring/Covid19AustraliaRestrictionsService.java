package ru.learn.spring;

import java.util.UUID;

public class Covid19AustraliaRestrictionsService {

    private final UUID uuid = UUID.randomUUID();

    public String getAllRestrictions(){
        return "Australia. All travelling forbidden, except New Zealand.";
    }

    public String getUUID() {
        return uuid.toString();
    }

}
