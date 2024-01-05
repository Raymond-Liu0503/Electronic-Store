import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
public class StoreButPane extends Pane{
    private Button resetButton;
    private Button addButton;
    private Button removeButton;
    private Button saleButton;

    // Replace this with your own code
    public StoreButPane(){
        Pane butPane = new Pane();
        /*butPane.setStyle("-fx-background-color: white; " +
                "-fx-border-color: gray; " +
                "-fx-padding: 4 4;");*/
        // Create the buttons

        resetButton = new Button("Reset Store");
        resetButton.relocate(0, 0);
        resetButton.setPrefSize(135, 45);
        resetButton.setStyle("-fx-font: 14 arial; -fx-base: rgb(240, 240, 240); -fx-text-fill: BLACK;");

        addButton = new Button("Add To Cart");
        addButton.relocate(250, 0);
        addButton.setPrefSize(135, 45);
        addButton.setStyle("-fx-font: 14 arial; -fx-base: rgb(240, 240, 240); -fx-text-fill: BLACK;");
        //addButton.setDisable(true);

        removeButton = new Button("Remove from Cart");
        removeButton.relocate(470, 0);
        removeButton.setPrefSize(135, 45);
        removeButton.setStyle("-fx-font: 14 arial; -fx-base: rgb(245, 245, 245); -fx-text-fill: BLACK;");
        removeButton.setDisable(true);

        saleButton = new Button("Complete Sale");
        saleButton.relocate(605, 0);
        saleButton.setPrefSize(135, 45);
        saleButton.setStyle("-fx-font: 14 arial; -fx-base: rgb(245, 245, 245); -fx-text-fill: BLACK;");
        saleButton.setDisable(true);

        butPane.getChildren().addAll(resetButton, addButton, removeButton, saleButton);
        getChildren().addAll(butPane);
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getSaleButton() {
        return saleButton;
    }
}
