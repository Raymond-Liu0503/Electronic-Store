import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import java.util.ArrayList;
public class StoreAppView extends Pane implements StoreView{
    private StoreButPane buttons = new StoreButPane();
    private ArrayList<String> storeStock, curCart, popItems;
    private ListView<String> StockList;
    private ListView<String> CartList;
    private ListView<String> popList;
    private TextField numSField;
    private TextField revenueField;
    private TextField amountPerSaleField;
    private int numSales;
    private double rev;
    private Label label3;
    public StoreAppView(){
        // Create the labels

        Label label1 = new Label("Store Summary:");
        label1.relocate(50, 5);
        label1.setPrefSize(110, 30);

        Label label2 = new Label("Store Stock:");
        label2.relocate(295, 5);
        label2.setPrefSize(80, 30);

        label3 = new Label("Current Cart:");
        label3.relocate(600, 5);
        label3.setPrefSize(150, 30);

        Label label4 = new Label("# Sales:");
        label4.relocate(30, 30);
        label4.setPrefSize(80, 30);

        Label label5 = new Label("Revenue:");
        label5.relocate(25, 65);
        label5.setPrefSize(80, 30);

        Label label6 = new Label("$ / Sale:");
        label6.relocate(30, 100);
        label6.setPrefSize(80, 30);

        Label label7 = new Label("Most Popular Items:");
        label7.relocate(35, 135);
        label7.setPrefSize(110, 30);

        // Create the lists

        StockList = new ListView<String>();
        StockList.relocate(185, 30);
        StockList.setPrefSize(295, 300);

        CartList = new ListView<String>();
        CartList.relocate(490, 30);
        CartList.setPrefSize(295, 300);

        popList = new ListView<String>();
        popList.relocate(10, 165);
        popList.setPrefSize(165, 165);

        //Create the text fields

        numSField = new TextField();
        numSField.setPrefSize(95, 30);
        numSField.relocate(80, 30);
        numSField.setEditable(false);

        revenueField = new TextField();
        revenueField.setPrefSize(95, 30);
        revenueField.relocate(80, 65);
        revenueField.setEditable(false);

        amountPerSaleField = new TextField();
        amountPerSaleField.setPrefSize(95, 30);
        amountPerSaleField.relocate(80, 100);
        amountPerSaleField.setEditable(false);

        // Create the buttons
        buttons.relocate(20,335);
        getChildren().add(buttons);

        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, StockList, CartList, popList, numSField, revenueField, amountPerSaleField);
    }
    public void update(ElectronicStore model) {
        ArrayList<Product> temp = model.getStock();
        ArrayList<Pair<Integer, String>> poptemp = model.popularList();
        storeStock =  new ArrayList<String>();
        curCart = new ArrayList<>();
        popItems = new ArrayList<>();
        numSales = model.getNumSales();
        rev = model.getRevenue();


        buttons.getAddButton().disableProperty().bind(StockList.getSelectionModel().selectedItemProperty().isNull());
        buttons.getRemoveButton().disableProperty().bind(CartList.getSelectionModel().selectedItemProperty().isNull());

        if(model.getCart().size() != 0){
            buttons.getSaleButton().setDisable(false);
        }else{
            buttons.getSaleButton().setDisable(true);
        }


        for(int i = 0; i < temp.size(); i++){
            if(model.getStock().get(i).getStockQuantity() != 0){
                storeStock.add(model.getStock().get(i).toString());
            }
        }
        for(int j = 0; j < 3; j++){
            popItems.add(poptemp.get(j).getValue());
        }

        for (String key : model.getCart().keySet()) {
            int value = model.getCart().get(key);
            curCart.add(value + "x " + key);
        }

        if(numSales == 0){
            amountPerSaleField.setText("N/A");
        }else{
            amountPerSaleField.setText(Double.toString(rev/numSales));
        }
        label3.setText("Current Cart " + "($" + model.getCartTotal() + "):");
        StockList.setItems(FXCollections.observableArrayList(storeStock));
        CartList.setItems(FXCollections.observableArrayList(curCart));
        popList.setItems(FXCollections.observableArrayList(popItems));
        numSField.setText(Integer.toString(numSales));
        revenueField.setText(Double.toString(rev));

    }

    public ListView<String> getStockList() {
        return StockList;
    }

    public ListView<String> getCartList() {
        return CartList;
    }

    public ListView<String> getPopList() {
        return popList;
    }

    public StoreButPane getButtons() {
        return buttons;
    }

    public int getNumSales() {
        return numSales;
    }

    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }
}
