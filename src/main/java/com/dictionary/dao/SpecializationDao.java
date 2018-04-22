package com.dictionary.dao;

import com.dictionary.dto.SpecializationDto;
import com.dictionary.mapper.SpecializationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecializationDao extends BaseDao {

    private static final String sqlAllSpecialization = "select * " +
            "from TBL_SPECIALIZATION;";
    private static final String sqlAvailableSpecializationByUser = "select spe.* " +
            "from TBL_USER u JOIN TBL_PAYMENT pay ON u.USER_ID = pay.USER_ID_FK " +
            "  JOIN TBL_PAYMENT_SPECIALIZATION payspe ON pay.PAYMENT_ID = payspe.PAYMENT_ID_FK " +
            "  JOIN TBL_SPECIALIZATION spe ON payspe.SPECIALIZATION_ID_FK = spe.SPECIALIZATION_ID " +
            "where u.USER_ID = ? and pay.EXPIRE_DATE > current_date();";
    
    private static final String sqlNotAvailableSpecializationByUser = "select *" + "from TBL_SPECIALIZATION " + "where SPECIALIZATION_ID not in (select spe.SPECIALIZATION_ID "
            + "from TBL_USER u JOIN TBL_PAYMENT pay ON u.USER_ID = pay.USER_ID_FK "
            + "JOIN TBL_PAYMENT_SPECIALIZATION payspe ON pay.PAYMENT_ID = payspe.PAYMENT_ID_FK "
            + "JOIN TBL_SPECIALIZATION spe ON payspe.SPECIALIZATION_ID_FK = spe.SPECIALIZATION_ID "
            + "where u.USER_ID = ? and pay.EXPIRE_DATE > current_date());";

    public List<SpecializationDto> getAvailableSpecializationByUser(long userId) {
        return getTemplate().query(sqlAvailableSpecializationByUser, new Object[] {userId}, new SpecializationMapper());
    }

    public List<SpecializationDto> getNotAvailableSpecializationByUser(long userId) {
        return getTemplate().query(sqlNotAvailableSpecializationByUser, new Object[] {userId}, new SpecializationMapper());
    }

    public List<SpecializationDto> getAllSpecialization() {
        return getTemplate().query(sqlAllSpecialization, new SpecializationMapper());
    }
}
