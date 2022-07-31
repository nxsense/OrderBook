import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWork fileWork = new FileWork();
        FileReader fileReader=new FileReader("input.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        List<Order> bids = new ArrayList<>();
        List<Order> asks = new ArrayList<>();
        OrderBook bidOrderBook = new OrderBook(bids);
        OrderBook askOrderBook = new OrderBook(asks);
        while ((line = reader.readLine()) != null) {
            fileWork.dataWork(line,bidOrderBook,askOrderBook);
        }
        reader.close();
    }
}
