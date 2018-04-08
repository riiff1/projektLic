package com.dictionary.dto;

import java.io.Serializable;

public class NoteDto implements Serializable {
    private static final long serialVersionUID = 6686391995094780717L;
    private long noteId;
    private long userId;
    private long dictionaryId;
    private String message;

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
