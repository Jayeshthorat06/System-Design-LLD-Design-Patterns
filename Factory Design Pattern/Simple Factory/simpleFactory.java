interface Burger
{
    public void prepare();
}

class basic implements Burger
{
    public void prepare()
    {
        System.out.println("Basic Burger is prepared");
    }
}

class standard implements Burger
{
    public void prepare()
    {
        System.out.println("Standard Burger is prepared");
    }
}

class premium implements Burger
{
    public void prepare()
    {
        System.out.println("Premium Burger is prepared");
    }
}

public class simpleFactory {
    
    public Burger createFactory(String type)
    {
        if(type == "Basic")
        {
            return new basic();
        }
        else if(type == "standard")
        {
            return new standard();
        }
        else if(type == "premium")
        {
            return new premium();
        }

        return new basic();
    }

    public static void main(String[] args) {
        String type = "standard";

        simpleFactory simple = new simpleFactory();

        Burger b = simple.createFactory(type);
        b.prepare();

        Burger b2 = simple.createFactory("premium");
        b2.prepare();

        Burger b3 = simple.createFactory("basic");
        b3.prepare();

        
    }
}
