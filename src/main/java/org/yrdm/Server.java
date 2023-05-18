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
    int id;

    int capacity;

    int size;

    double loadRate;

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
