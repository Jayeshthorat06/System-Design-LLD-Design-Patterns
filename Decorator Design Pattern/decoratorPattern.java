import java.security.PublicKey;

interface Icharacter
{
    public String getAbilities();
}

class mario implements Icharacter
{
    public String getAbilities()
    {
        return "Mario";
    }
}

abstract class decorator implements Icharacter
{
    Icharacter ch;
    
    public decorator(Icharacter ch)
    {
        this.ch = ch;
    }
}

class heightUpDecorator extends decorator
{
    public heightUpDecorator(Icharacter ch)
    {
        super(ch);
    }

    public String getAbilities()
    {
        return ch.getAbilities() + "Height UP functionality done";
    }
}

class gunPowerDecorator extends decorator
{
    public gunPowerDecorator(Icharacter ch)
    {
        super(ch);
    }

    public String getAbilities()
    {
        return ch.getAbilities() + "Gun Power Functionality done";
    }
}

class starPowerDecorator extends decorator
{
    public starPowerDecorator(Icharacter ch)
    {
        super(ch);
    }

    public String getAbilities()
    {
        return ch.getAbilities() + "Star Power Functionality done";
    }
}

class decoratorPattern
{
    public static void main(String[] args) {
        // Icharacter mario = new mario();
        // System.out.println(mario.getAbilities()); 

        // mario = new heightUpDecorator(mario);
        // System.out.println(mario.getAbilities());

        // mario = new gunPowerDecorator(mario);
        // System.out.println(mario.getAbilities());

        // mario = new starPowerDecorator(mario);
        // System.out.println(mario.getAbilities());

        Icharacter ch = new starPowerDecorator(new gunPowerDecorator(new heightUpDecorator(new mario())));
        System.err.println(ch.getAbilities());


    }
}