package com.dictionary.dao;

import com.dictionary.dto.SpecializationColorDto;
import com.dictionary.dto.SpecializationDto;
import com.dictionary.mapper.SpecializationColorMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecializationColorDao extends BaseDao {

    private static final String sqlColorForAvailableSpecialization = "select col.*, spec.NAME_ " +
            "from TBL_SPECIALIZATION_COLOR col JOIN TBL_SPECIALIZATION spec ON col.SPECIALIZATION_ID_FK = spec.SPECIALIZATION_ID " +
            "where col.USER_ID_FK = ? and col.SPECIALIZATION_ID_FK in (%s);";
    private static final String sqlUpdateColor = "update TBL_SPECIALIZATION_COLOR set COLOR = ? where SPECIALIZATION_COLOR_ID = ?;";

    public List<SpecializationColorDto> getAvailableSpecializationByUser(long userId, List<SpecializationDto> specializationIds) {
        String specializationIdsString = specializationIds.stream().map(spec -> String.valueOf(spec.getSpecializationId())).collect(Collectors.joining(", "));
        return getTemplate().query(String.format(sqlColorForAvailableSpecialization, specializationIdsString), new Object[]{userId}, new SpecializationColorMapper());
    }

    public void updateSpecializationColorBatch(List<SpecializationColorDto> specializationColorDto) {
        getTemplate().batchUpdate(sqlUpdateColor, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                SpecializationColorDto dto = specializationColorDto.get(i);
                preparedStatement.setString(1, dto.getColor());
                preparedStatement.setLong(2, dto.getSpecializationColorId());
            }

            @Override
            public int getBatchSize() {
                return specializationColorDto.size();
            }
        });
    }
}
