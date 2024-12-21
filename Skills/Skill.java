package Skills;

import java.util.ArrayList;
import java.util.List;
import Pokemon.Pokemon;

public class Skill {
    private String name;      // 技能名稱
    private String type;      // 技能類型 (Sky, Land, Ocean, Normal)
    private double factor;    // 倍率
    private String effect;    // 效果描述
    private Runnable normalAttack; 

    public Skill(String name, String type, double factor, String effect) {
        this.name = name;
        this.type = type;
        this.factor = factor;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getFactor() {
        return factor;
    }

    public String getEffect() {
        return effect;
    }

    // 計算屬性克制倍率
    public static double calculateTypeEffectiveness(String attackerType, String defenderType) {
        if ((attackerType.equals("Sky") && defenderType.equals("Land")) ||
            (attackerType.equals("Land") && defenderType.equals("Ocean")) ||
            (attackerType.equals("Ocean") && defenderType.equals("Sky"))) {
            return 1.5; // 攻擊方克制防禦方
        } else if ((attackerType.equals("Land") && defenderType.equals("Sky")) ||
                   (attackerType.equals("Ocean") && defenderType.equals("Land")) ||
                   (attackerType.equals("Sky") && defenderType.equals("Ocean"))) {
            return 0.75; // 攻擊方被防禦方克制
        } else {
            return 1.0; // 無克制
        }
    }

    // 計算傷害 (based on ATK, DEF, factor, 屬性克制等)
    public static int calculateDamage(Pokemon attacker, Pokemon defender, double factor) {
        String attackerType = attacker.getType();
        String defenderType = defender.getType(); 
        int atk = attacker.getAtk(); 
        int def = defender.getDef();
        double typeEffectiveness = calculateTypeEffectiveness(attackerType, defenderType);
        return (int) Math.max(1, (atk / (double) def) * factor * typeEffectiveness * 20);
    }

    @Override
    public String toString() {
        return "技能名稱: " + name + "\n" +
               "類型: " + type + "\n" +
               "倍率: " + factor + "\n" +
               "效果: " + effect + "\n";
    }

    public static List<Skill> getSkillsForPokemon(Pokemon pokemon) {
        List<Skill> skills = new ArrayList<>();
        String pokemonName = pokemon.getName();
        switch (pokemonName) {
            case "No.001":
                skills.add(new Skill("Sky attack", "Sky", 1.0, "Standard Sky-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Normal", 0.5, "HP recovering from attack"));
                break;
            case "No.002":
                skills.add(new Skill("Sky attack", "Sky", 1.0, "Standard Sky-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Sky", 1.5, "Taking 50% of the damage dealt"));
                break;
            case "No.003":
                skills.add(new Skill("Land attack", "Land", 1.0, "Standard Land-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Land", 0.75, "50% chance to attack twice"));
                break;
            case "No.004":
                skills.add(new Skill("Land attack", "Land", 1.0, "Standard Land-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Normal", 0.0, "Dealing 1 / 10 / 30 damage regardless of enemy's DEF"));
                break;
            case "No.005":
                skills.add(new Skill("Ocean attack", "Ocean", 1.0, "Standard Ocean-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Normal", 0.0, "Cutting enemy's remaining HP to 3/4 regardless of DEF"));
                break;
            case "No.006":
                skills.add(new Skill("Ocean attack", "Ocean", 1.0, "Standard Ocean-type attack"));
                skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack"));
                skills.add(new Skill("Special attack", "Ocean", 1.0, "Increasing ATK by 5, decreasing DEF by 5"));
                break;
            default:
                System.out.println("No skills found for the specified Pokemon.");
            }
        return skills;
    }

    public static Runnable normalAttack(Pokemon attacker, Pokemon defender) {
        return () -> {
            int damage = calculateDamage(attacker, defender, 1.0);
            defender.takeDamage(damage);
        };
    }

    // public static List<Skill> getSkillsForPokemon(String pokemonName, int hp, int atk, int def, int spd) {
    //     List<Skill> skills = new ArrayList<>();
    //     switch (pokemonName) {
    //         case "No.001":
    //             skills.add(new Skill("Sky attack", "Sky", 1.0, "Standard Sky-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Normal", 0.5, "HP recovering from attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.5), calculatePP(hp, atk, def, spd, 0.5)));
    //             break;
    //         case "No.002":
    //             skills.add(new Skill("Sky attack", "Sky", 1.0, "Standard Sky-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Sky", 1.5, "Taking 50% of the damage dealt",
    //                 calculateCoolDown(hp, atk, def, spd, 1.5), calculatePP(hp, atk, def, spd, 1.5)));
    //             break;
    //         case "No.003":
    //             skills.add(new Skill("Land attack", "Land", 1.0, "Standard Land-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Land", 0.75, "50% chance to attack twice",
    //                 calculateCoolDown(hp, atk, def, spd, 0.75), calculatePP(hp, atk, def, spd, 0.75)));
    //             break;
    //         case "No.004":
    //             skills.add(new Skill("Land attack", "Land", 1.0, "Standard Land-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Normal", 0.0, "Dealing 1 / 10 / 30 damage regardless of enemy's DEF",
    //                 calculateCoolDown(hp, atk, def, spd, 0.0), calculatePP(hp, atk, def, spd, 0.0)));
    //             break;
    //         case "No.005":
    //             skills.add(new Skill("Ocean attack", "Ocean", 1.0, "Standard Ocean-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Normal", 0.0, "Cutting enemy's remaining HP to 3/4 regardless of DEF",
    //                 calculateCoolDown(hp, atk, def, spd, 0.0), calculatePP(hp, atk, def, spd, 0.0)));
    //             break;
    //         case "No.006":
    //             skills.add(new Skill("Ocean attack", "Ocean", 1.0, "Standard Ocean-type attack",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             skills.add(new Skill("Normal attack", "Normal", 0.8, "Lower damage normal attack",
    //                 calculateCoolDown(hp, atk, def, spd, 0.8), calculatePP(hp, atk, def, spd, 0.8)));
    //             skills.add(new Skill("Special attack", "Ocean", 1.0, "Increasing ATK by 5, decreasing DEF by 5",
    //                 calculateCoolDown(hp, atk, def, spd, 1.0), calculatePP(hp, atk, def, spd, 1.0)));
    //             break;
    //         default:
    //             System.out.println("No skills found for the specified Pokemon.");
    //     }
    //     return skills;
    // }


}