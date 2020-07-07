package com.masivian.roulette.model;

import java.io.Serializable;
import java.util.Date;

public class Roulette implements Serializable {

    private final int[] numbers;
    private final String[] colors;
    private String state;
    private final int maxBet;
    private final Date createdAt;
    private Date updatedAt;

    public Roulette() {
        this.maxBet = 10000;
        this.colors = new String[]{"RED", "BLACK"};
        this.numbers = new int[]{
          0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
          24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36
        };
        this.state = "close";
        this.createdAt = new Date();
        this.updatedAt = new Date();
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

    public int getMaxBet() {
        return maxBet;
    }

    public int[] getNumbers() {
        return this.numbers;
    }

    public String[] getColors() {
        return this.colors;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
