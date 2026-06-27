public class Main {
    static void main(String[] args) throws InterruptedException {
        MyThread c1= new MyThread("Child1");
        MyThread c2= new MyThread("Child2");

        c1.run();
        c2.run();

        c1.run();//will not create new thread run in the existing method
        c1.T1.start();//create a new thread
        c1.T1.join();//add throws Interrupted Exception

        c2.T1.start();
        c2.T1.join();

        System.out.println("Main Thread exiting...");

    }
}
