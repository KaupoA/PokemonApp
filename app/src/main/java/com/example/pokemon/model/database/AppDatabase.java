package com.example.pokemon.model.database;

import com.example.pokemon.model.dto.NextEvolutionDto;
import com.example.pokemon.model.dto.PokemonDto;
import com.example.pokemon.model.dto.PrevEvolutionDto;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonDto.class, PrevEvolutionDto.class, NextEvolutionDto.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();
}
