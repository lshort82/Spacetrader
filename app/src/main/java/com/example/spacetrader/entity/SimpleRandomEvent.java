package com.example.spaceTrader.entity;

@SuppressWarnings("unused")
public enum SimpleRandomEvent {
    DEHYDRATED(0,"Dehydrated Travelers",
            "You have come across a neutral spaceship of pilots trying to escape their home planet. Perhaps if you were to help them they would reward your kindness.",
            Item.WATER, 3, "pilot", 1),
    FREEZING(1,"Freezing Cold",
                       "A group of engineers seem to have forgotten to bring their coats into the freezing void of space!",
               Item.FURS, 2, "engineer", 2);

    private final String title;
    private final String description;
    private final Item goodNeeded;
    private final int amtNeeded;
    private final String benefit;
    private final int benefitAmt;
    //private final int index;
    SimpleRandomEvent(int index, String title, String description, Item goodNeeded, int amtNeeded, String benefit, int benefitAmt) {
        this.title = title;
        this.description =description;
        this.goodNeeded = goodNeeded;
        this.amtNeeded = amtNeeded;
        this.benefit = benefit;
        this.benefitAmt = benefitAmt;
        //this.index = index;
    }

    public int getAmtNeeded() {
        return amtNeeded;
    }

    public int getBenefitAmt() {
        return benefitAmt;
    }

    public Item getGoodNeeded() {
        return goodNeeded;
    }

    public String getBenefit() {
        return benefit;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    /*public int getIndex() {
        return index;
    }*/

    public String getGoodName() {
        return goodNeeded.getName();
    }
}
