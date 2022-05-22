package pl.vistula.exercise11;

public class PriorityMaxMin implements Runnable {

    int count; Thread thread; static boolean stop = false; static String currentName;

    PriorityMaxMin(String name){
        thread = new Thread(this,name);
        count=0;
        currentName = name;

    }

    public void run(){
        System.out.println(thread.getName()+ " starts to operate");
        do {
            count++;
            if (currentName.compareTo(thread.getName()) != 0){
                currentName= thread.getName();
                System.out.println(currentName+ " is executed");
            }
        }while (stop==false && count <10_000_000);
        stop = true;
        System.out.println("\n"+ thread.getName()+ " finishes running");
    }
}


class Task3 {
    public static void main(String[] args) {
        PriorityMaxMin bb1= new PriorityMaxMin("High Priority thread");
        PriorityMaxMin bb2= new PriorityMaxMin("Thread with low priority");
        PriorityMaxMin bb3= new PriorityMaxMin("Thread #1 with normal priority");
        PriorityMaxMin bb4= new PriorityMaxMin("Thread #2 with normal priority");
        PriorityMaxMin bb5= new PriorityMaxMin("Thread #3 with normal priority");

        bb1.thread.setPriority(Thread.MAX_PRIORITY);
        bb2.thread.setPriority(Thread.MIN_PRIORITY);

        bb1.thread.start();bb2.thread.start();bb3.thread.start();bb4.thread.start();bb5.thread.start();

        //calling the program 10 times
        for (int i=0; i<10;i++){

            try{
                bb1.thread.join();bb2.thread.join();bb3.thread.join();bb4.thread.join();bb5.thread.join();
            }catch (InterruptedException e){
                System.out.println("The main thread starts running");

            }

            System.out.println("\nHigh priority thread counted to "+bb1.count);
            System.out.println("The low priority thread added to "+bb2.count);
            System.out.println("#1 Normal priority thread added to "+bb3.count);
            System.out.println("#2 Normal priority thread added to "+bb4.count);
            System.out.println("#3 Normal priority thread added to "+bb5.count);

        }

    }
}
