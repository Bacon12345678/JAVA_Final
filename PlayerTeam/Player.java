package PlayerTeam;
import java.util.*;
import Pokemon.Pokemon;

// Player Class
public class Player {
    private String name;
    private List<Pokemon> pokemonList;
    private Pokemon currentPokemon;

    // Constructor
    public Player(String name, List<Pokemon> pokemonList) {
        this.name = name;
        this.pokemonList = pokemonList;
        this.currentPokemon = pokemonList.get(0); // Default to the first Pokemon
    }

    // Additional Constructor for dynamic initialization
    public Player(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
        this.currentPokemon = null; // No Pokemon initially chosen
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public Pokemon getCurrentPokemon() {
        return currentPokemon;
    }

    // Add a Pokemon to the player's team
    public void addPokemon(Pokemon pokemon) {
        pokemonList.add(pokemon);
        if (currentPokemon == null) {
            currentPokemon = pokemon; // Automatically set the first added Pokemon as current
        }
    }

    public void setCurrentPokemon(int index) {
        currentPokemon = getPokemon(index);
    }

    // Choose a Pokemon to switch to
    public void choosePokemon(int index) {
        if (index >= 0 && index < pokemonList.size()) {
            Pokemon chosenPokemon = pokemonList.get(index);
            if (chosenPokemon.isFainted()) {
                System.out.println("Cannot choose " + chosenPokemon.getName() + " because it has fainted.");
            } else {
                currentPokemon = chosenPokemon;
                System.out.println(name + " chose " + currentPokemon.getName() + "!");
            }
        } else {
            System.out.println("Invalid Pokemon selection!");
        }
    }

    // Get a specific Pokemon by index
    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < pokemonList.size()) {
            return pokemonList.get(index);
        } else {
            System.out.println("Invalid index!");
            return null;
        }
    }

    // Check if all Pokemon have fainted
    public boolean isAllPokemonFainted() {
        for (Pokemon p : pokemonList) {
            if (!p.isFainted()) {
                return false;
            }
        }
        return true;
    }

    // Check if player has any usable Pokemon left
    public boolean hasPokemonLeft() {
        return !isAllPokemonFainted();
    }
}

