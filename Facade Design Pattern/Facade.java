class powerSupply
{
    public void providePower()
    {
        System.out.println("Power supply Provided turn ON");
    }
}

class cpu
{
    public void initialize()
    {
        System.out.println("CPU initialized");
    }
}

class hardDrive
{
    public void spinUp()
    {
        System.out.println("Hard Drive is Spinned UP");
    }
}

class coolingSystem
{
    public void startFan()
    {
        System.out.println("Fan is started for Cooling System");
    }
}

class memory
{
    public void selfTest()
    {
        System.out.println("Memory Tested Successfully under Control");
    }
}

class BIOS
{
    public void boot(cpu cpu, memory mem)
    {
        cpu.initialize();
        mem.selfTest();

        System.out.println("System Booted Successfully");
    }
}

class operatingSystem
{
    public void load()
    {
        System.out.println("Operating System load Successfully");
    }
}

class computerFacade
{
    powerSupply ps;
    cpu cpu;
    hardDrive hd;
    coolingSystem cs;
    memory mem;
    BIOS bios;
    operatingSystem os;

    public computerFacade()
    {
        this.ps = new powerSupply();
        this.cpu = new cpu();
        this.hd = new hardDrive();
        this.cs = new coolingSystem();
        this.mem = new memory();
        this.bios = new BIOS();
        this.os = new operatingSystem();
    }

    public void startComputer()
    {
        ps.providePower();
        cpu.initialize();
        hd.spinUp();
        cs.startFan();
        mem.selfTest();
        bios.boot(cpu, mem);
        os.load();
    }
}

class client
{
    computerFacade comp;

    public client(computerFacade comp)
    {
        this.comp = comp;
    }

    public void startComp()
    {
        this.comp.startComputer();
    }
    
}

class Facade
{
    public static void main(String[] args) {
        client cl = new client(new computerFacade());
        cl.startComp();
    }
}