package com.example.pokemon.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PokemonListDto implements Parcelable {

    private List<PokemonDto> pokemon;

    public PokemonListDto() {

    }

    protected PokemonListDto(Parcel in) {
        pokemon = in.createTypedArrayList(PokemonDto.CREATOR);
    }

    public static final Creator<PokemonListDto> CREATOR = new Creator<PokemonListDto>() {
        @Override
        public PokemonListDto createFromParcel(Parcel in) {
            return new PokemonListDto(in);
        }

        @Override
        public PokemonListDto[] newArray(int size) {
            return new PokemonListDto[size];
        }
    };

    public List<PokemonDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<PokemonDto> pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(pokemon);
    }
}
