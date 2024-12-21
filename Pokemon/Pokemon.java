package Pokemon;
import java.util.ArrayList;
import java.util.List;
import Skills.*;

/**
 * Represents a Pokemon with its basic attributes and battle capabilities.
 */
public class Pokemon {
    // Basic attributes as specified
    private String name;
    private String type;
    private int maxHealth;
    private int currentHealth;
    private List<Skill> skills;
    private String imagePath;

    // Battle stats
    private int atk;
    private int def;
    private int spd;

    /**
     * Constructor for creating a new Pokemon
     */
    public Pokemon(String number, String type, int maxHealth, int atk, int def, int spd) {
        this.name = "No." + number;
        this.type = type.toUpperCase();  // Store type in uppercase for consistency
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.skills = new ArrayList<>();
        this.imagePath = "pics/PokePic/" + number + ".png";
        //this.status = "Normal";  // Will be implemented later if needed
    }

    /**
     * Uses a skill to attack a target Pokemon
     */
    public void attack(Pokemon target, Skill skill) {
        if (skill != null && skills.contains(skill)) {
            int damage = calculateDamage(target, skill);
            target.takeDamage(damage);
        }
    }


    /**
     * Calculates damage based on the formula:
     * DMG = (ATK / DEF) * (Move Factor) * (Type Multiplier) * 20
     */
    private int calculateDamage(Pokemon target, Skill skill) {
        double typeMultiplier = getTypeMultiplier(this.type, target.getType());
        return (int) ((this.atk / (double) target.getDef()) * skill.getFactor() * typeMultiplier * 20);
    }

    /**
     * Gets the type multiplier between attacker and defender
     */
    private double getTypeMultiplier(String attackerType, String defenderType) {
        if (attackerType.equals("SKY") && defenderType.equals("LAND") ||
                attackerType.equals("LAND") && defenderType.equals("OCEAN") ||
                attackerType.equals("OCEAN") && defenderType.equals("SKY")) {
            return 1.5;
        } else if (attackerType.equals("LAND") && defenderType.equals("SKY") ||
                attackerType.equals("OCEAN") && defenderType.equals("LAND") ||
                attackerType.equals("SKY") && defenderType.equals("OCEAN")) {
            return 0.75;
        }
        return 1.0;
    }

    /**
     * Reduces current health by damage amount, preventing negative values
     */
    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
    }

    /**
     * Checks if the Pokemon has fainted (HP = 0)
     */
    public boolean isFainted() {
        return currentHealth <= 0;
    }

    /**
     * Heals the Pokemon by the specified amount
     */
    public void heal(int amount) {
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    /* Status effect method will be implemented later if needed
     * 狀態（如正常、中毒、麻痺等，有這個設定的話再加）
     */
    //public void applyStatusEffect(String status) {
    //    this.status = status;
    //}

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentHealth() { return currentHealth; }
    public List<Skill> getSkills() { return new ArrayList<>(skills); }
    //public String getStatus() { return status; }  // Will be implemented later if needed
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpd() { return spd; }

    public Skill getSkill(int index) {
        return skills.get(index); 
    }


    /**
     * Adds a skill to the Pokemon's skill list
     */
    public void addSkills(List<Skill> skills) {
        this.skills.addAll(skills);
    }

    // Static collection of all Pokemon data
    private static final List<Pokemon> ALL_POKEMON = new ArrayList<>();
    
    static {
        Pokemon P001 = new Pokemon("001", "SKY", 100, 50, 30, 80);
        P001.addSkills(Skill.getSkillsForPokemon(P001));
        ALL_POKEMON.add(P001);
    
        Pokemon P002 = new Pokemon("002", "SKY", 120, 40, 40, 60);
        P002.addSkills(Skill.getSkillsForPokemon(P002));
        ALL_POKEMON.add(P002);
    
        Pokemon P003 = new Pokemon("003", "LAND", 150, 40, 40, 50);
        P003.addSkills(Skill.getSkillsForPokemon(P003));
        ALL_POKEMON.add(P003);
    
        Pokemon P004 = new Pokemon("004", "LAND", 200, 20, 60, 30);
        P004.addSkills(Skill.getSkillsForPokemon(P004));
        ALL_POKEMON.add(P004);
    
        Pokemon P005 = new Pokemon("005", "OCEAN", 70, 60, 20, 70);
        P005.addSkills(Skill.getSkillsForPokemon(P005));
        ALL_POKEMON.add(P005);
    
        Pokemon P006 = new Pokemon("006", "OCEAN", 220, 30, 50, 40);
        P006.addSkills(Skill.getSkillsForPokemon(P006));
        ALL_POKEMON.add(P006);
    }

    /**
     * Returns a copy of all available Pokemon
     */
    public static List<Pokemon> getAllPokemon() {
        return new ArrayList<>(ALL_POKEMON);
    }

    public static Pokemon getPokemonByName(String name) {
        for (Pokemon pokemon : ALL_POKEMON) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return null; // Return null if no matching Pokemon is found
    }

    public String getPokePic() {
        return imagePath;
    }
}