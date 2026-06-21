public class Student {
    private int id;
    private String name;
    private Payment fee;//association
    public Student(){

    }
    public Student(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Payment getFee() {
        return fee;
    }

    public void setFee(Payment fee) {
        this.fee = fee;
    }
    public void PayFee(float amount,String currency){
        if(fee.process(amount,currency)){
            System.out.println(fee.provider());
            System.out.println("Payment approved by brunch");
            if(fee.validate(amount)){
                System.out.println("Payment approved by baank");
            }
            else{
                System.out.println("Payment denied by brunch");
            }

        }
        else{
            System.out.println(fee.provider());
            System.out.println("Payment Denied by brunch");
        }

    }

    public void print(){
        System.out.println("Student :"+name);
        System.out.println("Student id :"+id);
    }
}
