package bonus;


// data structure that stores what the trader is offering and requesting

public class Offer {

    private final int foodOffered;
    private final int waterOffered;
    private final int goldOffered;

    private final int foodRequested;
    private final int waterRequested;
    private final int goldRequested;

    public Offer(int foodOffered, int waterOffered, int goldOffered,
                      int foodRequested, int waterRequested, int goldRequested) {
        this.foodOffered = foodOffered;
        this.waterOffered = waterOffered;
        this.goldOffered = goldOffered;
        this.foodRequested = foodRequested;
        this.waterRequested = waterRequested;
        this.goldRequested = goldRequested;
    }

    // getters
    public int getFoodOffered() {
        return foodOffered;
    }

    public int getWaterOffered() {
        return waterOffered;
    }

    public int getGoldOffered() {
        return goldOffered;
    }

    public int getFoodRequested() {
        return foodRequested;
    }

    public int getWaterRequested() {
        return waterRequested;
    }

    public int getGoldRequested() {
        return goldRequested;
    }

     // returns string description of the offer
    public String getDescription() {
        return String.format(
            "Trader offers: %d food, %d water, %d gold%nTrader wants: %d food, %d water, %d gold",
            foodOffered, waterOffered, goldOffered,
            foodRequested, waterRequested, goldRequested
        );
    }

    @Override
    public String toString() {
        return getDescription();
    }
}

