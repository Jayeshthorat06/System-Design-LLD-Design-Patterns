import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class colleague {
    IMediator mediator;

    public colleague(IMediator md) {
        this.mediator = md;
        this.mediator.register(this);
    }

    public abstract String getName();

    public abstract void sendAll(String message);

    public abstract void sendTo(String to, String message);

    public abstract void receive(String from, String message);
}

interface IMediator {
    public void sendAll(String from, String message);

    public void sendTo(String from, String to, String message);

    public void register(colleague c);
}

class ChatMediator implements IMediator {
    List<colleague> list = new ArrayList<>();
    Map<String, String> mute = new HashMap<>();

    public void sendAll(String from, String message) {
        System.out.println(from + " Broadcasting Message " + message);
        for (colleague col : list) {
            if (col.getName() == from) {
                continue;
            }

            boolean isMute = false;
            if (mute.containsKey(col.getName()) && mute.get(col.getName()) == from) {
                System.out.println("User is Muted not able to send message");
                isMute = true;
            }

            if (!isMute) {
                col.receive(from, message);
            }
        }
    }

    public void sendTo(String from, String to, String message) {
        System.out.println(from + " sending message " + message + " to " + to);

        for (colleague col : list) {
            if(col.getName() == to)
            {
                if (mute.containsKey(to) && mute.get(to) == from) {
                    System.out.println("User is Muted not able to send message");
                } else {
                    col.receive(from, message);
                }
            }
        }

    }

    public void register(colleague c) {
        list.add(c);
    }

    public void muteColleague(String who, String whom) {
        mute.put(who, whom);
    }
}

class User extends colleague
{
    String name;

    public User(String name, IMediator md)
    {
        super(md);
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void sendAll(String message)
    {
        mediator.sendAll(name, message);
    }

    public void sendTo(String to, String message)
    {
        mediator.sendTo(name, to, message);
    }

    public void receive(String from, String message)
    {
        System.out.println(this.name + "\t Received Message \t" + message + "from: \t" + from);
    }
}

class MediatorPattern
{
    public static void main(String[] args) {
        ChatMediator chat = new ChatMediator();

        User user1 = new User("Rohan", chat);
        User user2 = new User("Mohan", chat);
        User user3 = new User("Neha", chat);

        chat.muteColleague("Rohan", "Mohan");

        user1.sendAll("Hello EveryOne!!!");

        user1.sendTo("Neha", "Hi Neha");

        user2.sendAll("Hiiiii");
        user2.sendTo("Rohan", "Hi Rohan");
    }
}