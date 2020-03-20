package com.example.pokemon.utils;

import com.example.pokemon.model.dto.PokemonDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortUtils {

    public static List<PokemonDto> returnSortedSpawnChanceAscList(List<PokemonDto> input) {
        List<PokemonDto> pokemonDtos = new ArrayList<>(input);
        Collections.sort(pokemonDtos, new PokemonDto.());
    }
}
