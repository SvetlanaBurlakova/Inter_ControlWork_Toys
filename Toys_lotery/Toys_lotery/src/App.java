import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import Classes.Lottery;
import Classes.Shop;
import Classes.Toy;

public class App {
    public static Scanner iScanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Toy toy1 = new Toy(1,"car",5, 4);
        Toy toy2 = new Toy(2,"phone", 3,2);
        Toy toy3 = new Toy(3,"train", 8, 6);
        Toy toy4 = new Toy(4,"ship", 5, 5);
        Toy toy5 = new Toy(5,"gun", 12,8);
        List <Toy> toysList = new ArrayList<Toy>();
        toysList.add(toy1);
        Shop shop = new Shop(toysList);
        shop.addToy(toy2);
        shop.addToy(toy3);
        shop.addToy(toy4);
        shop.addToy(toy5);  
        Lottery lottery = new Lottery("Toy lottery");
        Queue<Toy> queue = new LinkedList<Toy> ();  
        int choice = 1;
        do {
            choice = choice_list(); 
            menu(choice, shop, toysList, queue, lottery);
        } while (choice!=6);

    }
    public static int choice_list(){
        System.out.println("Консольное приложение игрушки.");
        System.out.println("1. Просмотр списка игрушек в магазине");
        System.out.println("2. Добавление новой игрушки в магазин");
        System.out.println("3. Изменение частоты выпадение игрушки");
        System.out.println("4. Проведение лотереи");
        System.out.println("5. Забрать выигрушную игрушку");
        System.out.println("6. Выход из приложения");
        System.out.print("Выберите один из пунктов меню: ");
        String choice_str = iScanner.nextLine();
        try {
            int choice = Integer.parseInt(choice_str);
            return choice;
        } catch (Exception e) {
            System.out.println("Введено не целое число, повторите ввод");
            choice_list();
        }
        return 0;
    }

    public static void menu(int choice, Shop shop, List <Toy> toysList, Queue<Toy> queue, Lottery lottery) throws IOException{
        if (choice == 1){
            ViewShop(shop);
        }
        else if (choice == 2) {
            AddToy(shop, toysList);
        }
        else if (choice == 3) {
            ChangeWeight(shop, toysList);
        }
        else if (choice == 4) {
            RunLottery(toysList, queue, lottery);
        }
        else if (choice == 5) {
            GetToy(shop, queue);
        }
        else if (choice == 6) {
            System.out.println("Приложение завершает работу");
        }
        else {
            System.out.println("Неверно введена команда, повторите выбор");
            int choice_new = choice_list();
            menu(choice_new, shop, toysList, queue, lottery);
        }
    }


    public static void ViewShop(Shop shop){
        System.out.println(shop.toString());
    }

    public static void AddToy(Shop shop, List <Toy> toysList){
        System.out.println("Игрушка в листе: (y/n)");
        String ans = iScanner.nextLine();
        if (ans.equals("y")){
            ViewShop(shop);
            System.out.println("введите ID игрушки");
            String id_str = iScanner.nextLine();
            int id;
            try {
                id = Integer.parseInt(id_str);
            } catch (Exception e) {
                System.out.println("Введено не целое число");
                return;
            }
            System.out.println("Введите количество игрушек");
            String count_str = iScanner.nextLine();
            int count;
            try {
                count = Integer.parseInt(count_str);
            } catch (Exception e) {
                System.out.println("Введено не целое число");
                return;
            }  
            Boolean idExist = false;         
            for (Toy toy : toysList) {
                if (toy.getId() == id){
                    toy.setCount(toy.getCount() + count);
                    System.out.println("Количество игрушек с ID" + id + "обновлено");
                    idExist = true;
                }
            }
            if (!idExist){
                System.out.println("Заданного ID игрушки в магазине нет");
            }
        }
        else {
            System.out.println("Введите название игрушки");
            String name = iScanner.nextLine();
            System.out.println("Введите количество игрушек");
            String count_str = iScanner.nextLine();
            int count;
            try {
                count = Integer.parseInt(count_str);
            } catch (Exception e) {
                System.out.println("Введено не целое число");
                return;
            }  
            System.out.println("Введите частоту выпадения игрушки (целое число)");
            String weight_str = iScanner.nextLine();
            int weight;
            try {
                weight = Integer.parseInt(weight_str);
            } catch (Exception e) {
                System.out.println("Введено не целое число");
                return;
            }
            int id_max = 0;
            for (Toy toy : toysList) {
                if (toy.getId()> id_max){
                    id_max = toy.getId();
                }
            }
            Toy toy_new = new Toy(id_max + 1,name,count,weight);
            shop.addToy(toy_new);
            System.out.println("Игрушка добавлена");
        }
    }

    private static void ChangeWeight(Shop shop,List <Toy> toysList) {
        ViewShop(shop);
        System.out.println("введите ID игрушки");
        String id_str = iScanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(id_str);
        } catch (Exception e) {
            System.out.println("Введено не целое число");
            return;
        }
        System.out.println("Введите частоту выпадения игрушки (целое число)");
        String weight_str = iScanner.nextLine();
        int weight;
        try {
            weight = Integer.parseInt(weight_str);
        } catch (Exception e) {
            System.out.println("Введено не целое число");
            return;
        }
        Boolean idExist = false;
        for (Toy toy : toysList) {
            if (toy.getId() == id){
                    shop.changeWeight(toy, weight);
                    idExist = true;
                }
            }
        if (!idExist){
            System.out.println("Заданного ID игрушки в магазине нет");
        }
    }

    private static void RunLottery(List <Toy> toysList, Queue <Toy> queue, Lottery lottery) {
        System.out.println("Введите кол-во разыгрываемых в лотереи игрушек");
        String toy_lottery_count_str = iScanner.nextLine();
        int toy_lottery_count;
        try {
            toy_lottery_count = Integer.parseInt(toy_lottery_count_str);
        } catch (Exception e) {
            System.out.println("Введено не целое число");
            return;
        }
        for (int i = 0; i < toy_lottery_count; i++) {
            queue.add(lottery.setLottery(toysList));
        }
        System.out.println("В лотереи выиграли следующие игрушки:");
        System.out.println(queue);
    }

    private static void GetToy(Shop shop, Queue <Toy> queue) throws IOException {
        System.out.println("Введите кол-во игрушек, разыгранных в лотерее, которые необходимо забрать");
        String toy_lottery_get_str = iScanner.nextLine();
        int toy_lottery_get;
        try {
            toy_lottery_get = Integer.parseInt(toy_lottery_get_str);
        } catch (Exception e) {
            System.out.println("Введено не целое число");
            return;
        }
        Toy toy;
        System.out.println(queue.size());
        if (queue.size()!=0){
            for (int i = 0; i < toy_lottery_get; i++) {
                if (i<=queue.size()){
                    toy = shop.getToy(queue);
                    try (FileWriter fil = new FileWriter("lottery.txt", true))  {
                        fil.write(toy.toString());
                        fil.append('\n');
                    }
                } 
            }
        }
    }
}
