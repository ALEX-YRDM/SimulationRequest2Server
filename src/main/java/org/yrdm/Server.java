package org.yrdm;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zbq
 * @Date: 2023/5/16 上午10:48
 */
//@Data
public class Server {
    /**
     * 服务器编号
     */
    int id;
    /**
     * 服务器最大负载
     */
    int capacity;
    /**
     * 服务器当前负载
     */
    int size;
    /**
     * 服务器当前的负载率
     */
    double loadRate;
    /**
     * 等待队列 暂时没用到
     */
    List<Request> queue;

//    public Server(Integer id, Integer capacity, int size) {
//        this.id = id;
//        this.capacity = capacity;
//        this.size = size;
//
//    }

    public Server(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.queue=new LinkedList<>();
    }

    public Server(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getLoadRate() {
        return loadRate;
    }

    public void setLoadRate(double loadRate) {
        this.loadRate = loadRate;
    }

    public List<Request> getQueue() {
        return queue;
    }

    public void setQueue(List<Request> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", size=" + size +
                ", loadRate=" + loadRate +
                ", queue=" + queue +
                '}';
    }
}
