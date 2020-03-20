package com.example.pokemon.model.database;

import com.example.pokemon.model.dto.PokemonDto;

import java.util.List;
import io.reactivex.Observable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;

@Dao
public interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<PokemonDto> pokemonDtos);

    @Query("SELECT * FROM pokemondto")
    Observable<List<PokemonDto>> get();
}
