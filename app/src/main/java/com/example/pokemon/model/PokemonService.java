package com.example.pokemon.model;

import com.example.pokemon.model.dto.PokemonListDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonService {

    @GET("pokedex.json")
    Call<PokemonListDto> getPokemons();
}
