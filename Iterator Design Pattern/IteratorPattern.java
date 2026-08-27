import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

interface Iterator<T>
{
    public boolean hasNext();
    public T next();
}

interface Iterable<T>
{
    public Iterator<T> getIterator();
}

class LinkedList implements Iterable<Integer>
{
    int data;
    LinkedList next;

    public LinkedList(int data, LinkedList next)
    {
        this.data = data;
        this.next = next;
    }

    public Iterator<Integer> getIterator()
    {
        return new LinkedListIterator(this);
    }

}

class BinaryTree implements Iterable<Integer>
{
    int data;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int data, BinaryTree left, BinaryTree right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Iterator<Integer> getIterator()
    {
        return new BinaryTreeIterator(this);
    }
}

class Song
{
    String Title;
    String Artist;

    public Song(String Title, String Artist)
    {
        this.Title = Title;
        this.Artist = Artist;
    }
}

class PlayList implements Iterable<Song>
{
    List<Song> list;

    public PlayList()
    {
        this.list = new ArrayList<>();
    }

    public void addPlayList(Song song)
    {
        list.add(song);
    }

    public Iterator<Song> getIterator()
    {
        return new PlayListIterator(list);
    }

}

class LinkedListIterator implements Iterator<Integer>
{
    LinkedList curr;

    public LinkedListIterator(LinkedList head)
    {
        this.curr = head;
    }

    public boolean hasNext()
    {
        if(curr != null)
        {
            return true;
        }
        return false;
    }

    public Integer next()
    {
        int data = curr.data;
        curr = curr.next;
        return data;
    }
}

class BinaryTreeIterator implements Iterator<Integer>
{
    BinaryTree curr;
    Stack<BinaryTree> stack = new Stack<>();

    public void pushNode(BinaryTree Node)
    {
        while(Node != null)
        {
            stack.push(Node);
            Node = Node.left;
        }
    }

    public BinaryTreeIterator(BinaryTree root)
    {
        this.curr = root;
        pushNode(root);
    }

    public boolean hasNext()
    {
        return !stack.isEmpty();
    }

    public Integer next()
    {
        BinaryTree curr = stack.peek();
        stack.pop();

        int val = curr.data;

        if(curr.right != null)
        {
            pushNode(curr.right);
        }

        return val;
    }

}

class PlayListIterator implements Iterator<Song>
{
    List<Song> list;
    int index;

    public PlayListIterator(List<Song> list)
    {
        this.list = list;
        this.index = 0;
    }

    public boolean hasNext()
    {
       return list.size() > index;
    }

    public Song next()
    {
        return list.get(index++);
    }
}

class IteratorPattern
{
    public static void main(String[] args) {
        LinkedList list = new LinkedList(10, null);
        list.next = new LinkedList(20, null);
        list.next.next = new LinkedList(30, null);
        list.next.next.next = new LinkedList(40, null);
        list.next.next.next.next = new LinkedList(50, null);

        Iterator<Integer> it = list.getIterator();
        System.out.println("List-------------------------->");
        while(it.hasNext())
        {
            System.out.println("--------> \t " + it.next());
        }
        
        BinaryTree tree = new BinaryTree(10, null, null);
        tree.left = new BinaryTree(20, null, null);
        tree.left.right = new BinaryTree(30, null, null);
        tree.left.left = new BinaryTree(40, null, null);
        tree.left.left.right = new BinaryTree(50, null, null);
        tree.left.left.left = new BinaryTree(60, null, null);
        tree.left.left.left.left = new BinaryTree(70, null, null);

        Iterator<Integer> iterator = tree.getIterator();
        System.out.println("Tree-------------------------------->");
        while(iterator.hasNext())
        {
            System.out.println("-------> \t" + iterator.next());
        }

        Song song = new Song("Darshan Raval", "Barish");
        Song song2 = new Song("Badshah", "Inaam");
        Song song3 = new Song("Anuv Jain", "Arz kiya hei");

        PlayList p1 = new PlayList();
        p1.addPlayList(song);
        p1.addPlayList(song2);
        p1.addPlayList(song3);

        Iterator<Song> iterator3 = p1.getIterator();
        System.out.println("Play List Songs -------------------------------------->");
        while(iterator3.hasNext())
        {
            System.out.println("Songs Play List -------------> \t" + iterator3.next().Title);
        }
    }
}