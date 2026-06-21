import static java.lang.System.*;
import static java.lang.Math.*;
public class Main {
     static void main(String[] args){
        out.println("Hello World");
        //Using Default Constructor
        Student s1=new Student();
        s1.setName("Fardin");
        s1.setId(1);
        System.out.println(s1.getId());
        System.out.println(s1.getName());
        s1.setFee(new Bkash());
        s1.PayFee(3000,"BDT");
        s1.PayFee(1000,"BDT");
        System.out.println("..............................");
        //Using Parameterised Constructor
        Student s2=new Student(2,"Ahmed");
        s2.print();


        Payment p1=new Bkash();
        System.out.println("Provider: "+p1.provider());
        p1.process(20000,"Usd");
        if(p1.validate(15000)){
            System.out.println("Proceed...1");
        }
        else{
            System.out.println("Not Proceed...1");
        }
        if(p1.validate(30000)){
            System.out.println("Proceed...2");
        }
        else{
            System.out.println("Not Proceed...1");
        }

        int b=10;
        //Integer a=Integer.valueOf(b);//error unnecessary boxing
        //Integer a=b;
         Integer a=10;
        System.out.println(a);
         System.out.println(b);

        //static import
        //int i=Math.max(28,23);
        //int j=(int)Math.pow(4,2);
        int i=max(28,23);
        int j=(int)pow(4,2);
        System.out.println("Max ="+i);
        System.out.println("Result ="+j);
    }
}
