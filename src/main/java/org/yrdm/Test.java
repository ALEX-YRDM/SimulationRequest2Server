package org.yrdm;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zbq
 * @Date: 2023/5/18 下午1:05
 */
public class Test {
    final Random random=new Random();
    final int PRI_MIN=1;
    final int PRI_MAX=3;
    final int TIME_MIN=1000;
    final int TIME_MAX=5000;
    final int CAP_MIN=5;
    final int CAP_MAX=10;
    public static void main(String[] args) {
        Test test=new Test();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //PriorityQueue<Server> servers=new PriorityQueue<>(Comparator.comparing(Server::getLoadRate));
        //LinkedList<Server> servers=new LinkedList<>();
        Server[] servers=new Server[4];
        for(int i=0;i< servers.length;i++){
            servers[i]=new Server(i,test.generateCapacity());;
        }
        Request.servers=servers;

        for(int i=1;i<=3000;i++){
            Request request = new Request(i, test.generatePriority(), test.generateProcessTime());
            System.out.println(request);
            executorService.execute(request);
        }
        System.out.println("Qos: "+Request.Qos+" ThroughOut: "+Request.throughOut+" fail: "+Request.fail);
        executorService.shutdown();
    }

    public  int generatePriority(){
        return random.nextInt(PRI_MAX-PRI_MIN)+PRI_MIN;
    }

    public  int generateProcessTime(){
        return random.nextInt(TIME_MAX-TIME_MIN)+TIME_MIN;
    }

    public  int generateCapacity(){
        return random.nextInt(CAP_MAX-CAP_MIN)+CAP_MIN;
    }

}
