import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class StoreApp extends Application{
    ElectronicStore model;

    public StoreApp(){
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();
        aPane.setStyle("-fx-background-color: white; " + "-fx-border-color: gray; " + "-fx-padding: 4 4;"); // margin spacing at bottom right
        StoreAppView  view = new StoreAppView();
        view.update(model);
        aPane.getChildren().add(view);

        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.getButtons().getAddButton().setDisable(false);

            }
        });

        view.getButtons().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String temps;
                temps = view.getStockList().getSelectionModel().getSelectedItem();

                for(int i = 0; i< model.getStock().size(); i++){
                    if(model.getStock().get(i).toString().equals(temps)){
                        model.addCart(model.getStock().get(i));
                        view.update(model);
                    }
                }

            }
        });

        view.getButtons().getSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.sellCart();
                view.setNumSales(view.getNumSales() + 1);
                view.update(model);
            }
        });

        view.getButtons().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String temps;
                temps = view.getCartList().getSelectionModel().getSelectedItem();

                //Goes back and adds 1 to the product stock quantity
                //Also subtracts the price of the product from the cart total
                for(int i = 0; i< model.getStock().size(); i++){
                    if(temps.contains(model.getStock().get(i).toString())){
                        model.getStock().get(i).setStockQuantity(model.getStock().get(i).getStockQuantity()+1);
                        model.setCartTotal(model.getCartTotal() - model.getStock().get(i).getPrice());
                    }
                }

                model.removeCart(temps);
                view.update(model);
            }
        });

        view.getButtons().getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                view.update(model);
            }
        });


        primaryStage.setTitle("Electronic Store Application"); // Set title of window
        primaryStage.setScene(new Scene(aPane, 800,400)); // Set size of window
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
