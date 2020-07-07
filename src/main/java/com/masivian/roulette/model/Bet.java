package com.masivian.roulette.model;

import java.io.Serializable;
import java.util.Date;

public class Bet implements Serializable {

    private String idRoulette;
    private final int bet;
    private final int number;
    private String color;
    private String userId;
    private String state;
    private final Date createdAt;
    private Date updatedAt;

    public Bet(int bet, int number, String color) {
        this.bet = bet;
        this.number = number;
        this.color = color;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.state = "open";
    }

    public String getIdRoulette() {

        return idRoulette;
    }

    public void setIdRoulette(String idRoulette) {
        this.idRoulette = idRoulette;
    }

    public int getBet() {

        return bet;
    }

    public int getNumber() {

        return number;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {

        return createdAt;
    }

    public Date getUpdatedAt() {

        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
