package com.example.pokemons.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageRelationsTest {

    @Test
    public void testDamageRelationsNoParameterConstructor() {
        new DamageRelations();
    }

    @Test
    public void testDamageRelationsFourParameterConstructor() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = new ArrayList<>();
        new DamageRelations(doubleDamage, halfDamage, noDamage);
    }

    @Test
    public void testDamageRelationsTwoMultiplier() {
        List<Pokemon> doubleDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = new ArrayList<>();
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        assertEquals(2f, damageRelations.getMultiplierAgainst("water"));
        assertEquals(2f, damageRelations.getMultiplierAgainst("fire"));
    }

    @Test
    public void testDamageRelationsHalfMultiplier() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        List<Pokemon> noDamage = new ArrayList<>();
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        assertEquals(0.5f, damageRelations.getMultiplierAgainst("water"));
        assertEquals(0.5f, damageRelations.getMultiplierAgainst("fire"));
    }

    @Test
    public void testDamageRelationsNoMultiplier() {
        List<Pokemon> doubleDamage = new ArrayList<>();
        List<Pokemon> halfDamage = new ArrayList<>();
        List<Pokemon> noDamage = Arrays.asList(new Pokemon("water"), new Pokemon("fire"));
        DamageRelations damageRelations = new DamageRelations(doubleDamage, halfDamage, noDamage);

        assertEquals(0f, damageRelations.getMultiplierAgainst("water"));
        assertEquals(0f, damageRelations.getMultiplierAgainst("fire"));
    }

    @Test
    public void testDamageRelationsOneMultiplier() {
        DamageRelations damageRelations = new DamageRelations();

        assertEquals(1f, damageRelations.getMultiplierAgainst("water"));
        assertEquals(1f, damageRelations.getMultiplierAgainst("fire"));
    }

}
