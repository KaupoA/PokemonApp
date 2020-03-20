package com.example.pokemon.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PokemonDto implements Parcelable {

    @PrimaryKey
    private Integer id;
    private String num;
    private String name;
    private String img;
    private String height;
    private String weight;
    private String candy;
    private Integer candy_count;
    private String egg;
    private Double spawn_chance;
    private Double avg_spawns;
    private String spawn_time;

    @Ignore
    private List<String> type;

    @Ignore
    private List<Double> multipliers;

    @Ignore
    private List<String> weaknesses;

    @Ignore
    private List<PrevEvolutionDto> prev_evolution;

    @Ignore
    private List<NextEvolutionDto> next_evolution;

    public PokemonDto(Integer id, String num, String name, String img, String height, String weight,
                      String candy, Integer candy_count, String egg, Double spawn_chance,
                      Double avg_spawns, String spawn_time, List<String> type,
                      List<Double> multipliers, List<String> weaknesses,
                      List<PrevEvolutionDto> prev_evolution, List<NextEvolutionDto> next_evolution) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.height = height;
        this.weight = weight;
        this.candy = candy;
        this.candy_count = candy_count;
        this.egg = egg;
        this.spawn_chance = spawn_chance;
        this.avg_spawns = avg_spawns;
        this.spawn_time = spawn_time;
        this.type = type;
        this.multipliers = multipliers;
        this.weaknesses = weaknesses;
        this.prev_evolution = prev_evolution;
        this.next_evolution = next_evolution;
    }

    public PokemonDto(Integer id, String num, String name, String img, String height, String weight,
                      String candy, Integer candy_count, String egg, Double spawn_chance,
                      Double avg_spawns, String spawn_time) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.height = height;
        this.weight = weight;
        this.candy = candy;
        this.candy_count = candy_count;
        this.egg = egg;
        this.spawn_chance = spawn_chance;
        this.avg_spawns = avg_spawns;
        this.spawn_time = spawn_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public Integer getCandy_count() {
        return candy_count;
    }

    public void setCandy_count(Integer candy_count) {
        this.candy_count = candy_count;
    }

    public String getEgg() {
        return egg;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public Double getSpawn_chance() {
        return spawn_chance;
    }

    public void setSpawn_chance(Double spawn_chance) {
        this.spawn_chance = spawn_chance;
    }

    public Double getAvg_spawns() {
        return avg_spawns;
    }

    public void setAvg_spawns(Double avg_spawns) {
        this.avg_spawns = avg_spawns;
    }

    public String getSpawn_time() {
        return spawn_time;
    }

    public void setSpawn_time(String spawn_time) {
        this.spawn_time = spawn_time;
    }

    public List<Double> getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(List<Double> multipliers) {
        this.multipliers = multipliers;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<PrevEvolutionDto> getPrev_evolution() {
        return prev_evolution;
    }

    public void setPrev_evolution(List<PrevEvolutionDto> prev_evolution) {
        this.prev_evolution = prev_evolution;
    }

    public List<NextEvolutionDto> getNext_evolution() {
        return next_evolution;
    }

    public void setNext_evolution(List<NextEvolutionDto> next_evolution) {
        this.next_evolution = next_evolution;
    }

    protected PokemonDto(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        num = in.readString();
        name = in.readString();
        img = in.readString();
        height = in.readString();
        weight = in.readString();
        candy = in.readString();
        candy_count = in.readByte() == 0x00 ? null : in.readInt();
        egg = in.readString();
        spawn_chance = in.readByte() == 0x00 ? null : in.readDouble();
        avg_spawns = in.readByte() == 0x00 ? null : in.readDouble();
        spawn_time = in.readString();
        if (in.readByte() == 0x01) {
            type = new ArrayList<>();
            in.readList(type, String.class.getClassLoader());
        } else {
            type = null;
        }
        if (in.readByte() == 0x01) {
            multipliers = new ArrayList<>();
            in.readList(multipliers, Double.class.getClassLoader());
        } else {
            multipliers = null;
        }
        if (in.readByte() == 0x01) {
            weaknesses = new ArrayList<>();
            in.readList(weaknesses, String.class.getClassLoader());
        } else {
            weaknesses = null;
        }
        if (in.readByte() == 0x01) {
            prev_evolution = new ArrayList<>();
            in.readList(prev_evolution, PrevEvolutionDto.class.getClassLoader());
        } else {
            prev_evolution = null;
        }
        if (in.readByte() == 0x01) {
            next_evolution = new ArrayList<>();
            in.readList(next_evolution, NextEvolutionDto.class.getClassLoader());
        } else {
            next_evolution = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(num);
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(candy);
        if (candy_count == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(candy_count);
        }
        dest.writeString(egg);
        if (spawn_chance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(spawn_chance);
        }
        if (avg_spawns == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(avg_spawns);
        }
        dest.writeString(spawn_time);
        if (type == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(type);
        }
        if (multipliers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(multipliers);
        }
        if (weaknesses == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(weaknesses);
        }
        if (prev_evolution == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(prev_evolution);
        }
        if (next_evolution == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(next_evolution);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PokemonDto> CREATOR = new Parcelable.Creator<PokemonDto>() {
        @Override
        public PokemonDto createFromParcel(Parcel in) {
            return new PokemonDto(in);
        }

        @Override
        public PokemonDto[] newArray(int size) {
            return new PokemonDto[size];
        }
    };

    public static class sortBySpawnChanceAsc implements Comparator<PokemonDto> {
        @Override
        public int compare(PokemonDto o1, PokemonDto o2) {
            spawnChance =
            return 0;
        }
    }
}
