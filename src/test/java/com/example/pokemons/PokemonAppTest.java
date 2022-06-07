package com.example.pokemons;

import com.example.pokemons.model.DamageRelations;
import com.example.pokemons.model.Pokemon;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.pokemons.PokemonApp.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonAppTest {
    @Test
    public void testAdd() {
        assertEquals(5, 2 + 3);
    }

    @Test
    public void testCalculateMultiplier() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        initiateDatabaseWithPokemons();

        assertEquals(0.5f, calculateMultiplier("fire", "water"));
        assertEquals(2f, calculateMultiplier("water", "fire"));
    }

    @Test
    public void testHandleInput() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        initiateDatabaseWithPokemons();

        String input = "fire -> water\nwater -> fire";
        String output = "0.5x\r\n2.0x\r\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        handleInput(inputStream, outputStream);

        assertEquals(output, outputStream.toString());
    }

    @Test
    public void testHandleInputWithInternetConnection() {
        createNetworkSys();

        String input = "fire -> water\nwater -> fire";
        String output = "0.5x\r\n2.0x\r\n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        handleInput(inputStream, outputStream);

        assertEquals(output, outputStream.toString());
    }

    private void initiateDatabaseWithPokemons() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<PokemonSimpleDatabase> constructor = PokemonSimpleDatabase.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        List<Pokemon> doubleDamage = Collections.singletonList(new Pokemon("fire"));
        List<Pokemon> halfDamage = Collections.singletonList(new Pokemon("water"));
        List<Pokemon> noDamage = new ArrayList<>();

        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        List<Pokemon> pokemons = Arrays.asList(new Pokemon("fire", damageRelations), new Pokemon("water", damageRelations));

        constructor.newInstance(pokemons);
    }
}
