package com.dictionary;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.inject.Inject;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DictionaryControllerShould {

  @Inject
  DictionaryController dictionaryController;
  DictionaryService dictionaryService;

  @BeforeEach
  void init() {
  dictionaryService = mock(DictionaryService.class);
  dictionaryController = new DictionaryController(dictionaryService);
  }

  @Test
  void fetch_all_words() {
    Iterable<Word> words = dictionaryController.getAllWords();
    assertNotNull(words);
  }

  @Test
  void save_new_word() {
    Word word = new Word("new");
    dictionaryController.saveNewWord(word);
    verify(dictionaryService).saveNewWord(word);
  }

  @Test
  void delete_word(){
    Word word = new Word("delete");
    dictionaryController.delete(word);
    verify(dictionaryService).delete(word);
  }
}
