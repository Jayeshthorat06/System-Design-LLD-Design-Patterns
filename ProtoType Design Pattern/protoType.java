interface clonable
{
    public clonable clone();
}

class NPC implements clonable
{
    String name;
    int health;
    int power;
    int defence;

    public NPC(String name, int health, int power, int defence)
    {
        this.name = name;
        this.health = health;
        this.power = power;
        this.defence = defence;
    }

    public NPC(NPC n)
    {
        this.name = n.name;
        this.health = n.health;
        this.power = n.power;
        this.defence = n.defence;
    }

    public void setname(String name)
    {
        this.name = name;
    }

    public void sethealth(int health)
    {
        this.health = health;
    }

    public void setpower(int power)
    {
        this.power = power;
    }

    public void setdefence(int defence)
    {
        this.defence = defence;
    }

    public clonable clone()
    {
        return new NPC(this);
    }
}

class protoType
{
    public static void main(String[] args) {
        NPC n1 = new NPC("alien", 30, 20, 5);
        System.out.println("N1 ------> " + n1.name + " " + n1.defence + " " + n1.power + " " + n1.health);
        System.out.println("Cloning n1");

        NPC n2 = (NPC)n1.clone();
        n2.setname("Jayesh");
        n2.setpower(100);
        System.out.println("N2 ------> " + n2.name + " " + n2.defence + " " + n2.power + " " + n2.health);

        NPC n3 = (NPC)n2.clone();
        System.out.println("N3 ------> " + n3.name + " " + n3.defence + " " + n3.power + " " + n3.health);

    }
}