package com.example.pokemons;

import com.example.pokemons.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface with defined functions,
 * used by retrofit to generate methods,
 * that fetch data from server
 *
 * @see retrofit2.Retrofit
 */
public interface InterfaceApi {

    @GET("type/{type}")
    Call<Pokemon> getPokemonTypeInformation(@Path("type") String pokemonType);
}
