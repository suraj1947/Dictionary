package com.dictionary;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

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

  @Put("/{updatedWord}")
  public Word update(@Body Word actualWord, @PathVariable("updatedWord") String newWord) {
    dictionaryService.update(actualWord,newWord);
    return actualWord;
  }
}
