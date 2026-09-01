interface FileSystemVisitor
{
    public void fileVisit(TextFile text);
    public void fileVisit(ImageFile img);
    public void fileVisit(VideoFile vid);
}

interface FileSystem
{
    public void accept(FileSystemVisitor fileVisitor);
}

class TextFile implements FileSystem
{
    String filename;

    public TextFile(String name)
    {
      this.filename = name;
    }

    public String getfilename()
    {
        return this.filename;
    }

    public void accept(FileSystemVisitor fileVisitor)
    {
        fileVisitor.fileVisit(this);
    }
}

class ImageFile implements FileSystem
{
    String filename;

    public ImageFile(String name)
    {
      this.filename = name;
    }

    public String getfilename()
    {
        return this.filename;
    }

    public void accept(FileSystemVisitor fileVisitor)
    {
        fileVisitor.fileVisit(this);
    }
}

class VideoFile implements FileSystem
{
    String filename;

    public VideoFile(String name)
    {
      this.filename = name;
    }

    public String getfilename()
    {
        return this.filename;
    }

    public void accept(FileSystemVisitor fileVisitor)
    {
        fileVisitor.fileVisit(this);
    }
}

class SizeCalcVisitor implements FileSystemVisitor
{
    public void fileVisit(TextFile text)
    {
        System.out.println("Calculating the Size of TextFile = \t" + text.getfilename());
    }

    public void fileVisit(ImageFile img)
    {
        System.out.println("Calculating the Size of ImageFile = \t" + img.getfilename());
    }

    public void fileVisit(VideoFile vid)
    {
        System.out.println("Calculating the Size of VideoFile = \t" + vid.getfilename());
    }
}

class SizeCompressVisitor implements FileSystemVisitor
{
    public void fileVisit(TextFile text)
    {
        System.out.println("Compressing the Size of TextFile = \t" + text.getfilename());
    }

    public void fileVisit(ImageFile img)
    {
        System.out.println("Compressing the Size of ImageFile = \t" + img.getfilename());
    }
    
    public void fileVisit(VideoFile vid)
    {
        System.out.println("Compressing the Size of VideoFile = \t" + vid.getfilename());
    }
}

class ScanVirusVisitor implements FileSystemVisitor
{
    public void fileVisit(TextFile text)
    {
        System.out.println("Scanning the Virus of TextFile = \t" + text.getfilename());
    }

    public void fileVisit(ImageFile img)
    {
        System.out.println("Scanning the Virus of ImageFile = \t" + img.getfilename());
    }
    
    public void fileVisit(VideoFile vid)
    {
        System.out.println("Scanning the Virus of VideoFile = \t" + vid.getfilename());
    }
}

class Visitor
{
    public static void main(String[] args) {
        FileSystem file1 = new TextFile("Jayesh.png");
        file1.accept(new SizeCalcVisitor());
        file1.accept(new SizeCompressVisitor());
        file1.accept(new ScanVirusVisitor());

        FileSystem file2 = new VideoFile("ApnaCollegeDSA.mp4");
        file2.accept(new SizeCalcVisitor());

    }
}