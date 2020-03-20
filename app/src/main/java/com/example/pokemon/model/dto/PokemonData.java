package com.example.pokemon.model.dto;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PokemonData {

    @Embedded
    public PokemonDto pokemonDto;

    @Relation(parentColumn = "id", entityColumn = "pokemonId", entity = PrevEvolutionDto.class)
    public List<PrevEvolutionDto> prevEvolutionDtos;

    @Relation(parentColumn = "id", entityColumn = "pokemonId", entity = NextEvolutionDto.class)
    public List<NextEvolutionDto> nextEvolutionDtos;
}
