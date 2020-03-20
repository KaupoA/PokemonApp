package com.example.pokemon.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PrevEvolutionDto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private Integer pokemonId;
    private String num;
    private String name;

    public PrevEvolutionDto(Integer id, Integer pokemonId, String num, String name) {
        this.id = id;
        this.pokemonId = pokemonId;
        this.num = num;
        this.name = name;
    }

    protected PrevEvolutionDto(Parcel in) {
        num = in.readString();
        name = in.readString();
    }

    public static final Creator<PrevEvolutionDto> CREATOR = new Creator<PrevEvolutionDto>() {
        @Override
        public PrevEvolutionDto createFromParcel(Parcel in) {
            return new PrevEvolutionDto(in);
        }

        @Override
        public PrevEvolutionDto[] newArray(int size) {
            return new PrevEvolutionDto[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(num);
        dest.writeString(name);
    }
}
