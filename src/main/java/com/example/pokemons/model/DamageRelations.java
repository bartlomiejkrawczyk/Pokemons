package com.example.pokemons.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DamageRelations {
    @SerializedName("double_damage_to")
    private final List<Pokemon> doubleDamageTo;
    @SerializedName("half_damage_to")
    private final List<Pokemon> halfDamageTo;
    @SerializedName("no_damage_to")
    private final List<Pokemon> noDamageTo;

    public DamageRelations() {
        this.doubleDamageTo = new ArrayList<>();
        this.halfDamageTo = new ArrayList<>();
        this.noDamageTo = new ArrayList<>();
    }

    public DamageRelations(List<Pokemon> doubleDamageTo, List<Pokemon> halfDamageTo, List<Pokemon> noDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
        this.halfDamageTo = halfDamageTo;
        this.noDamageTo = noDamageTo;
    }

    public float getMultiplierAgainst(String type) {
        if (doubleDamageTo.stream().anyMatch(pokemon -> pokemon != null && pokemon.getName().equals(type))) {
            return 2;
        } else if (halfDamageTo.stream().anyMatch(pokemon -> pokemon != null && Objects.equals(pokemon.getName(), type))) {
            return 0.5f;
        } else if (noDamageTo.stream().anyMatch(pokemon -> pokemon != null && Objects.equals(pokemon.getName(), type))) {
            return 0;
        }
        return 1;
    }
}
