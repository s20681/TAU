import exceptions.InventoryException;
import exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeMakerMockandStubTests {

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;

    private Recipe[] stubRecipies;

    private CoffeeMaker coffeeMaker;

    private RecipeBook recipeBookStub;

    @Before
    public void setUp() throws RecipeException {

        recipeBookStub = mock(RecipeBook.class);
        coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());

        //Coffee recipe
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Latte recipe
        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setAmtChocolate("0");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("3");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("100");

        //Hot chocolate recipe
        recipe3 = new Recipe();
        recipe3.setName("Hot Chocolate");
        recipe3.setAmtChocolate("4");
        recipe3.setAmtCoffee("0");
        recipe3.setAmtMilk("1");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("65");

        stubRecipies = new Recipe[]{recipe1, recipe2, recipe3};

        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);

        when(recipeBookStub.addRecipe(recipe1)).thenReturn(true);
        when(recipeBookStub.addRecipe(recipe2)).thenReturn(true);
        when(recipeBookStub.addRecipe(recipe2)).thenReturn(true);
        when(recipeBookStub.addRecipe(recipe3)).thenReturn(true);

        when(recipeBookStub.deleteRecipe(0)).thenReturn(recipe1.getName());
        when(recipeBookStub.deleteRecipe(1)).thenReturn(recipe2.getName());
        when(recipeBookStub.deleteRecipe(2)).thenReturn(recipe2.getName());
        when(recipeBookStub.deleteRecipe(3)).thenReturn(recipe3.getName());

        when(recipeBookStub.editRecipe(0, recipe1)).thenReturn(recipe1.getName());
        when(recipeBookStub.editRecipe(1, recipe2)).thenReturn(recipe2.getName());
        when(recipeBookStub.editRecipe(2, recipe2)).thenReturn(recipe2.getName());
        when(recipeBookStub.editRecipe(3, recipe3)).thenReturn(recipe3.getName());

    }

    @Test
    public void testMakeCoffee() {
        coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
        assertTrue(true);
    }

    @Test
    public void testMakeCoffee1() {
        coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
        assertEquals(0, coffeeMaker.makeCoffee(0, 50));
    }

    @Test
    public void testMakeCoffee2() {
        assertEquals(65, coffeeMaker.makeCoffee(1, 65));
    }

    @Test
    public void testMakeCoffee3() {
        assertEquals(35, coffeeMaker.makeCoffee(2, 100));
    }

    @Test
    public void testInvalidPurchase2() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(10, coffeeMaker.makeCoffee(0, 10));
    }

    @Test
    public void testInvalidPurchase1() {
        assertEquals(50, coffeeMaker.makeCoffee(0, 100));
    }

    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    @Test
    public void testaddSugar3() throws InventoryException {
        Inventory I = new Inventory();
        try {
            I.addSugar("-1");
        } catch (Exception e) {
            assertEquals(15, I.getSugar());
        }
    }

    @Test
    public void testaddchocolate1() throws InventoryException {
        coffeeMaker.addInventory("0", "0", "0", "5");
        coffeeMaker.checkInventory();
        Inventory I = new Inventory();
        assertEquals(15, I.getChocolate());
    }

    @Test
    public void testaddchocolate2() throws InventoryException {
        Inventory I = new Inventory();
        I.addChocolate("5");
        assertEquals(20, I.getChocolate());
    }

    @Test
    public void testaddchocolate3() throws InventoryException {
        Inventory I = new Inventory();
        try {
            I.addChocolate("-1");
        } catch (Exception e) {
            assertEquals(15, I.getChocolate());
        }
    }

    @Test
    public void testAddMilk1() throws InventoryException {
        Inventory I = new Inventory();
        coffeeMaker.addInventory("0", "5", "0", "0");
        assertEquals(20, I.getMilk());
    }

    @Test
    public void testAddMilk2() throws InventoryException {
        Inventory I = new Inventory();
        I.addMilk("5");
        assertEquals(20, I.getMilk());
    }

    @Test
    public void testAddMilk3() throws InventoryException {
        Inventory I = new Inventory();
        try {
            I.addMilk("-100");
        } catch (Exception e) {
            assertEquals(15, I.getMilk());
        }
    }

    @Test
    public void testAddcoffe1() throws InventoryException {
        coffeeMaker.addInventory("5", "0", "0", "0");
        Inventory I = new Inventory();
        assertEquals(15, I.getCoffee());
    }

    @Test
    public void testAddcoffe2() throws InventoryException {
        Inventory I = new Inventory();
        I.addCoffee("5");
        assertEquals(20, I.getCoffee());
    }

    @Test
    public void testAddcoffe3() throws InventoryException {
        Inventory I = new Inventory();
        try {
            I.addCoffee("-9");
        } catch (Exception e) {
            assertEquals(15, I.getCoffee());
        }
    }

    @Test
    public void testAddcoffe4() throws InventoryException {
        Inventory I = new Inventory();
        try {
            I.addCoffee("-9");
        } catch (Exception e) {
            assertEquals(15, I.getCoffee());
        }
    }

    @Test
    public void testSetCoffee() {
        Inventory I = new Inventory();
        I.setCoffee(-9);
        assertEquals(15, I.getCoffee());
    }

    @Test
    public void testSetAmtCoffee() throws RecipeException {
        Recipe R = new Recipe();
        try {
            R.setAmtCoffee("asd");
        } catch (Exception e) {
            assertEquals(0, R.getAmtCoffee());
        }
    }

    @Test
    public void testSetMilk() {
        Inventory I = new Inventory();
        I.setMilk(-5);
        assertEquals(15, I.getMilk());
    }

    @Test
    public void testSetAmtMilk() throws RecipeException {
        Recipe R = new Recipe();
        try {
            R.setAmtMilk("asd");
        } catch (Exception e) {
            assertEquals(0, R.getAmtMilk());
        }
    }

    @Test
    public void testSetSugar() {
        Inventory I = new Inventory();
        I.setSugar(-100);
        assertEquals(15, I.getSugar());
    }

    @Test
    public void testSetChocolate() {
        Inventory I = new Inventory();
        I.setChocolate(-50);
        assertEquals(15, I.getChocolate());
    }

    @Test
    public void testCheckChange1() {
        assertEquals(50, coffeeMaker.makeCoffee(0, 100));

    }

    @Test
    public void testCheckChange2() {
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));

    }


}