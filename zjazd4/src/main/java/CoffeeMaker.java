import exceptions.InventoryException;

public class CoffeeMaker {
    private RecipeBook recipeBook;
    private Inventory inventory;

    public CoffeeMaker(RecipeBook recipeBook, Inventory inventory) {
        this.recipeBook = recipeBook;
        this.inventory = inventory;
    }

    public boolean addRecipe(Recipe r) {
        return recipeBook.addRecipe(r);
    }

    public String deleteRecipe(int recipeToDelete) {
        return recipeBook.deleteRecipe(recipeToDelete);
    }

    public String editRecipe(int recipeToEdit, Recipe r) {
        return recipeBook.editRecipe(recipeToEdit, r);
    }

    public void addInventory(String amtCoffee, String amtMilk, String amtSugar, String amtChocolate) throws InventoryException {
        inventory.addCoffee(amtCoffee);
        inventory.addMilk(amtMilk);
        inventory.addSugar(amtSugar);
        inventory.addChocolate(amtChocolate);
    }

    public String checkInventory() {
        return inventory.toString();
    }

    public int makeCoffee(int recipeToPurchase, int amtPaid) {
        int change = 0;

        if (getRecipes()[recipeToPurchase] == null) {
            change = amtPaid;
        } else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) {
            if (inventory.useIngredients(getRecipes()[recipeToPurchase])) {
                change = amtPaid - getRecipes()[recipeToPurchase].getPrice();
            } else {
                change = amtPaid;
            }
        } else {
            change = amtPaid;
        }

        return change;
    }

    public Recipe[] getRecipes() {
        return recipeBook.getRecipes();
    }

    public void setRecipes(RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }
}