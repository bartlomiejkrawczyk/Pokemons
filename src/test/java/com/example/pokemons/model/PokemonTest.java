package com.example.pokemons.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    @Test
    public void testPokemonConstructorTakingOneParameter() {
        Pokemon pokemon1 = new Pokemon("fire");
        assertEquals("fire", pokemon1.getName());

        Pokemon pokemon2 = new Pokemon("water");
        assertEquals("water", pokemon2.getName());
    }

    @Test
    public void testPokemonConstructorTakingTwoParameters() {
        DamageRelations damageRelations = new DamageRelations();
        Pokemon pokemon1 = new Pokemon("fire", damageRelations);
        assertEquals("fire", pokemon1.getName());

        Pokemon pokemon2 = new Pokemon("water", damageRelations);
        assertEquals("water", pokemon2.getName());
    }

    @Test
    public void testPokemonGetTwoDamageMultiplier() {
        List<Pokemon> doubleDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = new ArrayList<>();
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        Pokemon pokemon1 = new Pokemon("fire", damageRelations);
        assertEquals(2f, pokemon1.getDamageMultiplierAgainst("water"));

        Pokemon pokemon2 = new Pokemon("water", damageRelations);
        assertEquals(2f, pokemon2.getDamageMultiplierAgainst("water"));
    }

    @Test
    public void testPokemonGetHalfDamageMultiplier() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        List<Pokemon> noDamage = new ArrayList<>();
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        Pokemon pokemon1 = new Pokemon("fire", damageRelations);
        assertEquals(0.5f, pokemon1.getDamageMultiplierAgainst("water"));

        Pokemon pokemon2 = new Pokemon("water", damageRelations);
        assertEquals(0.5f, pokemon2.getDamageMultiplierAgainst("water"));
    }

    @Test
    public void testPokemonGetNoDamageMultiplier() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        Pokemon pokemon1 = new Pokemon("fire", damageRelations);
        assertEquals(0f, pokemon1.getDamageMultiplierAgainst("water"));

        Pokemon pokemon2 = new Pokemon("water", damageRelations);
        assertEquals(0f, pokemon2.getDamageMultiplierAgainst("water"));
    }

    @Test
    public void testPokemonGetOneDamageMultiplier() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = new ArrayList<>();
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        Pokemon pokemon1 = new Pokemon("fire", damageRelations);
        assertEquals(1f, pokemon1.getDamageMultiplierAgainst("water"));

        Pokemon pokemon2 = new Pokemon("water", damageRelations);
        assertEquals(1f, pokemon2.getDamageMultiplierAgainst("water"));
    }
}
