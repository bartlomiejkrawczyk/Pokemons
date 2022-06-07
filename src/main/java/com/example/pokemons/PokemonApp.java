package com.example.pokemons;

import com.example.pokemons.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Base class for pokemon application
 *
 * PokemonApp:
 * setups network connection,
 * initializes local database of pokemons
 * starts listening input for pokemons
 * stores information about given type
 * returns damage multiplier of given pokemons
 */
public class PokemonApp {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static InterfaceApi interfaceApi;

    private static void createNetworkSys() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .serializeNulls()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();

        interfaceApi = retrofit.create(InterfaceApi.class);
    }

    public static float calculateMultiplier(String attacker, String defender) {
        PokemonSimpleDatabase db = PokemonSimpleDatabase.getDatabase();
        Pokemon pokemon = db.getPokemonInformation(attacker);

        return pokemon.getDamageMultiplierAgainst(defender);
    }

    public static void main(String[] args) {
        createNetworkSys();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.trim().length() == 0) return;

            String[] pokemons = input.split(" ");

            if (pokemons.length > 3) {
                String attacker = pokemons[0];
                String defender = pokemons[2].trim();

                System.out.println(calculateMultiplier(attacker, defender) + "x");
            }
        }

    }
}
