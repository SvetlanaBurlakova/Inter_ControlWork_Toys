package Interface;

import java.util.Queue;

import Classes.Toy;

public interface iToyBehavior {
    public void addToy(Toy toy);
    public void changeWeight(Toy toy, Integer weight);
    public Toy getToy(Queue<Toy> toyQueue);
}
