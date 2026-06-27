public class MyThread implements Runnable{

    Thread T1;

    public MyThread(String name){
        T1= new Thread(this, name);
        System.out.println("New Thread created"+T1.getName());
    }
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Thread_name: "+T1.getName()+"Loop: "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(T1.getName() + " interrupted");
            }
        }
    }
}


