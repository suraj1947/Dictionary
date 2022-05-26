package com.dictionary;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface DictionaryRepository extends CrudRepository<Word, String> {

}
