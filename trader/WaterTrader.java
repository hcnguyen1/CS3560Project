package trader;

public class WaterTrader extends Trader {

    public WaterTrader() {
        this.maxCounterOffers = 4;
    }

    @Override
    public boolean isOfferAcceptable(Offer offer) {
        ResourceBundle offered = offer.getPlayerOffer();
        ResourceBundle requested = offer.getPlayerRequest();

        int valueGiven = offered.getWater() * 2 + offered.getFood() + offered.getGold();
        int valueRequested = requested.getWater() * 2 + requested.getFood() + requested.getGold();
        return valueGiven >= valueRequested;
    }

    @Override
    public Offer generateCounterOffer(Offer playerOffer) {
        incrementCounterOffers();

        ResourceBundle request = playerOffer.getPlayerRequest();

        int waterRequired = request.getWater(); // 1:1
        int foodRequired = request.getFood() * 2; // 2:1
        int goldRequired = request.getGold() * 2; // 2:1

        ResourceBundle playerOfferBundle = playerOffer.getPlayerOffer();
        ResourceBundle counter = new ResourceBundle(foodRequired, waterRequired, goldRequired);

        return new Offer(playerOfferBundle, request, counter);
    }
}
