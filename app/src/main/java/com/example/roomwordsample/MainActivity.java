package com.example.roomwordsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomwordsample.adapter.WordListAdapter;
import com.example.roomwordsample.databinding.ActivityMainBinding;
import com.example.roomwordsample.model.Word;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WordViewModel mWordViewModel;
    private WordListAdapter mAdapter; // Déclaration de l'adaptateur en tant que membre de la classe
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialiser le ViewModel
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        // Observer les changements dans la liste des mots et mettre à jour l'adaptateur
        mWordViewModel.getAllWords().observe(this, words -> {
            if (words != null) {
                mAdapter.setWords(words);  // Met à jour les mots dans l'adaptateur
            }
        });

        // Initialiser le binding et définir le contenu de l'interface utilisateur
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialiser l'adaptateur
        mAdapter = new WordListAdapter();
        binding.contentMain.recyclerview.setAdapter(mAdapter);
        binding.contentMain.recyclerview.setHasFixedSize(true);

        // Configurer la barre d'outils
        setSupportActionBar(binding.toolbar);

        // Configurer le bouton FAB
        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Gonfler le menu; cela ajoute des éléments à la barre d'outils si elle est présente.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gérer les clics sur les éléments de la barre d'outils.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        } }
}
