package sosadmemberships;

import sosad.Disease;
import sosad.DiseaseDatabase;
import java.util.HashMap;

/**
 * This class represents the insurance company entity used by the PremiumServiceProvider
 */

public class InsuranceCompany {
    private final HashMap<String, Integer> offers;
    public static int MIN_DISCOUNT_PERCENTAGE = 30;
    public static int MAX_DISCOUNT_PERCENTAGE = 100;
    private final String name;

    // this method will return an integer value between MAX_DISCOUNT_PERCENTAGE
    // and MIN_DISCOUNT_PERCENTAGE inclusive
    private int getDiscountOffer(){
        return (int)Math.floor(Math.random()* (MAX_DISCOUNT_PERCENTAGE - MIN_DISCOUNT_PERCENTAGE +1))
                + MIN_DISCOUNT_PERCENTAGE ;
    }

    /**
     * basic constructor
     * the Insurance company provides an offer to each disease included in the DiseaseDatabase
     *  the offers' estimations are stored in the offers field
     * @param name company's name
     */

    public InsuranceCompany(String name){
        DiseaseDatabase diseaseDatabase = DiseaseDatabase.getInstance();
        offers = new HashMap<>();
        for(Disease d : diseaseDatabase.getDatabase()){
            int a = getDiscountOffer();
            offers.put(d.getName(),a);
        }
        this.name = name;
    }

    // return the offer corresponding to the passed disease
    public int getOffer(Disease disease){
        return offers.get(disease.getName());
    }

    // getter for the company's name
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object another){
        if (!(another  instanceof InsuranceCompany company ))
            return false ;
        return this.getName().equals(company.getName());
    }
}
