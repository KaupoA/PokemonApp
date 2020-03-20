package com.example.pokemon.pokemon.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon.R;
import com.example.pokemon.model.dto.PokemonDto;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.MyViewHolder> {

    private List<PokemonDto> pokemonDtos = new ArrayList<>();
    private PokemonListActivityCallback pokemonListActivityCallback;

    @NonNull
    @Override
    public PokemonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.MyViewHolder holder, int position) {

        Glide.with(holder.itemView).load(pokemonDtos.get(position).getImg()).into(holder.img);

        holder.name.setText(pokemonDtos.get(position).getName());

        holder.type.setText(pokemonDtos.get(position).getType()
                .toString().replaceAll("[\\[\\]]", ""));

        holder.spawnChance.setText("Spawn chance: " + pokemonDtos.get(position).getSpawn_chance()
                .toString() + "%");

        holder.pokemonListLayout.setOnClickListener(v -> {

            pokemonListActivityCallback.navigateToPokemonDetails(pokemonDtos.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return pokemonDtos.size();
    }

    public void submitPokemons(List<PokemonDto> pokemonDtos) {
        this.pokemonDtos = pokemonDtos;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name;
        TextView type;
        TextView spawnChance;
        RelativeLayout pokemonListLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.pokemon_image);
            name = itemView.findViewById(R.id.pokemon_name);
            type = itemView.findViewById(R.id.type_text);
            spawnChance = itemView.findViewById(R.id.spawn_chance_text);
            pokemonListLayout = itemView.findViewById(R.id.pokemon_list_layout);
        }
    }

    public void setPokemonListActivityCallback(PokemonListActivityCallback pokemonListActivityCallback) {
        this.pokemonListActivityCallback = pokemonListActivityCallback;
    }
}
