import exceptions.RecipeException;

public class Recipe {
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;

    public Recipe() {
        this.name = "";
        this.price = 0;
        this.amtCoffee = 0;
        this.amtMilk = 0;
        this.amtSugar = 0;
        this.amtChocolate = 0;
    }

    public int getAmtChocolate() {
        return amtChocolate;
    }

    public void setAmtChocolate(String chocolate) throws RecipeException {
        int amtChocolate = 0;
        try {
            amtChocolate = Integer.parseInt(chocolate);
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of chocolate must be a positive integer");
        }
        if (amtChocolate >= 0) {
            this.amtChocolate = amtChocolate;
        } else {
            throw new RecipeException("Units of chocolate must be a positive integer");
        }
    }

    public int getAmtCoffee() {
        return amtCoffee;
    }

    public void setAmtCoffee(String coffee) throws RecipeException {
        int amtCoffee = 0;
        try {
            amtCoffee = Integer.parseInt(coffee);
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of coffee must be a positive integer");
        }
        if (amtCoffee >= 0) {
            this.amtCoffee = amtCoffee;
        } else {
            throw new RecipeException("Units of coffee must be a positive integer");
        }
    }

    public int getAmtMilk() {
        return amtMilk;
    }

    public void setAmtMilk(String milk) throws RecipeException {
        int amtMilk = 0;
        try {
            amtMilk = Integer.parseInt(milk);
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of milk must be a positive integer");
        }
        if (amtMilk >= 0) {
            this.amtMilk = amtMilk;
        } else {
            throw new RecipeException("Units of milk must be a positive integer");
        }
    }

    public int getAmtSugar() {
        return amtSugar;
    }

    public void setAmtSugar(String sugar) throws RecipeException {
        int amtSugar = 0;
        try {
            amtSugar = Integer.parseInt(sugar);
        } catch (NumberFormatException e) {
            throw new RecipeException("Units of sugar must be a positive integer");
        }
        if (amtSugar >= 0) {
            this.amtSugar = amtSugar;
        } else {
            throw new RecipeException("Units of sugar must be a positive integer");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(String price) throws RecipeException {
        int amtPrice = 0;
        try {
            amtPrice = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new RecipeException("Price must be a positive integer");
        }
        if (amtPrice >= 0) {
            this.price = amtPrice;
        } else {
            throw new RecipeException("Price must be a positive integer");
        }
    }

    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Recipe other = (Recipe) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}