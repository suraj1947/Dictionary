package com.dictionary;

import jakarta.inject.Singleton;

@Singleton
public class DictionaryService {


  private final DictionaryRepository dictionaryRepository;

  public DictionaryService(DictionaryRepository dictionaryRepository) {
    this.dictionaryRepository = dictionaryRepository;
  }

  Iterable<Word> getAllWords() {
    return dictionaryRepository.findAll();
  }

  public Word saveNewWord(Word word) {
    return dictionaryRepository.save(word);
  }

  public void delete(Word word) {
    dictionaryRepository.delete(word);
  }
}
