package trader;

public class GoldTrader extends Trader {

    public GoldTrader() {
        this.maxCounterOffers = 3;
    }

    @Override
    public boolean isOfferAcceptable(Offer offer) {
        ResourceBundle offered = offer.getPlayerOffer();
        ResourceBundle requested = offer.getPlayerRequest();

        int valueGiven = offered.getGold() * 2 + offered.getFood() + offered.getWater();
        int valueRequested = requested.getGold() * 2 + requested.getFood() + requested.getWater();
        return valueGiven >= valueRequested;
    }

    @Override
    public Offer generateCounterOffer(Offer playerOffer) {
        incrementCounterOffers();

        ResourceBundle request = playerOffer.getPlayerRequest();

        int goldRequired = request.getGold(); // 1:1 for gold
        int foodRequired = request.getFood() * 2; // 2:1 for food
        int waterRequired = request.getWater() * 2; // 2:1 for water

        ResourceBundle playerOfferBundle = playerOffer.getPlayerOffer();
        ResourceBundle counter = new ResourceBundle(foodRequired, waterRequired, goldRequired);

        return new Offer(playerOfferBundle, request, counter);
    }
}