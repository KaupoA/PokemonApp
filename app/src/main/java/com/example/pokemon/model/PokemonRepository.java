package com.example.pokemon.model;

import com.example.pokemon.model.database.PokemonDao;
import com.example.pokemon.model.dto.PokemonDto;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class PokemonRepository {

    private PokemonDao pokemonDao;
    private PokemonService pokemonService;

    public PokemonRepository(PokemonDao pokemonDao, PokemonService pokemonService) {
        this.pokemonDao = pokemonDao;
        this.pokemonService = pokemonService;
    }

    public Completable insertPokemons(List<PokemonDto> pokemonDtos) {
        return pokemonDao.insert(pokemonDtos);
    }

    public Observable<List<PokemonDto>> get() {
        return pokemonDao.get();
    }
}
