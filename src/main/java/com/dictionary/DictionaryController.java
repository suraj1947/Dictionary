package com.dictionary;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("dictionary")
public class DictionaryController {

  private final DictionaryService dictionaryService;

  public DictionaryController(DictionaryService dictionaryService) {
    this.dictionaryService = dictionaryService;
  }

  @Get
  Iterable<Word> getAllWords() {
    return dictionaryService.getAllWords();
  }

  @Post
  public Word saveNewWord(@Body Word word) {
    return dictionaryService.saveNewWord(word);
  }

  @Delete
  public Word delete(@Body Word word) {
   dictionaryService.delete(word);
   return word;
  }


}
