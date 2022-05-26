package com.dictionary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DictionaryServiceShould {
  DictionaryService dictionaryService;
  DictionaryRepository dictionaryRepository;
  @BeforeEach
  void init() {
    dictionaryRepository = mock(DictionaryRepository.class);
    dictionaryService = new DictionaryService(dictionaryRepository);
  }

  @Test
  void get_all_words_from_repo() {
    dictionaryService.getAllWords();
    verify(dictionaryRepository).findAll();
  }

  @Test
  void save_new_word() {
    Word word = new Word("new");
    dictionaryService.saveNewWord(word);
    verify(dictionaryRepository).save(word);
  }

}