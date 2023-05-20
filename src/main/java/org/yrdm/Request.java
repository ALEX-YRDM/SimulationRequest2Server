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
//@Data
public class Request implements Runnable{
    /**
     * 请求编号
     */
    private Integer id;
    /**
     * 请求优先级
     */
    private Integer priority;
    /**
     * 请求处理时间
     */
    private Integer processTime;

    //static PriorityQueue<Server> servers;
    //static LinkedList<Server> servers;
    /**
     * 请求要操作的服务器
     */
    static Server[] servers;
    /**
     * 服务质量
     */
    static Integer Qos=0;
    /**
     * 吞吐量： 目前含义为请求成功的数量
     */
    static Integer throughOut=0;
    /**
     * 失败次数
     */
    static int fail=0;

    public Request(){}
    public Request(Integer id, Integer priority, Integer processTime) {
        this.id = id;
        this.priority = priority;
        this.processTime = processTime;
    }

    //private Lock lock = new ReentrantLock();

    /**
     * 线程要执行的方法体
     */
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
                /**
                 * 线程睡眠模拟处理时间
                 */
                Thread.sleep(processTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            servers[avail].size--;
            servers[avail].loadRate=(double) servers[avail].size/servers[avail].capacity;
            Qos+=priority*priority;
            throughOut++;
            System.out.println("Qos : "+Qos+" throughOut : "+throughOut+" fail: "+fail);
        }else{
            fail++;
            System.out.println("Request "+id+" denied");
        }
    }

    /**
     * 从头遍历servers，有空闲就返回下标。都满的话返回-1
     * @return
     */
    public int isFull(){
        for(var ser:servers){
            if(ser.size!=ser.capacity) return ser.id;
        }
        return -1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", priority=" + priority +
                ", processTime=" + processTime +
                '}';
    }
}
