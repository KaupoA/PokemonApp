package com.example.pokemon.pokemon.widget;

import com.example.pokemon.model.dto.PokemonDto;

public interface PokemonListActivityCallback {
    void navigateToPokemonDetails(PokemonDto pokemonDto);
}
