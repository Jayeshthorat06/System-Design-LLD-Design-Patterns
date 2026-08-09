interface Burger
{
    public void prepare();
}

interface BurgerFactory
{
    public Burger createBurger(String type);
}

class basicBurger implements Burger{
    public void prepare()
    {
        System.out.println("Preparing Basic Burger");
    }
}

class standardBurger implements Burger{
    public void prepare()
    {
        System.out.println("Preparing Standard Burger");
    }
}

class premiumBurger implements Burger
{
    public void prepare()
    {
        System.out.println("Preparing Preminum Burger");
    }
}

class basicWheatBurger implements Burger{
    public void prepare()
    {
        System.out.println("Preparing Basic Wheat Burger");
    }
}

class standardWheatBurger implements Burger{
    public void prepare()
    {
        System.out.println("Preparing Standard Wheat Burger");
    }
}

class premiumWheatBurger implements Burger
{
    public void prepare()
    {
        System.out.println("Preparing Preminum Wheat Burger");
    }
}

class singhBurger implements BurgerFactory
{
    public Burger createBurger(String type)
    {
        if(type == "Basic")
        {
            return new basicBurger();
        }
        else if(type == "standard")
        {
            return new standardBurger();
        }
        else if(type == "premium")
        {
            return new premiumBurger();
        }

        return new basicBurger();
    }
}

class kingBurger implements BurgerFactory
{
    public Burger createBurger(String type)
    {
        if(type == "BasicWheat")
        {
            return new basicWheatBurger();
        }
        else if(type == "standardWheat")
        {
            return new standardWheatBurger();
        }
        else if(type == "premiumWheat")
        {
            return new premiumWheatBurger();
        }

        return new basicWheatBurger();
    }
}

class factoryMethod {
    public static void main(String[] args)
    {
        System.out.println("Factory Method");
        BurgerFactory singh = new singhBurger();
        Burger b = singh.createBurger("standard");
        b.prepare();
        BurgerFactory king = new kingBurger();
        Burger b2 = king.createBurger("premiumWheat");
        b2.prepare();

    }
}
