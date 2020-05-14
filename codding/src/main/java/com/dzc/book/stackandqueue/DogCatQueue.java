package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-14 19:42
 * <p>
 * 猫狗队列
 * <p>
 * 这个题目考察的是一个设计思路；
 * <p>
 * 有点像mergeSort ，维护一个时间戳， 这样比较的时候就知道谁先假如队列了
 */
public class DogCatQueue {

    private LinkedList<PetEnterQueue> dogQueue;

    private LinkedList<PetEnterQueue> catQueue;

    public DogCatQueue() {
        this.dogQueue = new LinkedList<>();
        this.catQueue = new LinkedList<>();
    }

    public void add(Pet p) {
        if (p.getType() == "dog") {
            dogQueue.add(new PetEnterQueue(p, System.currentTimeMillis()));
        } else {
            catQueue.add(new PetEnterQueue(p, System.currentTimeMillis()));
        }
    }

    public void pollAll() {
        while (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
            if (dogQueue.peek().getTime() > catQueue.peek().getTime()) {
                catQueue.poll();
            } else {
                dogQueue.poll();
            }
        }
        while (!dogQueue.isEmpty()) {
            dogQueue.poll();
        }
        while (!catQueue.isEmpty()) {
            catQueue.poll();
        }
    }

}


class PetEnterQueue {

    private Pet pet;

    private long time;

    public PetEnterQueue(Pet pet, long time) {
        this.pet = pet;
        this.time = time;
    }

    public String getPetType() {
        return this.pet.getType();
    }

    public long getTime() {
        return time;
    }
}






class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {

    public Cat() {
        super("cat");
    }
}
