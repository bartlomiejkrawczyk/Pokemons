package com.example.pokemons;

import com.example.pokemons.model.Pokemon;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A pseudo database class
 * - class that stores values of current session in one place with convenient access
 */
public class PokemonSimpleDatabase {
    private static PokemonSimpleDatabase db;

    private final List<Pokemon> pokemons;

    /**
     * Make sure there are only one instance of database at the same time
     * Singleton pattern
     *
     * @return database instance
     */
    public static synchronized PokemonSimpleDatabase getDatabase() {
        if (db == null) {
            db = new PokemonSimpleDatabase();
        }
        return db;
    }

    private PokemonSimpleDatabase() {
        this.pokemons = new ArrayList<>();
    }

    private PokemonSimpleDatabase(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        db = this;
    }

    public Pokemon getPokemonInformation(String type) {
        return pokemons.stream()
                .filter(pokemon -> pokemon != null && Objects.equals(pokemon.getName(), type))
                .findFirst()
                .orElseGet(() -> downloadPokemonInformation(type));
    }

    private Pokemon downloadPokemonInformation(String type) {
        Call<Pokemon> call = PokemonApp.interfaceApi.getPokemonTypeInformation(type);
        try {
            Response<Pokemon> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                Pokemon pokemon = response.body();

                pokemons.add(pokemon);

                return pokemon;
            } else {
                if (!response.isSuccessful())
                    System.err.println("Response error: " + response.code());
                else
                    System.err.println("Response error: Empty body");
            }
        } catch (IOException e) {
            System.err.println("Failure error: Check your internet connection!");
        }

        // On error return default pokemon
        return new Pokemon("");
    }
}
