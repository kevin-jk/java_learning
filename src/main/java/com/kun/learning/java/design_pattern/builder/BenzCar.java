package com.kun.learning.java.design_pattern.builder;



/**
 * @Author: jrjiakun
 * @Date: 2019/3/6 19:45
 *
 * Guava Cache Builder
 */

public class BenzCar {
    String logo;
    String wheel;
    String power;
    String body;

` `
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "BenzCar{" +
                "logo='" + logo + '\'' +
                ", wheel='" + wheel + '\'' +
                ", power='" + power + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
