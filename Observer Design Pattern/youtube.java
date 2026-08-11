import java.util.ArrayList;
import java.util.List;

interface Isubscriber {
    public void update();
}

interface Ichannel {
    public void subscribe(Isubscriber s);

    public void unsubscribe(Isubscriber s);

    public void notifyVid();
}

class channel implements Ichannel {
    List<Isubscriber> list = new ArrayList();
    String name;
    String latestVid;

    public channel(String name) {
        this.name = name;
    }

    public void subscribe(Isubscriber s) {
        if (!list.contains(s)) {
            list.add(s);
        }
        System.out.println(s + "Subscriber added into channel");
    }

    public void unsubscribe(Isubscriber s) {
        if (list.contains(s)) {
            for (Isubscriber sub : list) {
                if (sub == s) {
                    list.remove(s);
                    break;
                }
            }
        }

        System.out.println(s + "Unsubscribe the channel");
    }

    public void notifyVid() {
        for (Isubscriber sub : list) {
            sub.update();
        }
    }

    public void uploadvid(String title) {
        this.latestVid = title;
        System.out.println("New Video Uploaded" + title);
        notifyVid();
    }

    public String getVid() {
        return this.latestVid;
    }
}

class subscriber implements Isubscriber {
    String name;
    channel channel;

    public subscriber(String name, channel channel) {
        this.name = name;
        this.channel = channel;
    }

    public void update() {
        System.out.println("Hi" + this.name + "new Video is Uploaded please check" + channel.getVid());
    }
}

class youtube {
    public static void main(String[] args) {
        channel ch = new channel("Apna College");

        subscriber sb = new subscriber("Jayesh", ch);
        subscriber sb2 = new subscriber("Sunil", ch);

        ch.subscribe(sb);
        ch.subscribe(sb2);

        ch.uploadvid("DSA Series");

        ch.unsubscribe(sb2);

        ch.uploadvid("Nodejs Tutorial");


    }

}