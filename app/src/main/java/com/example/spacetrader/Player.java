package com.example.spacetrader;

public class Player {
    private String name;
    private String difficulty;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private String spaceship;

    public Player(String nam, String diff, int pPoints, int fPoints, int tPoints, int ePoints, int cred, String ship) {
        name = nam;
        difficulty = diff;
        pilotPoints = pPoints;
        fighterPoints = fPoints;
        traderPoints = tPoints;
        engineerPoints = ePoints;
        credits = cred;
        spaceship = ship;
    }

    public void setName(String nam) {
        name = nam;
    }

    public String getName() {
        return name;
    }

    public void setDifficulty(String diff) {
        difficulty = diff;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setPilotPoints(int points) {
        pilotPoints = points;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public void setFighterPoints(int points) {
        fighterPoints = points;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public void setTraderPoints(int points) {
        traderPoints = points;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public void setEngineerPoints(int points) {
        engineerPoints = points;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public void setCredits(int cred) {
        credits = cred;
    }

    public int getCredits() {
        return credits;
    }

    public void setSpaceship(String ship) {
        spaceship = ship;
    }

    public String getSpaceship() {
        return spaceship;
    }

}
