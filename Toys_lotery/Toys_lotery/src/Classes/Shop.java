package Classes;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Interface.iToyBehavior;

public class Shop implements iToyBehavior{
    private List<Toy> toyList;
    private PriorityQueue<Toy> toyQueue;

    public Shop(List<Toy> toyList) {
        this.toyList = toyList;
    }

    @Override
    public void addToy(Toy toy) {
        toyList.add(toy);
        
    }

    @Override
    public void changeWeight(Toy toy,Integer weight) {
        toy.setWeight(weight);
        
    }
    @Override
    public Toy getToy(Queue<Toy> toyQueue) {
        return toyQueue.remove();
    }

    @Override
    public String toString() {
        return "Shop [toyList=" + toyList + "]";
    }




    
}
