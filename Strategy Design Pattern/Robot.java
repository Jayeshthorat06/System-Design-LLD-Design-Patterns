interface Talkable
{
    public void talk();
}

interface walkable
{
    public void walk();
}

interface flyable
{
    public void fly();
}

interface projectable
{
    public void projection();
}

class normalTalk implements Talkable
{
    public void talk()
    {
        System.out.println("Robot is talking Normally");
    }
}

class noTalk implements Talkable
{
    public void talk()
    {
        System.out.println("Robot is not talking");
    }
}

class normalWalk implements walkable
{
    public void walk()
    {
        System.out.println("Robot is walking Normally");
    }
}

class noWalk implements walkable
{
    public void walk()
    {
        System.out.println("Robot is Not walking");
    }
}

class normalFly implements flyable
{
    public void fly()
    {
        System.out.println("Robot is flying Normally");
    }
}

class noFly implements flyable
{
    public void fly()
    {
        System.out.println("Robot is not flying");
    }
}

class correctProjection implements projectable
{
    public void projection()
    {
        System.out.println("The projection of Robot is Correct");
    }
}

class wrongProjection implements projectable
{
    public void projection()
    {
        System.out.println("The projection of Robot is Wrong");
    }
}

class Robot{
    Talkable t;
    walkable w;
    flyable f;
    projectable p;

    public Robot(Talkable t, walkable w, flyable f, projectable p)
    {
        this.t = t;
        this.w = w;
        this.f = f;
        this.p = p;
    }

    public void talk()
    {
        t.talk();
    }

    public void walk()
    {
        w.walk();
    }

    public void fly()
    {
        f.fly();
    }

    public void projection()
    {
        p.projection();
    }

    public static void main(String[] args)
    {
        Robot robot = new Robot(
            new normalTalk(),
            new normalWalk(),
            new normalFly(),
            new correctProjection()
        );

        robot.talk();
        robot.walk();
        robot.fly();
        robot.projection();

        Robot robot2 = new Robot(
            new noTalk(),
            new noWalk(),
            new noFly(),
            new wrongProjection()
        );

        robot2.talk();
        robot2.walk();
        robot2.fly();
        robot2.projection();

        robot.t.talk();
    }
}