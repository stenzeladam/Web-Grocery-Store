package groceryStore.GroceryStore;

import java.util.ArrayList;

public class BeerEvaluator {
    public ArrayList<Item> evaluateBeers(int belgianBeers, int dutchBeers, int germanBeers) {
        // Take the quantity of each beer and perform integer division by 6 to determine how many 6-packs for each beer.
        // The sum of (n + k) times (a/b), where k = 0, ... , i-1, i, is the same as
        // (n+0)(a/b) + ... + (n+(i-1))(a/b) + (n+1)(a/b) where a and b are integers, and b is not equal to 0.
        // This is relevant because adding up the sum of 6 discounted beer bottles will be the same as discounting
        // the sum of 6 beer bottles.

        // Per the example given in the assignment:
        // If a Dutch beer is 0.50 EUR per bottle, and a 6 pack of Dutch beers is 2.00 EUR, that means a 6 pack of Dutch
        // beer has a 33.3333% off discount. If 6 packs of Belgian and German beers are 3.00 EUR and 4.00 EUR respectively,
        // then to maintain the same 33.3333% off discount for 6 packs, the default individual bottle price for Belgian
        // and German beer bottles should be 0.75 EUR and 1.00 EUR respectively. Otherwise, if all bottles are 0.50 EUR
        // a 6 pack of German beer would be 4.00 EUR while 6 individual bottles would only be 3.00 EUR, so that'd be a
        // markup and not make much sense.

        ArrayList<Item> beerReceipt = new ArrayList<>();
        int n = 6; // set the number of bottles to define a pack

        double DutchBottlePrice = Prices.getInstance().getDUTCH_BEERS_PRICE();
        double Dutch_n_PackDiscount = 33.3333; // ~33.3333...%

        // No need to add item to receipt if not on order
        if (dutchBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, dutchBeers, "Dutch", DutchBottlePrice, Dutch_n_PackDiscount));
        }

        double BelgianBottlePrice = Prices.getInstance().getBELGIAN_BEERS_PRICE();
        double Belgian_n_PackDiscount = 33.3333; // ~33.3333...%

        if (belgianBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, belgianBeers, "Belgian", BelgianBottlePrice, Belgian_n_PackDiscount));
        }

        double GermanBottlePrice = Prices.getInstance().getGERMAN_BEERS_PRICE();
        double German_n_PackDiscount = 33.3333; // ~33.3333...%

        if (germanBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, germanBeers, "German", GermanBottlePrice, German_n_PackDiscount));
        }

        return beerReceipt;
    }

    public ArrayList<Item> addBeerTypeToReceipt(int n, int beerType, String beerName, double bottlePrice, double packDiscount) {

        ArrayList<Item> localList = new ArrayList<>();
        int numOfPacks = beerType / n;
        int numOfSingleBottles = beerType % n;

        if (numOfPacks > 0) {
            String localItemName = n + "-Pack of " + beerName + " Beer";
            String discountRule = n + "-Pack of Beer";
            double localPackPrice = applyPercentOffDiscount(packDiscount, bottlePrice * n);
            Item beerPack = new Item(numOfPacks, localItemName, discountRule, localPackPrice, localPackPrice * numOfPacks);
            localList.add(beerPack);
        }
        if (numOfSingleBottles > 0) {
            String localItemName = beerName + " Beer Bottle";
            String discountRule = "";
            Item beerBottles = new Item(numOfSingleBottles, localItemName, discountRule, bottlePrice, bottlePrice * numOfSingleBottles);
            localList.add(beerBottles);
        }

        return localList;
    }

    public double applyPercentOffDiscount(double percentOff, double itemPrice) {
        double complementaryPercentage = (1.0 - (percentOff / 100.0));
        double result = itemPrice * complementaryPercentage;
        if (result < 0.0) {
            return 0.0;
        }
        return itemPrice * complementaryPercentage;
    }
}
