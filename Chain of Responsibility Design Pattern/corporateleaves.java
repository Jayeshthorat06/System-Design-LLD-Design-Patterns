abstract class leavesApproval
{
    leavesApproval nextApproval;

    public void setNextApproval(leavesApproval nextApproval)
    {
        this.nextApproval = nextApproval;
    }

    abstract public void approvalRequest(int days);
}

class TeamLead extends leavesApproval
{
    int limit;

    public TeamLead(int days)
    {
        this.limit = days;
    }

    public void approvalRequest(int days)
    {
        if(days > this.limit)
        {
            if(nextApproval != null)
            {
                nextApproval.approvalRequest(days);
            }
            else{
                System.out.println("Cannot able to provide you leave for days " + days);
            }
        }
        else{
            System.out.println("Leave Request Approved by TeamLead for days " + days);
        }
    }
}

class Manager extends leavesApproval
{
    int limit;

    public Manager(int days)
    {
        this.limit = days;
    }

    public void approvalRequest(int days)
    {
        if(days > this.limit)
        {
            if(nextApproval != null)
            {
                nextApproval.approvalRequest(days);
            }
            else{
                System.out.println("Cannot able to provide you leave for days " + days);
            }
        }
        else{
            System.out.println("Leave Request Approved by Manager for days " + days);
        }
    }
}

class Director extends leavesApproval
{
    int limit;

    public Director(int days)
    {
        this.limit = days;
    }

    public void approvalRequest(int days)
    {
        if(days > this.limit)
        {
            if(nextApproval != null)
            {
                nextApproval.approvalRequest(days);
            }
            else{
                System.out.println("Cannot able to provide you leave for days " + days);
            }
        }
        else{
            System.out.println("Leave Request Approved by Director for days " + days);
        }
    }
}


class corporateleaves {
    public static void main(String[] args) {
        leavesApproval TL = new TeamLead(3);
        leavesApproval manager = new Manager(6);
        leavesApproval director = new Director(10);

        TL.setNextApproval(manager);
        manager.setNextApproval(director);

        int reqLeave = -1;

        System.out.println("Leaver request for days " + reqLeave);
        TL.approvalRequest(reqLeave);
    }
}
