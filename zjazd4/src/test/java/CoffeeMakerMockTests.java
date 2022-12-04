import exceptions.InventoryException;
import exceptions.RecipeException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMakerMockTests {

    @Mock
    Recipe recipeMock;

    @Mock
    RecipeBook recipeBookMock;

    @Mock
    CoffeeMaker coffeeMakerMock;

    @Before
    public void setUp() {
        coffeeMakerMock.setRecipes(recipeBookMock);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void shouldCreateMockInstance() {
        assertThat(recipeBookMock, is(notNullValue()));
    }

    @Test
    public void testAddRecipe1(){
        recipeBookMock.addRecipe(recipeMock);

        verify(recipeBookMock, atLeastOnce()).addRecipe(any());
    }

    @Test
    public void testMakeCoffee1(){
        when(coffeeMakerMock.makeCoffee(anyInt(), anyInt()))
                .thenReturn(50);

        assertEquals(coffeeMakerMock.makeCoffee(anyInt(), anyInt()), 50);
    }

    @Test
    public void testMakeCoffee2(){
        when(coffeeMakerMock.makeCoffee(Matchers.eq(1), Matchers.eq(100))).thenReturn(35);

        assertEquals(coffeeMakerMock.makeCoffee(1, 100), 35);
    }

    @Test(expected = MockitoException.class)
    public void testRecipeException() throws RecipeException {
        when(coffeeMakerMock.makeCoffee(anyInt(), anyInt())).thenThrow(new RecipeException("Recipe exception throw tests"));

        exceptionRule.expect(RecipeException.class);
        exceptionRule.expectMessage("Recipe exception throw tests");
    }

    @Test(expected = MockitoException.class)
    public void testInventoryException() throws InventoryException {
        when(coffeeMakerMock.makeCoffee(anyInt(), anyInt())).thenThrow(new InventoryException("Inventory exception throw tests"));

        exceptionRule.expect(RecipeException.class);
        exceptionRule.expectMessage("Inventory exception throw tests");
    }
}