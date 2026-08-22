interface engine
{
    public void start();
}

class electric implements engine
{
    public void start()
    {
        System.out.println("Electric engine started without noice");
    }
}

class petrol implements engine
{
    public void start()
    {
        System.out.println("Petrol engine started with ignite");
    }
}

class diesel implements engine
{
    public void start()
    {
        System.out.println("Diesel engine started with roar");
    }
}

abstract class car
{
    engine eng;

    public car(engine en)
    {
        this.eng = en;
    }

    abstract public void drive();
}

class suv extends car
{
    public suv(engine eng)
    {
        super(eng);
    }

    public void drive()
    {
        System.out.println("SUV car boarded to show his class");
        eng.start();
    }
}

class sedan extends car
{
    public sedan(engine en)
    {
        super(en);
    }

    public void drive()
    {
        System.out.println("Sedan car boarded to show his class");
        eng.start();
    }
}

class Bridge
{
    public static void main(String[] args) {
        electric electric = new electric();
        petrol petrol = new petrol();
        diesel diesel = new diesel();

        suv suv = new suv(diesel);
        sedan sedan = new sedan(electric);
        sedan sedanB = new sedan(petrol);

        suv.drive();
        sedan.drive();
        sedanB.drive();
    }
}
