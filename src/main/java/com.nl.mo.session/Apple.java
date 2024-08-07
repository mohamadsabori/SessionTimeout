package com.nl.mo.session;

public class Apple {
    private final Integer weight;
    private String country;

    public Apple(Integer weight, String country) {
        this.weight = weight;
        this.country = country;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.country + this.weight.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Apple){
            Apple appleObject = (Apple) obj;
            return this.weight.equals(appleObject.weight);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.weight;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public String getCountry() {
        return this.country;
    }
}
