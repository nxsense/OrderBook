import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    private List<Order> orders = new ArrayList<>();

    public OrderBook(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(int price, int size){
        Order order = new Order(price, size);
        orders.add(order);
    }

    public void deleteLowestOrder(int size){
        int lowestPrice = 100000;
        for (Order order: orders) {
            if (order.getSize() == size && order.getPrice() < lowestPrice)
                lowestPrice = order.getPrice();
        }
        for (Order order:orders) {
            if(order.getSize() == size && order.getPrice() == lowestPrice)
                orders.remove(order);
        }
    }

    public void deleteHighestOrder(int size){
        int highestPrice = -100000;
        for (Order order: orders) {
            if (order.getSize() == size && order.getPrice() > highestPrice)
                highestPrice = order.getPrice();
        }
        for (Order order:orders) {
            if(order.getSize() == size && order.getPrice() == highestPrice)
                orders.remove(order);
        }
    }

    public Order bestBid (){
        Order bestOrder = new Order(0, 0);
        int highestPrice = -100000;
        for (Order order: orders) {
            if (order.getSize() != 0 && order.getPrice()>highestPrice)
                highestPrice = order.getPrice();
        }
        for (Order order:orders) {
            if(order.getPrice() == highestPrice){
                bestOrder.setPrice(order.getPrice());
                bestOrder.setSize(order.getSize());
            }

        }
        return bestOrder;
    }

    public Order bestAsk (){
        Order bestOrder = new Order(0, 0);
        int lowestPrice = 100000;
        for (Order order: orders) {
            if (order.getSize() != 0 && order.getPrice()<lowestPrice)
                lowestPrice = order.getPrice();
        }
        for (Order order:orders) {
            if(order.getPrice() == lowestPrice){
                bestOrder.setPrice(order.getPrice());
                bestOrder.setSize(order.getSize());
            }

        }
        return bestOrder;
    }

    public int orderSize (int price){
        int size = 0;
        for (Order order: orders) {
            if(order.getPrice() == price) size = order.getSize();
        }
        return size;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
