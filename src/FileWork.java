import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWork {



    public void fileWrite(String input) throws IOException {
            FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(input);
            bufferedWriter.close();
    }

    public void dataWork (String line, OrderBook bidOrderBook, OrderBook askOrderBook) throws IOException {
        List<Object> values = new ArrayList<>();
        for (String s:line.split(",")) {
            values.add(parse(s));
        }
        String operation = (String) values.get(0);
        switch (operation){
            case "u":{
                int price = (int) values.get(1);
                int size = (int) values.get(2);
                if(values.get(3) == "bid") bidOrderBook.addOrder(price, size);
                if(values.get(3) == "ask") askOrderBook.addOrder(price, size);
            }

            case "o":{
                int size = (int) values.get(2);
                if(values.get(1) == "buy") askOrderBook.deleteLowestOrder(size);
                if(values.get(1) == "ask") bidOrderBook.deleteHighestOrder(size);
            }

            case "q":{
                if(values.get(1) == "best_bid") {
                    System.out.println( bidOrderBook.bestBid().getPrice() + "," +  bidOrderBook.bestBid().getSize() + '\n');
                }
                if(values.get(1) == "best_ask") {
                    System.out.println(askOrderBook.bestAsk().getPrice() + "," + askOrderBook.bestAsk().getSize()+ '\n');
                }
                if(values.get(1) == "size") {
                    int size = -1;
                    int price = (int) values.get(2);
                    List<Order> allOrdersList = new ArrayList<>();
                    allOrdersList.addAll(bidOrderBook.getOrders());
                    allOrdersList.addAll(askOrderBook.getOrders());
                    for (Order order: allOrdersList) {
                        if(order.getPrice() == price) size = order.getSize();
                    }
                    System.out.println(size);
                }
            }

        }
    }

    static Object parse(String s){
        try {
            return Integer.valueOf(s);
        } catch ( Exception ignored) {}

        try {
            return String.valueOf(s);
        } catch ( Exception ignored) {}
        return null;
    }

}
