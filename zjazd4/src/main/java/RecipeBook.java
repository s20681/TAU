public interface RecipeBook {

    public Recipe[] getRecipes();

    public boolean addRecipe(Recipe r);

    public String deleteRecipe(int recipeToDelete);

    public String editRecipe(int recipeToEdit, Recipe newRecipe);

}