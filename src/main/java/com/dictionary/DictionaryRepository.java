package com.dictionary;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface DictionaryRepository extends CrudRepository<Word, String> {

  @Query("update Word set word= :UpdatedWord where word=:actualWord")
  String updateWord(String actualWord, String UpdatedWord);
}
