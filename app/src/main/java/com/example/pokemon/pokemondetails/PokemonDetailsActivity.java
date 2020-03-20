package com.example.pokemon.pokemondetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon.R;
import com.example.pokemon.model.dto.PokemonDto;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PokemonDetailsActivity extends AppCompatActivity {

    public String pokemonImage, pokemonName, pokemonHeight, pokemonWeight;
    public List<String> pokemonType;
    public List<String> pokemonWeaknesses;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_view);

        getPokemonNextEvolution();
        getPokemonPrevEvolution();

        Intent intent = getIntent();
        PokemonDto pokemonDto = intent.getParcelableExtra("pokemon");

        pokemonImage = pokemonDto.getImg();
        pokemonName = pokemonDto.getName();
        pokemonType = pokemonDto.getType();
        pokemonHeight = pokemonDto.getHeight();
        pokemonWeight = pokemonDto.getWeight();
        pokemonWeaknesses = pokemonDto.getWeaknesses();

        ImageView image = findViewById(R.id.pokemon_view_image);
        Glide.with(this).load(pokemonImage).into(image);

        TextView name = findViewById(R.id.pokemon_view_name);
        name.setText(pokemonName);

        TextView type = findViewById(R.id.pokemon_view_type);
        type.setText(pokemonType.toString().replaceAll("[\\[\\]]", ""));

        TextView height = findViewById(R.id.pokemon_view_dimensions_height);
        height.setText("H: " + pokemonHeight);

        TextView weight = findViewById(R.id.pokemon_view_dimensions_weight);
        weight.setText("W: " + pokemonWeight);

        TextView weaknesses = findViewById(R.id.pokemon_view_weaknesses);
        weaknesses.setText(pokemonWeaknesses.toString().replaceAll("[\\[\\]]", ""));
    }

    private void getPokemonNextEvolution() {

        TextView nextEvo = findViewById(R.id.pokemon_view_next_evolution);
        String multipleEvo = "";

        Intent intent = getIntent();
        PokemonDto pokemonDto = intent.getParcelableExtra("pokemon");

        if (pokemonDto.getNext_evolution() == null && pokemonDto.getPrev_evolution() == null) {

            nextEvo.setText("This is my only form");

        } else if (pokemonDto.getNext_evolution() == null) {

            nextEvo.setText("This is the final form");

        } else {

            for (int i = 0; i < pokemonDto.getNext_evolution().size(); i++) {

                String num = pokemonDto.getNext_evolution().get(i).getNum();
                String name = pokemonDto.getNext_evolution().get(i).getName();
                multipleEvo = multipleEvo + num + name + "\n";
            }

            nextEvo.setText(multipleEvo);
        }
    }

    private void getPokemonPrevEvolution() {

        TextView prevEvo = findViewById(R.id.pokemon_view_previous_evolution);
        String multipleEvo = "";

        Intent intent = getIntent();
        PokemonDto pokemonDto = intent.getParcelableExtra("pokemon");

        if (pokemonDto.getNext_evolution() == null && pokemonDto.getPrev_evolution() == null) {

            // prevEvo.setText("This is my only form");
        } else if (pokemonDto.getPrev_evolution() == null) {

            prevEvo.setText("This is the first form");

        } else {
            for (int i = 0; i < pokemonDto.getPrev_evolution().size(); i++) {

                String num = pokemonDto.getPrev_evolution().get(i).getNum();
                String name = pokemonDto.getPrev_evolution().get(i).getName();
                multipleEvo = multipleEvo + num + name + "\n";
            }

            prevEvo.setText(multipleEvo);
        }
    }
}