public class Nagad implements Payment{
    @Override
    public boolean process(float amount, String currency){
        System.out.println("Nagad is processing: "+amount+"  "+currency);
        return false;
    }

    public String provider(){
        return "Nagad";
    }
}
