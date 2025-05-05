package trader;

public class FoodTrader extends Trader {

    public FoodTrader() {
        this.maxCounterOffers = 6;
    }

    @Override
    public boolean isOfferAcceptable(Offer offer) {
        ResourceBundle offered = offer.getPlayerOffer();
        ResourceBundle requested = offer.getPlayerRequest();

        int valueGiven = offered.getFood() * 2 + offered.getWater() + offered.getGold();
        int valueRequested = requested.getFood() * 2 + requested.getWater() + requested.getGold();
        return valueGiven >= valueRequested;
    }

    @Override
    public Offer generateCounterOffer(Offer playerOffer) {
        incrementCounterOffers();

        ResourceBundle request = playerOffer.getPlayerRequest();

        int foodRequired = request.getFood(); // 1:1
        int waterRequired = request.getWater() * 2; // 2:1
        int goldRequired = request.getGold() * 2; // 2:1

        ResourceBundle playerOfferBundle = playerOffer.getPlayerOffer();
        ResourceBundle counter = new ResourceBundle(foodRequired, waterRequired, goldRequired);

        return new Offer(playerOfferBundle, request, counter);
    }
}
