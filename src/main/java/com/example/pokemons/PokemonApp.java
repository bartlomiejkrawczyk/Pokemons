package com.example.pokemons;

import com.example.pokemons.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Base class for pokemon application
 * <p>
 * PokemonApp:
 * setups network connection,
 * starts listening input for pokemons,
 * stores information about given type if not present,
 * returns damage multiplier for given pokemons
 */
public class PokemonApp {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static InterfaceApi interfaceApi;

    public static void createNetworkSys() {
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

    private static String format(float floatingPointValue) {
        if (floatingPointValue == (int) floatingPointValue)
            return String.format("%d", (int) floatingPointValue);
        else
            return String.format("%s", floatingPointValue);
    }

    public static void handleInput(InputStream inputStream, OutputStream outputStream) {
        Scanner scanner = new Scanner(inputStream);

        PrintWriter printWriter = new PrintWriter(outputStream);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.trim().length() == 0) return;

            String[] pokemons = input.split("->", 2);

            if (pokemons.length == 2) {
                String attacker = pokemons[0].trim();
                String defender = pokemons[1].trim();

                float multiplier = 1f;
                for (String type : defender.split(" ")) {
                    multiplier *= calculateMultiplier(attacker, type);
                }

                printWriter.println(format(multiplier) + "x");
                printWriter.flush();
            }
        }

        printWriter.close();
    }

    public static void main(String[] args) {
        createNetworkSys();

        handleInput(System.in, System.out);
    }
}
