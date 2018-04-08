package com.dictionary.dto;

import java.io.Serializable;

public class DictionaryDto implements Serializable {
    private static final long serialVersionUID = 7969331868180393492L;
    private long dictionaryId;
    private long specjalizationId;
    private String word;
    private String expression;

    public long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public long getSpecjalizationId() {
        return specjalizationId;
    }

    public void setSpecjalizationId(long specjalizationId) {
        this.specjalizationId = specjalizationId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
