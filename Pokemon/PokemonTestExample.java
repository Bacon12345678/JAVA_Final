package Pokemon;

public class PokemonTestExample {
    private String name;
    private int health;

    public PokemonTestExample(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    //扣血，並防止變成負數
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }


    //是否倒下
    public boolean isFainted() {
        return health == 0;
    }
}

