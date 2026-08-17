import java.util.ArrayList;
import java.util.List;

interface fileSystemItem
{
    public void ls();
    public void openAll();
    public int getSize();
    public String getName();
    public fileSystemItem cd(String target);
    public boolean isFolder();
}

class file implements fileSystemItem
{
    String name;
    int size;

    public file(String name, int size)
    {
        this.name = name;
        this.size = size;
    }

    public void ls()
    {
        System.out.println("---->  " + name);
    }

    public void openAll()
    {
        System.out.println("---->  " + name);
    }

    public int getSize()
    {
        return this.size;
    }

    public String getName()
    {
        return this.name;
    }

    public fileSystemItem cd(String target)
    {
        return null;
    }

    public boolean isFolder()
    {
        return false;
    }
}

class folder implements fileSystemItem
{
    String name;
    List<fileSystemItem> list = new ArrayList<>();

    public folder(String name)
    {
        this.name = name;
    }

    public void add(fileSystemItem child)
    {
        list.add(child);
    }

    public void ls()
    {
        for(fileSystemItem child : list)
        {
            System.out.println("----->   " + child.getName());
        }
    }

    public void openAll()
    {
        System.out.println("-----> " + this.name);
        for(fileSystemItem child : list)
        {
            child.openAll();
        }
    }

    public int getSize()
    {
        int total = 0;
        for(fileSystemItem child : list)
        {
            total = total + child.getSize();
        }

        return total;
    }

    public String getName()
    {
        return this.name;
    }

    public fileSystemItem cd(String target)
    {
        for(fileSystemItem child : list)
        {
            if(child.isFolder() && child.getName() == target)
            {
                return child;
            }
        }

        return null;
    }

    public boolean isFolder()
    {
        return true;
    }
}

class Composite{
    public static void main(String[] args) {
        folder root = new folder("Root");
        root.add(new file("file1.txt",1));
        root.add(new file("file2.txt", 1));

        folder dir = new folder("Dir");
        dir.add(new file("Resume.pdf", 1));
        dir.add(new file("image.jpg", 1));

        root.add(dir);

        folder apps = new folder("Apps");
        apps.add(new file("Vs Code", 2));
        apps.add(new file("SSMS", 2));

        root.add(apps);
        dir.add(apps);

        // root.ls();
        // dir.openAll();

        // System.out.println(root.getSize());
        fileSystemItem child = root.cd("Dir");
        if(child != null)
        {
            child.openAll();
        }
        else{
            System.out.println("Folder not found");
        }
    }
}