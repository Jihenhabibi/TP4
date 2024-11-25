package com.example.roomwordsample.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.roomwordsample.model.Word;
import java.util.List;

@Dao
public interface WordDao {

    // Permet d'insérer un mot dans la base de données, ignore les doublons
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    // Supprime tous les mots de la table
    @Query("DELETE FROM word_table")
    void deleteAll();

    // Récupère tous les mots ordonnés par ordre alphabétique, encapsulés dans LiveData pour pouvoir être observés
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
}
