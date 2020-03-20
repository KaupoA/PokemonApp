package com.example.pokemon.pokemon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pokemon.R;
import com.example.pokemon.model.PokemonRepository;
import com.example.pokemon.model.PokemonService;
import com.example.pokemon.model.database.AppDatabase;
import com.example.pokemon.model.database.PokemonDao;
import com.example.pokemon.model.dto.PokemonDto;
import com.example.pokemon.model.dto.PokemonListDto;
import com.example.pokemon.pokemon.widget.PokemonAdapter;
import com.example.pokemon.pokemon.widget.PokemonListActivityCallback;
import com.example.pokemon.pokemondetails.PokemonDetailsActivity;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PokemonActivity extends AppCompatActivity implements PokemonListActivityCallback {

    private int filterOn = 0;
    private PokemonService pokemonService;
    private PokemonAdapter pokemonAdapter;
    private PokemonRepository pokemonRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(getString(R.string.api_base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        pokemonService = retrofit.create(PokemonService.class);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Database").build();
        PokemonDao pokemonDao = db.pokemonDao();
        pokemonRepository = new PokemonRepository(pokemonDao, pokemonService);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_retrofit);
        pokemonAdapter = new PokemonAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(pokemonAdapter);
        pokemonAdapter.setPokemonListActivityCallback(this);

        fetchJSON();
    }

    private void fetchJSON() {

        Call<PokemonListDto> call = pokemonService.getPokemons();

        call.enqueue(new Callback<PokemonListDto>() {
            @Override
            public void onResponse(Call<PokemonListDto> call, Response<PokemonListDto> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PokemonListDto pokemonListDto = response.body();
                        List<PokemonDto> pokemonDtos = pokemonListDto.getPokemon();
                        pokemonAdapter.submitPokemons(pokemonDtos);
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonListDto> call, Throwable t) {
                Toast.makeText(PokemonActivity.this, "Error fetching JSON", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pokemon_list_top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String[] filterValues = getResources().getStringArray(R.array.filter_settings_filter_by_key);
        String filterBy = sharedPreferences.getString(getString(R.string.shared_prefs_filter_settings_by), filterValues[0]);
        String[] orderValues = getResources().getStringArray(R.array.filter_settings_order_key);
        String orderBy = sharedPreferences.getString(getString(R.string.shared_prefs_filter_settings_order), orderValues[0]);
        switch (item.getItemId()) {
            case R.id.filter:
                if (filterOn == 0) {
                    if (filterBy.matches(filterValues[0]) && orderBy.matches(orderValues[0])) {
                        pokemonAdapter.submitPokemons();
                    }
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToPokemonDetails(PokemonDto pokemonDto) {
        Intent pokemonIntent = new Intent(this, PokemonDetailsActivity.class);
        pokemonIntent.putExtra("pokemon", pokemonDto);
        startActivity(pokemonIntent);
    }
}
