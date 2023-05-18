package org.yrdm;



import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zbq
 * @Date: 2023/5/16 上午10:50
 */
@Data
public class Request implements Runnable{
    private Integer id;

    private Integer priority;

    private Integer processTime;

    //static PriorityQueue<Server> servers;
    //static LinkedList<Server> servers;
    static Server[] servers;

    static Integer Qos=0;

    static Integer throughOut=0;

    static int fail=0;

    public Request(){}
    public Request(Integer id, Integer priority, Integer processTime) {
        this.id = id;
        this.priority = priority;
        this.processTime = processTime;
    }

    //private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        /*Server ser = servers.poll();
        if(ser.getSize()!=ser.getCapacity()){
            ser.setSize(ser.getSize()+1);
            ser.setLoadRate((double)(ser.getSize()/ser.getCapacity()));
            try {
                Thread.sleep(processTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ser.setSize(ser.getSize()-1);
            ser.setLoadRate((double) (ser.getSize()/ser.getCapacity()));
            servers.add(ser);
            Qos+=priority*priority;
            throughOut++;
            System.out.println("Qos : "+Qos+" throughOut : "+throughOut);
        }else{
            servers.add(ser);
            fail++;
            System.out.println("Request "+id+" denied");
        }*/
        int avail=isFull();
        if(avail!=-1){
            servers[avail].size++;
            servers[avail].loadRate=(double) servers[avail].size/servers[avail].capacity;
            try {
                Thread.sleep(processTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            servers[avail].size--;
            servers[avail].loadRate=(double) servers[avail].size/servers[avail].capacity;
            Qos+=priority*priority;
            throughOut++;
            System.out.println("Qos : "+Qos+" throughOut : "+throughOut);
        }else{
            fail++;
            System.out.println("Request "+id+" denied");
        }
    }
    public int isFull(){
        for(var ser:servers){
            if(ser.size!=ser.capacity) return ser.id;
        }
        return -1;
    }

}
