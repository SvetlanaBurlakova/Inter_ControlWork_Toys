package Classes;
import java.util.List;
import java.util.Random;

import Interface.iLotteryBehavior;

public class Lottery implements iLotteryBehavior{
    public String lotteryName;
    public Toy toyFromLottery;
     

    public Lottery(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    @Override
    public Toy setLottery(List<Toy> toyList) {
        int sum_weight = 0;
        for (Toy toy : toyList) {
            if (toy.getCount()!=0)
                sum_weight+=toy.getWeight();
        }
        Random rn = new Random();
        int choice = rn.nextInt(sum_weight);
        int sum_for_lottery = 0;
        for (Toy toy : toyList) {
            if (toy.getCount()!=0){
                sum_for_lottery+=toy.getWeight();
                if (choice<=sum_for_lottery)
                {   
                    toy.setCount(toy.getCount() - 1);
                    return toy;
                }
            }
        }  
        return null;
    }

    
}
