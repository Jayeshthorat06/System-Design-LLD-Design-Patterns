// Lazy Loading

// class singleton
// {
//     private static singleton singleton = null;

//     private singleton()
//     {
//         System.out.println("Create object using getInstance Method");
//     }

//     public static singleton getInstance()
//     {
//         if(singleton == null)
//         {
//             singleton = new singleton();
//         }

//         return singleton;
//     }

//     public void message()
//     {
//         System.out.println("Hello from Singleton");
//     }
// }

//Eager Loading

// class singleton
// {
//     private static singleton singleton = new singleton();

//     private singleton()
//     {
//         System.out.println("Create object using getInstance Method");
//     }

//     public static singleton getInstance()
//     {
//         return singleton;
//     }

//     public void message()
//     {
//         System.out.println("Hello from Singleton");
//     }
// }

//Thread Safe SingleTon (Lazy Loading)

class singleton {
    private static singleton singleton = null;

    private singleton() {
        System.out.println("Create object using getInstance Method");
    }

    public static singleton getInstance()
    {
        if(singleton == null)
        {
            synchronized(singleton.class)
            {
                if(singleton == null)
                {
                    singleton = new singleton();
                }
            }
        }

        return singleton;
    }

    public void message() {
        System.out.println("Hello from Singleton");
    }
}

class singletonmain {
    public static void main(String[] args) {
        singleton s = singleton.getInstance();
        singleton st = singleton.getInstance();
        s.message();
        st.message();

        System.out.println(s == st);
    }
}