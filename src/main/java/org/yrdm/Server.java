package org.yrdm;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zbq
 * @Date: 2023/5/16 上午10:48
 */
@Data
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
}
