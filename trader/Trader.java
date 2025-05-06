// Abstract Trader class uses ResourceBundle to consolidate resource variables
package trader;

public abstract class Trader {

    protected int counterOffersGiven = 0;
    protected int maxCounterOffers;

    public boolean canCounterOffer() {
        return counterOffersGiven < maxCounterOffers;
    }

    public void incrementCounterOffers() {
        counterOffersGiven++;
    }

    public abstract boolean isOfferAcceptable(Offer offer);

    public abstract Offer generateCounterOffer(Offer playerOffer);
}