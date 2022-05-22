package pl.vistula.exercise11;

public class MyThread  implements Runnable{

    Thread thread;
    MyThread(String name){
        thread = new Thread(this, name);
    }
    //A manufacturing method that create and starts the thread
    public static MyThread createAndStart (String name){
        MyThread myThread = new MyThread(name);
        myThread.thread.start();
        return myThread;
    }
    //Start executing a new thread
    public void run(){
        System.out.println(thread.getName() + " Start");
        try{
            for(int count = 0; count < 10; count++){
                Thread.sleep(10);
                System.out.println( thread.getName() + " is executed, the counter value " + count);
            }
        }
        catch(InterruptedException e){
            System.out.println( thread.getName()+"   interrupted");
        }
        System.out.println( thread.getName() + "Finish running");
    }
}


class MultiThreads2 {
    public static void main(String[] args){
        System.out.println( "The main thread is running");
        MyThread myThread1 =  MyThread.createAndStart("Child thread #1");
        MyThread myThread2 = MyThread.createAndStart("Child thread #2");
        MyThread myThread3 = MyThread.createAndStart("Child thread #3");
        do{
            System.out.print( ".");
            try{

                myThread1.thread.join();
                myThread2.thread.join();
                myThread3.thread.join();

            }catch(InterruptedException e){
                System.out.println( "The main thread has been terminated");
            }
        } while(myThread1.thread.isAlive() || myThread2.thread.isAlive() || myThread3.thread.isAlive());
        System.out.println( "The main thread is exiting");

    }

}
