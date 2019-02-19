package com.example.spacetrader;

public class PlayerBuilder {
    private String name;
    private String difficulty = "Easy";
    private int pilotPoints = 0;
    private int fighterPoints = 0;
    private int traderPoints = 0;
    private int engineerPoints = 0;
    private int credits = 1000;
    private String spaceship = "Gnat";

    public PlayerBuilder(String nam) {
        this.name = nam;
    }

    public PlayerBuilder difficulty(String diff) {
        this.difficulty = diff;

        return this;
    }

    public PlayerBuilder pilotPoints(int pPoints) {
        this.pilotPoints = pPoints;

        return this;
    }

    public PlayerBuilder fighterPoints(int fPoints) {
        this.fighterPoints = fPoints;

        return this;
    }

    public PlayerBuilder traderPoints(int tPoints) {
        this.traderPoints = tPoints;

        return this;
    }

    public PlayerBuilder engineerPoints(int ePoints) {
        this.engineerPoints = ePoints;

        return this;
    }

    public PlayerBuilder credits(int cred) {
        this.credits = cred;

        return this;
    }

    public PlayerBuilder spaceship(String ship) {
        this.spaceship = ship;

        return this;
    }
}
