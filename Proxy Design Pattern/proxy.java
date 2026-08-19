//VIRTUAL PROXY

// interface Idisplay
// {
//     public void display();
// }

// class fileDisplay implements Idisplay
// {
//     String filePath;

//     public fileDisplay(String filePath)
//     {
//         this.filePath = filePath;
//         System.out.println("Loading the File from the disk");
//     }

//     public void display()
//     {
//         System.out.println("file is loaded ready to display \t" + this.filePath);
//     }
// }

// class fileProxy implements Idisplay
// {
//     String filePath;
//     fileDisplay fd;

//     public fileProxy(String filePath)
//     {
//         this.filePath = filePath;
//         this.fd = null;
//     }

//     public void display()
//     {
//         if(fd == null)
//         {
//             this.fd = new fileDisplay(filePath);
//         }
//         fd.display();
//     }
// }

// class proxy
// {
//     public static void main(String[] args) {
//         Idisplay dis = new fileProxy("C\\Images");
//         dis.display();
//     }
// }

//PROTECTION PROXY

interface Idisplay {
    public void display();
}

class fileDisplay implements Idisplay {
    String filePath;

    public fileDisplay(String filePath) {
        this.filePath = filePath;
        System.out.println("Loading the File from the disk");
    }

    public void display() {
        System.out.println("file is loaded ready to display \t" + this.filePath);
    }
}

class user {
    String username;
    boolean authorize;

    public user(String name, boolean access) {
        this.username = name;
        this.authorize = access;
    }

    public boolean isAuthenticate() {
        return this.authorize;
    }
}

class fileProxy implements Idisplay {
    String filePath;
    fileDisplay fd;
    user u;

    public fileProxy(String filePath, user user) {
        this.filePath = filePath;
        this.u = user;
        this.fd = null;
    }

    public void display() {
        if (u.isAuthenticate()) {
            if (fd == null) {
                this.fd = new fileDisplay(filePath);
            }
            fd.display();
        }
        else{
            System.out.println("User is not authorized to perform this action");
        }

    }
}

class proxy {
    public static void main(String[] args) {
        user au = new user("Jayesh", true);
        Idisplay dis = new fileProxy("C\\Images", au);
        dis.display();
    }
}