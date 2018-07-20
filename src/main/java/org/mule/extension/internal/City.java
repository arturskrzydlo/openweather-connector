package org.mule.extension.internal;

public class City {

    public City(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    private String name;
    private String countryCode;

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
