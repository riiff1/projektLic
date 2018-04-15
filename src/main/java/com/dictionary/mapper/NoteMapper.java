package com.dictionary.mapper;

import com.dictionary.dto.NoteDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements RowMapper<NoteDto> {
    @Override
    public NoteDto mapRow(ResultSet resultSet, int i) throws SQLException {
        NoteDto noteDto = new NoteDto();
        noteDto.setNoteId(resultSet.getLong("NOTE_ID"));
        noteDto.setUserId(resultSet.getLong("USER_ID_FK"));
        noteDto.setDictionaryId(resultSet.getLong("DICTIONARY_ID_FK"));
        noteDto.setMessage(resultSet.getString("MESSAGE"));
        return noteDto;
    }
}
