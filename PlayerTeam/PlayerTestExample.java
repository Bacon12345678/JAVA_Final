package PlayerTeam;
import java.util.ArrayList;
import java.util.List;

import Pokemon.PokemonTestExample;

public class PlayerTestExample {

    private String name;
    private List<PokemonTestExample> pokemonList;

    public PlayerTestExample(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
    }

    public void addPokemon(PokemonTestExample pokemon) {
        pokemonList.add(pokemon);
    }

    public PokemonTestExample getPokemon(int index) {
        return pokemonList.get(index);
    }

    public boolean hasPokemonLeft() {
        for (PokemonTestExample pokemon : pokemonList) {
            if (!pokemon.isFainted()) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
