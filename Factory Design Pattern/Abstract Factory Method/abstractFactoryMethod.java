interface Burger {
    public void prepare();
}

interface GarlicBread {
    public void prepare();
}

interface MealFactory {
    public Burger createBurger(String type);

    public GarlicBread createGarlicBread(String type);
}

class basicBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Burger");
    }
}

class standardBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Burger");
    }
}

class premiumBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Preminum Burger");
    }
}

class basicWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger");
    }
}

class standardWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Burger");
    }
}

class premiumWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Preminum Wheat Burger");
    }
}

class basicGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic GarlicBread");
    }
}

class standardGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard GarlicBread");
    }
}

class premiumGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Preminum GarlicBread");
    }
}

class basicWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic Wheat GarlicBread");
    }
}

class standardWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard Wheat GarlicBread");
    }
}

class premiumWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Preminum Wheat GarlicBread");
    }
}

class singhBurger implements MealFactory {
    public Burger createBurger(String type) {
        if (type.equals("Basic")) {
            return new basicBurger();
        } else if (type.equals("standard")) {
            return new standardBurger();
        } else if (type.equals("premium")) {
            return new premiumBurger();
        }
        return new basicBurger();
    }

    public GarlicBread createGarlicBread(String type) {
        if (type.equals("Basic")) {
            return new basicGarlicBread();
        } else if (type.equals("standard")) {
            return new standardGarlicBread();
        } else if (type.equals("premium")) {
            return new premiumGarlicBread();
        }

        return new basicGarlicBread();
    }
}

class kingBurger implements MealFactory {
    public Burger createBurger(String type) {
        if (type.equals("BasicWheat")) {
            return new basicWheatBurger();
        } else if (type.equals("standardWheat")) {
            return new standardWheatBurger();
        } else if (type.equals("premiumWheat")) {
            return new premiumWheatBurger();
        }

        return new basicWheatBurger();
    }

    public GarlicBread createGarlicBread(String type) {
        if (type.equals("BasicWheat")) {
            return new basicWheatGarlicBread();
        } else if (type.equals("standardWheat")) {
            return new standardWheatGarlicBread();
        } else if (type.equals("premiumWheat")) {
            return new premiumWheatGarlicBread();
        }

        return new basicWheatGarlicBread();
    }
}

class abstractFactoryMethod {
    public static void main(String[] args) {

        MealFactory bfs = new singhBurger();
        MealFactory bfk = new kingBurger();

        Burger b = bfk.createBurger("premiumWheat");
        b.prepare();

        GarlicBread g = bfk.createGarlicBread("premiumWheat");
        g.prepare();

        Burger bs = bfs.createBurger("premium");
        bs.prepare();

        GarlicBread gs = bfs.createGarlicBread("premium");
        gs.prepare();
    }
}
