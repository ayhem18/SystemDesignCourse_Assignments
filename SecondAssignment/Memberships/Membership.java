package sosadmemberships;

/**
 * Membership Enum is used to categorize clients.
 * Depending on their membership, clients will be provided different services
 */

public enum Membership {
    BASIC,PREMIUM,PRO,PRO_PLUS;

    /**
     * this method converts a string value into the corresponding Membership value
     * @param s the passed string
     * @return Returns the corresponding Membership Value
     */
    public static Membership getValue(String s ){
        return switch (s) {
            case "BASIC" -> Membership.BASIC;
            case "PREMIUM" -> Membership.PREMIUM;
            case "PRO" -> Membership.PRO;
            case "PRO+" -> Membership.PRO_PLUS;
            default -> null;
        };
    }
}
