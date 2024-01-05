//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class ElectronicStore{
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private ArrayList<Product> stock; //List to hold all products
    private double revenue;
    private HashMap<String, Integer> soldQ;
    private HashMap<String, Integer> cart;
    private double cartTotal;
    private ArrayList<Product> cartItems;
    private int numSales;

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new ArrayList<Product>();
        cart = new HashMap<String, Integer>();
        soldQ = new HashMap<>();
        curProducts = 0;
        cartTotal = 0;
        cartItems = new ArrayList<>();
        numSales = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getStock() {
        return stock;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public int getNumSales() {
        return numSales;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        for(int i = 0; i < stock.size(); i++){
            if(stock.get(i).toString().equals(newProduct.toString())){
                return false;
            }
        }
        stock.add(newProduct);
        soldQ.put(newProduct.toString(), 0);
        return true;
    }

    public void addCart(Product p){
        if(cart.containsKey(p.toString())){
            cart.replace(p.toString(), cart.get(p.toString())+1);
        }else{
            cart.put(p.toString(), 1);
        }
        p.setStockQuantity(p.getStockQuantity() - 1);
        cartTotal = cartTotal + p.getPrice();
        cartItems.add(p);
    }

    public void sellCart(){
        for(Product p: cartItems){
            p.setSoldQuantity(p.getSoldQuantity() + 1);
            soldQ.put(p.toString(), soldQ.get(p.toString()) + 1);
        }
        revenue = revenue + cartTotal;
        cart.clear();
        cartItems.clear();
        cartTotal = 0;
        numSales++;
    }

    public void removeCart(String s){

        for(String key: cart.keySet()){
            int value = cart.get(key);

            if(s.contains(key) && value > 1){
                cart.put(key, value - 1);
            }else{
                if(s.contains(key)){
                    cart.remove(key);
                }
            }
        }
        for(int i = 0; i < cartItems.size(); i++){
            if(s.contains(cartItems.get(i).toString())){
                cartItems.remove(i);
                break;
            }
        }

    }

    public ArrayList<Pair<Integer, String>> popularList(){
        ArrayList<Pair<Integer, String>> popular;
        popular = new ArrayList<Pair<Integer, String>>();
        Pair pair;
        for (String key : soldQ.keySet()) {
            int value = soldQ.get(key);
            pair = new Pair(value, key);
            popular.add(pair);
        }
        Collections.sort(popular, new Comparator<Pair<Integer, String>>() {
            public int compare(Pair<Integer, String> p1, Pair<Integer, String> p2) {
                return p2.getKey().compareTo(p1.getKey());
            }
        });
        return popular;
    }

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 