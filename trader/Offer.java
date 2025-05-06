package trader;

// handles trade negotiation between a player and a trader.
// uses ResourceBundle to reduce variable clutter.

public class Offer {

    private final ResourceBundle playerOffer;       // player offer
    private final ResourceBundle playerRequest;     // player request
    private final ResourceBundle traderCounter;     // trader counter-offer

    public Offer(ResourceBundle playerOffer, ResourceBundle playerRequest, ResourceBundle traderCounter) {
        this.playerOffer = playerOffer;
        this.playerRequest = playerRequest;
        this.traderCounter = traderCounter;
    }

    public ResourceBundle getPlayerOffer() {
        return playerOffer;
    }

    public ResourceBundle getPlayerRequest() {
        return playerRequest;
    }

    public ResourceBundle getTraderCounter() {
        return traderCounter;
    }

    public String getDescription() {
        return String.format(
            "Player offers: %s%n" +
            "Player requests: %s%n" +
            "Trader counter-offers: %s for requested resources",
            playerOffer.toString(),
            playerRequest.toString(),
            traderCounter.toString()
        );
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
