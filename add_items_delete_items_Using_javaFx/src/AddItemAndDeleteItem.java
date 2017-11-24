import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddItemAndDeleteItem extends Application{

    Stage window;
    TableView<Product> table;
    TextField nameInput,priceInput,quantityInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Add & Delete");

        TableColumn<Product , String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product , Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product,Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMaxWidth(150);

        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMaxWidth(150);

        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");
        quantityInput.setMaxWidth(50);

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addButtonSelected());


        Button deletButton = new Button("Delete");
        deletButton.setOnAction(event -> deletButtonPressed());

        HBox layout1 = new HBox(10);
        layout1.setPadding(new Insets(10,10,10,10));
        layout1.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deletButton);

        table = new TableView<>();
        table.getColumns().addAll(nameColumn,priceColumn,quantityColumn);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(table,layout1);

        Scene scene = new Scene(layout2,500,300);

        window.setScene(scene);
        window.show();

    }

    public void addButtonSelected(){

        Product product = new Product();
        int i = 0;
        try {
            product.setName(nameInput.getText());
            product.setPrice(Double.parseDouble(priceInput.getText()));
            nameInput.clear();
            priceInput.clear();
            i += 2;

        } catch (NumberFormatException e) {

            priceInput.clear();
            priceInput.setPromptText("Invalid");


        }

        try {
            product.setQuantity(Integer.parseInt(quantityInput.getText()));
            quantityInput.clear();
            i++;
        } catch (NumberFormatException e) {

            quantityInput.clear();
            quantityInput.setPromptText("Invalid");
            quantityInput.setOnAction(event -> {
                setUserAgentStylesheet(STYLESHEET_CASPIAN);
            });
        }


        if(i==3) table.getItems().add(product);
    }

    public void deletButtonPressed(){
        ObservableList<Product> selectedProduct , allProduct;
        allProduct = table.getItems();
        selectedProduct = table.getSelectionModel().getSelectedItems();

        selectedProduct.forEach(allProduct::remove);
    }
}