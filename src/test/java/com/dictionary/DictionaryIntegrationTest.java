package com.dictionary;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class DictionaryIntegrationTest {

  @Inject
  @Client("/")
  HttpClient httpClient;
  Word word1;
  Word word2;

  @BeforeEach
  void setup() {
    word1 = new Word("word1");
    word1.setWord("word1");
    word1 = httpClient.toBlocking()
        .retrieve(HttpRequest.POST("/dictionary", word1), Argument.of(Word.class));
    word2 = new Word("word2");
    word2.setWord("word2");
    word2 = httpClient.toBlocking()
        .retrieve(HttpRequest.POST("/dictionary", word2), Argument.of(Word.class));
  }

  @AfterEach
  void clear() {
    List<Word> words = httpClient.toBlocking()
        .retrieve(HttpRequest.GET("dictionary"), Argument.listOf(Word.class));

  }

  @Test
  void save_new_word() {
    Word word = new Word("new");
    word = httpClient.toBlocking()
        .retrieve(HttpRequest.POST("dictionary", word), Argument.of(Word.class));
    assertThat(word.getWord()).isEqualTo("new");
  }

  @Test
  void search_for_given_word() {
    List<Word> receivedWords = httpClient.toBlocking().retrieve(HttpRequest.GET("dictionary"),
        Argument.listOf(Word.class));
    List<String> actualWords = receivedWords.stream().map(Word::getWord)
        .collect(Collectors.toList());
    List<String> expectedWords = List.of("word1", "word2");
    assertThat(expectedWords).containsExactlyInAnyOrderElementsOf(actualWords);
  }

  @Test
  void delete_word_from_db() {
    Word word = new Word("word1");
    Word deletedWord = httpClient.toBlocking()
        .retrieve(HttpRequest.DELETE("dictionary", word), Word.class);
    List<Word> receivedWords = httpClient.toBlocking()
        .retrieve(HttpRequest.GET("dictionary"), Argument.listOf(Word.class));
    List<String> wordsInDB = receivedWords.stream().map(Word::getWord).collect(Collectors.toList());
    assertFalse(wordsInDB.contains("word1"));
  }

  void update_existing_word_in_db() {
    Word actualWord = new Word("new");
    httpClient.toBlocking()
        .retrieve(HttpRequest.PUT("dictionary/update", actualWord), Argument.of(Word.class));
    List<Word> receivedWords = httpClient.toBlocking()
        .retrieve(HttpRequest.GET("dictionary"), Argument.listOf(Word.class));
    List<String> wordsInDB = receivedWords.stream().map(Word::getWord).collect(Collectors.toList());
    assertFalse(wordsInDB.contains("word1"));
    assertTrue(wordsInDB.contains("new"));
  }
}
