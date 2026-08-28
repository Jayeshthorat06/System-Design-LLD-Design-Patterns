import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AsteroidIntrinsic {
    int length;
    int width;
    int weight;
    String colour;
    String material;
    String texture;

    public AsteroidIntrinsic(int length, int width, int weight, String colour, String material, String texture) {
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.colour = colour;
        this.material = material;
        this.texture = texture;
    }

    public void render(int xpos, int ypos, int xvelociy, int yvelocity) {
        System.out.println("Asteroid Info:");
        System.out.println("Length of Asteroid\t" + this.length);
        System.out.println("Width of Asteroid\t" + this.width);
        System.out.println("Weight of Asteroid\t" + this.weight);
        System.out.println("Colour of Asteroid\t" + this.colour);
        System.out.println("Material of Asteroid\t" + this.material);
        System.out.println("Texture of Asteroid\t" + this.texture);
        System.out.println("X Co-Ordinates Position of Asteroid\t" + xpos);
        System.out.println("Y Co-Ordinates Position of Asteroid\t" + ypos);
        System.out.println("X Co-Ordinates Velocity of Asteroid\t" + xvelociy);
        System.out.println("Y Co-Ordinates Velocity of Asteroid\t" + yvelocity);
    }
}

class AsteroidExtrinsic {
    AsteroidIntrinsic AI;
    int xpos, ypos;
    int xvelocity, yvelocity;

    public AsteroidExtrinsic(AsteroidIntrinsic AI, int xpos, int ypos, int xvelocity, int yvelocity) {
        this.AI = AI;
        this.xpos = xpos;
        this.ypos = ypos;
        this.xvelocity = xvelocity;
        this.yvelocity = yvelocity;
    }

    public void render() {
        AI.render(xpos, ypos, xvelocity, yvelocity);
    }
}

class FlyWeightFactory {
    public static Map<String, AsteroidIntrinsic> map = new HashMap<>();

    public static AsteroidIntrinsic getAsteroidInterinsic(int length, int width, int weight, String colour,
            String material, String texture) {
        String key = String.valueOf(length) + "_" + String.valueOf(width) + "_" + String.valueOf(weight) + "_" + colour
                + "_" + material + "_" + texture;

        if (!map.containsKey(key)) {
            map.put(key, new AsteroidIntrinsic(length, width, weight, colour, material, texture));
        }

        return map.get(key);
    }
}

class SpaceGameWithFlyWeight {
    List<AsteroidExtrinsic> list = new ArrayList<>();

    public void spawnAsteroid(int count) {
        for (int i = 1; i <= count; i++) {
            String[] col = new String[] { "Red", "Green", "Blue" };
            String[] mat = new String[] { "Iron", "Stone", "Ice" };
            String[] tex = new String[] { "Rocky", "Metalic", "Icy" };
            int[] size = new int[] { 25, 35, 45 };

            int type = i % 3;

            AsteroidIntrinsic AI = FlyWeightFactory.getAsteroidInterinsic(
                    size[type], size[type], size[type] * 2, col[type], mat[type], tex[type]);

            list.add(new AsteroidExtrinsic(
                    AI, 100 + i * 50, 100 + i * 30, 100, 50));
        }
    }

    public void renderAll() {
        for (int i = 0; i < 5; i++) {
            list.get(i).render();
        }
    }
}

class FlyWeight
{
    public static void main(String[] args) {
        SpaceGameWithFlyWeight space = new SpaceGameWithFlyWeight();
        int AsteroidCount = 100000;
        space.spawnAsteroid(AsteroidCount);
        space.renderAll();
    }
}