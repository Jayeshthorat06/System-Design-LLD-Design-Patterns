import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Decorator Design Pattern

interface Inotification
{
    public String getContent();
}

class simpleNotification implements Inotification
{
    String text;

    public simpleNotification(String text)
    {
        this.text = text;
    }

    public String getContent()
    {
        return text;
    }

}

abstract class Inotificationdecorator implements Inotification
{
    Inotification notify;

    public Inotificationdecorator(Inotification notify)
    {
        this.notify = notify;
    }
}

class timestampDecorator extends Inotificationdecorator
{
    public timestampDecorator(Inotification notify)
    {
        super(notify);
    }

    public String getContent()
    {
        LocalDateTime myObj = LocalDateTime.now();
        return myObj + "\t"  + notify.getContent();
    }
}

class signatureDecorator extends Inotificationdecorator
{
    public signatureDecorator(Inotification notify)
    {
        super(notify);
    }
    
    public String getContent()
    {
        return "Sign By Amazon \t" + notify.getContent();
    }
}

//Observer Design Pattern

interface Iobserver
{
    public void update();
}

interface Iobservable
{
    public void add(Iobserver os);
    public void remove(Iobserver os);
    public void sendNotification();
}

class notificationObservable implements Iobservable
{
    List<Iobserver> list = new ArrayList<>();
    Inotification currnotification;

    public notificationObservable()
    {
        this.currnotification = null;
    }

    public void add(Iobserver os)
    {
        if(!list.contains(os))
        {
            list.add(os);
        }
    }

    public void remove(Iobserver os)
    {
        if(list.contains(os))
        {
            for(Iobserver ob : list)
            {
                if(ob == os)
                {
                    list.remove(os);
                    break;
                }
            }
        }
    }

    public void sendNotification()
    {
        for(Iobserver ob : list)
        {
            ob.update();
        }
    }

    public void setNotification(Inotification content)
    {
        this.currnotification = content;
        sendNotification();
    }

    public String getNotification()
    {
        return this.currnotification.getContent();
    }

}


class Logger implements Iobserver
{
    notificationObservable no;

    public Logger(notificationObservable no)
    {
        this.no = no;
    }

    public void update()
    {
        System.out.println(":Logs added into Logger for \t" + no.getNotification());
    }
}

interface InotificationStrategy
{
    public void sendNotification(String content);
}

class notificationEngine implements Iobserver
{
    notificationObservable nob;
    List<InotificationStrategy> list = new ArrayList<>();

    public notificationEngine(notificationObservable no)
    {
        this.nob = no;
    }

    public void addNotificationStrategy(InotificationStrategy ns)
    {
        list.add(ns);
    }

    public void update()
    {
        for(InotificationStrategy ns : list)
        {
            ns.sendNotification(nob.getNotification());
        }
    }
}

class emailStrategy implements InotificationStrategy
{
    String email;

    public emailStrategy(String email)
    {
        this.email = email;
    }

    public void sendNotification(String content) 
    {
        System.out.println("Email Send Successfully to \t" + this.email + "for \t" + content);
    }
}

class smsStrategy implements InotificationStrategy
{
    String mobileno;

    public smsStrategy(String mobileno)
    {
        this.mobileno = mobileno;
    }

    public void sendNotification(String content)
    {
        System.out.println("SMS Send Successfully to \t" + this.mobileno + "for \t" + content);
    }
}

class popupStrategy implements InotificationStrategy
{
    public void sendNotification(String content)
    {
        System.out.println("POP UP Send Successfully for \t" + content);
    }
}

class notificationService
{
    List<Inotification> list = new ArrayList<>();
    notificationObservable no;
    public static notificationService notificationservice = null;

    private notificationService()
    {
        this.no = new notificationObservable();
    }

    public static notificationService getInstance()
    {
        if(notificationservice == null)
        {
            notificationservice = new notificationService();
        }

        return notificationservice;
    }

    public notificationObservable getnotificationObservable()
    {
        return no;
    }

    public void sendNotificationService(Inotification in)
    {
        list.add(in);
        no.setNotification(in);
    }
}
class Notification
{
    public static void main(String[] args) {

        notificationService ns = notificationService.getInstance();
        notificationObservable no = ns.getnotificationObservable();

        Logger logger = new Logger(no);
        notificationEngine notificationengine = new notificationEngine(no);

        notificationengine.addNotificationStrategy(new emailStrategy("thoratjayesh06@gmail.com"));
        notificationengine.addNotificationStrategy(new smsStrategy("9763247532"));
        notificationengine.addNotificationStrategy(new popupStrategy());

        no.add(logger);
        no.add(notificationengine);

        Inotification notify = new timestampDecorator(new signatureDecorator(new simpleNotification("Your Order Delivered Successfully")));
        // System.out.println(notify.getContent());

        ns.sendNotificationService(notify);
    }
}