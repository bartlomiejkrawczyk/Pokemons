package com.example.pokemons;

import com.example.pokemons.model.Pokemon;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonSimpleDatabaseTest {

    @Test
    public void testGetInstance() {
        PokemonSimpleDatabase.getDatabase();
    }

    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<PokemonSimpleDatabase> constructor = PokemonSimpleDatabase.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<PokemonSimpleDatabase> constructor = PokemonSimpleDatabase.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);

        List<Pokemon> pokemons = Arrays.asList(new Pokemon("fire"), new Pokemon("water"));
        PokemonSimpleDatabase db = constructor.newInstance(pokemons);

        Pokemon pokemon = db.getPokemonInformation("fire");

        assertEquals("fire", pokemon.getName());
    }
}
