package com.example.pokemons.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Pokemon {
    private final String name;
    @SerializedName("damage_relations")
    private final DamageRelations damageRelations;

    public Pokemon(String name) {
        this.name = name;
        this.damageRelations = new DamageRelations();
    }

    public Pokemon(String name, DamageRelations damageRelations) {
        this.name = name;
        this.damageRelations = damageRelations;
    }

    public String getName() {
        return name;
    }

    public float getDamageMultiplierAgainst(String type) {
        return damageRelations.getMultiplierAgainst(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
