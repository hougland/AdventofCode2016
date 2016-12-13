package com.hougland.adventofcode.day10;

public class Bot {
    int botNumber = -1;
    int high = -1;
    int low = -1;
    int only = -1;

    public Bot(int botNumber, int initial) {
        this.botNumber = botNumber;
        this.only = initial;
    }

    public Bot(int botNumber) {
        this.botNumber = botNumber;
    }

    public int getBotNumber() {
        return this.botNumber;
    }

    public boolean hasTwoCards() {
        return high > 0 && low > 0;
    }

    public boolean hasNoCards() {
        return only < 0 && high < 0 && low < 0;
    }

    public void addNumber(int number) {
        // what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips
        if ((number == 61 || only == 61) && (number == 17 || only == 17)) {
            System.out.println("FOUND BOT REQUESTED. Bot number: " + this.botNumber);
        }

        if (hasNoCards()) {
            only = number;
        } else if (number > only) {
            high = number;
            low = only;
            only = -1;
        } else if (number <= only) {
            low = number;
            high = only;
            only = -1;
        } else if (hasTwoCards()) {
            throw new IllegalStateException("Can't have more than 2 cards");
        } else {
            throw new IllegalStateException("WTF");
        }
    }

    public void giveHigh() {
        // what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips
        if ((high == 61 || low == 61) && (high == 17 || low == 17)) {
            System.out.println("FOUND BOT REQUESTED. Bot number: " + this.botNumber);
        }

        high = -1;
        only = low;
        low = -1;
    }

    public void giveLow() {
        // what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips
        if ((high == 61 || low == 61) && (high == 17 || low == 17)) {
            System.out.println("FOUND BOT REQUESTED. Bot number: " + this.botNumber);
        }

        low = -1;
        only = high;
        high = -1;
    }

    public void giveAll() {
        // what is the number of the bot that is responsible for comparing value-61 microchips with value-17 microchips
        if ((high == 61 || low == 61) && (high == 17 || low == 17)) {
            System.out.println("FOUND BOT REQUESTED. Bot number: " + this.botNumber);
        }

        low = -1;
        only = -1;
        high = -1;
    }
}
