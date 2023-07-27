package Classes;

import Interface.iToyBehavior;

public class Toy{
    private Integer id;
    private String name;
    private Integer count;
    public Integer weight;
    
    public Toy() {
    }

    public Toy(Integer id, String name, Integer count, Integer weight) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Toy [id=" + id + ", name=" + name + ", count=" + count + ", weight=" + weight + "]";
    }

    


}
