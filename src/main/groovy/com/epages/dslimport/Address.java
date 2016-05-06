package com.epages.dslimport;
import org.gradle.model.Managed;

@Managed
interface Address {
    String getStreet();
    void setStreet(String street);

    String getCity();
    void setCity(String city);

    default Boolean isEmpty() {
        return Boolean.valueOf(getStreet() == null && getCity() == null);
    }
}