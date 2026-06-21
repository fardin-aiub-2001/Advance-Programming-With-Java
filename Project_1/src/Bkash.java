public class Bkash implements Payment{
    @Override
    public boolean process(float amount, String currency){
        System.out.println("Bkash is processing: "+amount+"  "+currency);
        if (amount>20000){
            return false;
        }
        return true;
    }

    public String provider(){
        return "Bkash";
    }
    public boolean validate(float amount){
        if (amount>20000){
            return false;
        }
        return true;
    }
}
