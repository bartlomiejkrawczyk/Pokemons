package com.example.pokemons.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
}
