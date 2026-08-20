import javafx.scene.layout.BackgroundImage;

class paymentRequest
{
    String sender;
    String receiver;
    double amount;
    String currency;

    public paymentRequest(String s, String r, double amt, String c)
    {
        this.sender = s;
        this.receiver = r;
        this.amount = amt;
        this.currency = c;
    }
}

interface bankingSystem
{
    public boolean processPayment(double amount);
}

class paytmBankingSystem implements bankingSystem
{
    public boolean processPayment(double amount)
    {
        int randomInt = (int) (Math.random() * 100) + 1;
        return randomInt < 80;
    }
}
class razorpayBankingSystem implements bankingSystem
{
    public boolean processPayment(double amount)
    {
        int randomInt = (int) (Math.random() * 100) + 1;
        return randomInt < 80;
    }
}

abstract class paymentGateway
{
    bankingSystem bankingsystem;

    abstract public boolean validatePayment(paymentRequest pr);
    abstract public boolean initiatePayment(paymentRequest pr);
    abstract public boolean confirmPayment(paymentRequest pr);

    public boolean processPayment(paymentRequest pr)
    {
        if(!validatePayment(pr))
        {
            System.out.println("[Payment Gateway] Validation failed for ---> " + pr.sender);
            return false;
        }

        if(!initiatePayment(pr))
        {
            System.out.println("[Payment Gateway] Payment Initialization failed ----> " + pr.sender);
            return false;
        }

        if(!confirmPayment(pr))
        {
            System.out.println("[Payment Gateway] Payment Confirmation failed for ---> " + pr.sender);
            return false;
        }
        return true;
    }
}

class PaytmGateway extends paymentGateway
{
    public PaytmGateway()
    {
        bankingsystem = new paytmBankingSystem();
    }

    public boolean validatePayment(paymentRequest pr)
    {
        System.out.println("[PaytmGateway] validating the payment request of ---> " + pr.sender + "for amount ----> "  + pr.amount);

        if(pr.amount <= 0 || !pr.currency.equals("INR"))
        {
            return false;
        }

        return true;
    }

    public boolean initiatePayment(paymentRequest pr)
    {
        System.out.println("[PaytmGateway] initializing the payment request of ---> " + pr.sender + "for amount ----> "  + pr.amount);

        return bankingsystem.processPayment(pr.amount);
    }

    public boolean confirmPayment(paymentRequest pr)
    {
        System.out.println("[PaytmGateway] Payment for user ----> " + pr.receiver + " is succeded");
        return true;
    }
}

class RazorPayGateway extends paymentGateway
{
    public RazorPayGateway()
    {
        bankingsystem = new razorpayBankingSystem();
    }

    public boolean validatePayment(paymentRequest pr)
    {
        System.out.println("[RazorPayGateway] validating the payment request of ---> " + pr.sender + "for amount ----> "  + pr.amount);

        if(pr.amount <= 0 || !pr.currency.equals("USD"))
        {
            return false;
        }

        return true;
    }

    public boolean initiatePayment(paymentRequest pr)
    {
        System.out.println("[RazorPayGateway] initializing the payment request of ---> " + pr.sender + "for amount ----> "  + pr.amount);

        return bankingsystem.processPayment(pr.amount);
    }

    public boolean confirmPayment(paymentRequest pr)
    {
        System.out.println("[RazorPayGateway] Payment for user ----> " + pr.receiver + " is succeded");
        return true;
    }
}

class paymentGatewayProxy extends paymentGateway
{
    paymentGateway pg;
    int maxretries;

    public paymentGatewayProxy(paymentGateway pg, int retry)
    {
        this.pg = pg;
        this.maxretries = retry;
    }

    public boolean processPayment(paymentRequest pr)
    {
        boolean status = false;
        for(int i = 0; i < maxretries; i++)
        {
            System.out.println("[paymentGatewayProxy] retrying payment for attempt ----> " + (i + 1));
            status = pg.processPayment(pr);

            if(status) break;

        }

        if(!status)
        {
            System.out.println("[paymentGatewayProxy] Payment failed after attempts " + maxretries);
            return status;
        }

        System.out.println("Payment Success");
        
        return true;

    }

    public boolean validatePayment(paymentRequest pr)
    {
        return pg.validatePayment(pr);
    }

    public boolean initiatePayment(paymentRequest pr)
    {
        return pg.initiatePayment(pr);
    }

    public boolean confirmPayment(paymentRequest pr)
    {
        return pg.confirmPayment(pr);
    }

}

class paymentService
{
    paymentGateway pg;

    public void setGateway(paymentGateway pg)
    {
        this.pg = pg;
    }

    public void processPayment(paymentRequest pr)
    {
        if(pg.processPayment(pr))
        {
            System.out.println("Payment executed Successfully for user ===> " + pr.sender);
        }
        else{
            System.out.println("Payment fialed for user ===> " + pr.sender);
        }
    }
}

class paymentGatewaySystem
{
    public static void main(String[] args) {
        paymentRequest pr = new paymentRequest("Jayesh", "Chetan", 20000, "INR");
        String gateway = "RAZORPAY";
        paymentService service = new paymentService();

        if(gateway.equals("PAYTM"))
        {
            paymentGateway pg = new PaytmGateway();
            service.setGateway(new paymentGatewayProxy(pg, 3));
        }
        else{
            paymentGateway pg = new RazorPayGateway();
            service.setGateway(new paymentGatewayProxy(pg, 1));
        }

        service.processPayment(pr);
    }
}