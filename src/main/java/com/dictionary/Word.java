package com.dictionary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {

  @Id
  private String word;

  public Word() {
  }

  public Word(String word) {
    this.word = word;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }
}
