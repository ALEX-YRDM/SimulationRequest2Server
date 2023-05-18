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
    final int PRI_MIN=1;//优先级的最小值
    final int PRI_MAX=3;//优先级最大值
    final int TIME_MIN=1000;//处理时间最小值
    final int TIME_MAX=5000;//处理时间最大值
    final int CAP_MIN=5;//服务器负载最小值
    final int CAP_MAX=10;//服务器负载最大值
    public static void main(String[] args) {
        /**
         * 前面为初始化操作
         */
        Test test=new Test();
        //开启线程池  池中线程数由参数决定
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //PriorityQueue<Server> servers=new PriorityQueue<>(Comparator.comparing(Server::getLoadRate));
        //LinkedList<Server> servers=new LinkedList<>();
        Server[] servers=new Server[4];
        for(int i=0;i< servers.length;i++){
            servers[i]=new Server(i,test.generateCapacity());;
        }
        Request.servers=servers;

        /**
         * 模拟3000次请求，通过for开启3000个线程
         */
        for(int i=1;i<=3000;i++){
            Request request = new Request(i, test.generatePriority(), test.generateProcessTime());
            System.out.println(request);
            executorService.execute(request);
        }
        System.out.println("Qos: "+Request.Qos+" ThroughOut: "+Request.throughOut+" fail: "+Request.fail);
        executorService.shutdown();
    }

    /**
     * 生成优先级的  目前是随机在PRI_MIN到PRI_MAX之间
     * @return
     */
    public  int generatePriority(){
        return random.nextInt(PRI_MAX-PRI_MIN)+PRI_MIN;
    }

    /**
     * 处理时间也是随机
     * @return
     */
    public  int generateProcessTime(){
        return random.nextInt(TIME_MAX-TIME_MIN)+TIME_MIN;
    }

    /**
     * 服务器最大负载随机生成
     * @return
     */
    public  int generateCapacity(){
        return random.nextInt(CAP_MAX-CAP_MIN)+CAP_MIN;
    }

}
