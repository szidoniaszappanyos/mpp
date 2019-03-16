package service;

import java.util.List;

public class Pizza {
    private String pizzaMessage;
    private Ingredient ExtraIngredient;
    private List<Ingredient> ingredients;

    public Pizza(String pizzaMessage) {
        this.pizzaMessage = pizzaMessage;
        System.out.println(pizzaMessage);
    }

    public Pizza() {
    }

    public String getPizzaMessage() {
        return pizzaMessage;
    }

    public void setPizzaMessage(String pizzaMessage) {
        this.pizzaMessage = pizzaMessage;
    }

    public Ingredient getExtraIngredient() {
        return ExtraIngredient;
    }

    public void setExtraIngredient(Ingredient extraIngredient) {
        this.ExtraIngredient = extraIngredient;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaMessage='" + pizzaMessage + '\'' +
                ", ExtraIngredient=" + ExtraIngredient +
                ", ingredients=" + ingredients +
                '}';
    }
}
