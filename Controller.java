package application;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
    // Variables to construct an instance of pizza
    String size = "Small";
    boolean vegetarian = false;
    String cheese = "Single";
    String pineapple = "None";
    String greenPepper = "None";
    String ham = "None";
    // Number of pizzas ordered
    int numOfPizzas = 0;
    // Final cost before taxes
    float totalCost = 0;
    private static ArrayList<LineItem> orders = new ArrayList<>();
    //Instantiated initial pizza class to act as default
    Pizza pizza = new Pizza (size, false, cheese, pineapple, greenPepper, ham);

    // FXML objects
    @FXML
    private ChoiceBox<String> sizeChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> vegetarianChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> cheeseChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> pineapplesChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> greenPeppersChoice = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> hamChoice = new ChoiceBox<>();

    @FXML
    private TextField quantity;

    @FXML
    private TextField totalOrderCost;

    @FXML
    private TextArea currentOrder;

    @FXML
    private TextField pizzaCost;

    @FXML
    private TextField totalCostPizza;

    private ObservableList<String> sizeList = FXCollections.observableArrayList(
            "Small", "Medium", "Large");

    private ObservableList<String> cheeseList = FXCollections.observableArrayList(
            "Single", "Double", "Triple");

    private ObservableList<String> vegetarianList = FXCollections.observableArrayList(
            "Yes", "No");

    private ObservableList<String> pineapplesList = FXCollections.observableArrayList(
            "None", "Single");

    private ObservableList<String> greenPeppersList = FXCollections.observableArrayList(
            "None", "Single");

    private ObservableList<String> hamList = FXCollections.observableArrayList(
            "None", "Single");

    public Controller() throws IllegalPizza {
    }


    // Button click for adding the pizza order
    @FXML
    void addOnClick() throws IllegalPizza {
        // Checks if the quantity of pizzas are valid before updating the order list
        if (numOfPizzas >= 1) {
            // Adds the LineItem to the array list of lineItems
            orders.add(new LineItem(numOfPizzas, pizza));
            // The conditional prevents the addition of a newline character to the first entry
            if (orders.size() > 1) {
                currentOrder.appendText("\n" + new LineItem(numOfPizzas, pizza).toString());
            } else {
                currentOrder.appendText(new LineItem(numOfPizzas, pizza).toString());
            }
            totalCost = totalCost + (numOfPizzas * pizza.getCost());
            totalOrderCost.setText(String.format("$%.2f\n", totalCost));
        }
    }

    // Initializing of the program
    @FXML
    void initialize() {

        // Dropdown list for user to choose size of pizza
        sizeChoice.setItems(sizeList);
        sizeChoice.setValue("Small");
        sizeChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Changes the size variable if the user chooses a new value
            this.size = newVal;
            // Instantiates a new pizza object with the modified size
            try {
                pizza = new Pizza (size, vegetarian, cheese, pineapple, greenPepper, ham);
            } catch (IllegalPizza illegalPizza) {
                illegalPizza.printStackTrace();
            }
            // Updates the cost per pizza of the new modified pizza
            pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
            // Updates the total cost of the total amount of modified pizzas
            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
        });

        // Dropdown list for user to choose cheese toppings of pizza
        cheeseChoice.setItems(cheeseList);
        cheeseChoice.setValue("Single");
        cheeseChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Changes the cheese variable if the user chooses a new value
            this.cheese = newVal;
            // Instantiates a new pizza object with the modified cheese toppings
            try {
                pizza = new Pizza (size, vegetarian, cheese, pineapple, greenPepper, ham);
            } catch (IllegalPizza illegalPizza) {
                illegalPizza.printStackTrace();
            }
            // Updates the cost per pizza of the new modified pizza
            pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
            // Updates the total cost of the total amount of modified pizzas
            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
        });

        // Dropdown list for user to choose whether or not the pizza is vegetarian
        vegetarianChoice.setItems(vegetarianList);
        vegetarianChoice.setValue("Yes");
        vegetarianChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Switches the vegetarian value from true to false and vice versa if the user desires to
            if (newVal.equals("Yes")) {
               this.vegetarian = true;
               // Sets the ability to change the ham topping to false
               hamChoice.setDisable(true);
               hamChoice.setValue("None");
               this.ham = "None";
               // Updates the price per pizza and the total cost for the pizzas
               pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
               totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
            } else {
                // Sets the ability to change the ham topping to false
                this.vegetarian = false;
                hamChoice.setDisable(false);
            }
        });

        // Dropdown list for user to choose pineapple toppings of pizza
        pineapplesChoice.setItems(pineapplesList);
        pineapplesChoice.setValue("None");
        pineapplesChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Changes the pineapple variable if the user chooses a new value
            this.pineapple = newVal;
            try {
                // Instantiates a new pizza object with the modified pineapple toppings
                pizza = new Pizza (size, vegetarian, cheese, pineapple, greenPepper, ham);
            } catch (IllegalPizza illegalPizza) {
                illegalPizza.printStackTrace();
            }
            // Updates the value for the price of each pizza and total cost of all pizzas
            pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
        });

        // Dropdown list for user to choose green pepper toppings of pizza
        greenPeppersChoice.setItems(greenPeppersList);
        greenPeppersChoice.setValue("None");
        greenPeppersChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Changes the green peppers variable if the user chooses a new value
            this.greenPepper = newVal;
            try {
                // Instantiates a new pizza object with the modified green pepper toppings
                pizza = new Pizza (size, vegetarian, cheese, pineapple, greenPepper, ham);
            } catch (IllegalPizza illegalPizza) {
                illegalPizza.printStackTrace();
            }
            // Updates the value for the price of each pizza and total cost of all pizzas
            pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
        });

        // Dropdown list for user to choose ham toppings of pizza
        hamChoice.setItems(hamList);
        hamChoice.setValue("None");
        hamChoice.valueProperty().addListener((observableValue, oldVal, newVal) ->
        {
            // Changes the hams variable if the user chooses a new value
            this.ham = newVal;
            try {
                // Instantiates a new pizza object with the modified ham toppings
                pizza = new Pizza (size, vegetarian, cheese, pineapple, greenPepper, ham);
            } catch (IllegalPizza illegalPizza) {
                illegalPizza.printStackTrace();
            }
            // Updates the value for the price of each pizza and total cost of all pizzas
            pizzaCost.setText(String.format("$%.2f\n", pizza.getCost()));
            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
        });

        // Text field to obtain the quantity of the order
        quantity.textProperty().addListener((observableValue, oldText, newText) ->
        {

            if (newText != null && !newText.isEmpty()) {
                    // Tests to see that the number is valid
                    try {
                        if (Integer.parseInt(newText) <= 100 && Integer.parseInt(newText) >= 1) {
                            this.numOfPizzas = Integer.parseInt(newText);
                            totalCostPizza.setText(String.format("$%.2f\n", pizza.getCost() * numOfPizzas));
                        } else {
                            // Prevents the number from being typed greater or less than range
                            ((StringProperty)observableValue).setValue(oldText);
                        }
                    } catch (NumberFormatException e) {
                        // Prevents number from being typed if it is not an integer
                        ((StringProperty)observableValue).setValue(oldText);
                    }
            } else {
                totalCostPizza.setText(String.format("$%.2f\n", 0.00));
                numOfPizzas = 0;
            }

        });




    }
}
