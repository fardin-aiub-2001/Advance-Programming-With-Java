public interface Payment {
    // Every payment provider must implement process and provider
    public boolean process(float amount, String currency);
    public String provider();

    public default boolean validate(float amount){
        if (amount>2000){
            return false;
        }
        return true;
    }

}
