package com.mark.bank.dao;

import com.mark.bank.dto.DbLinkDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2019/3/8
 */
@Repository
public interface DbLinkDao {
    @Select("select * from q_api_db_link")
    List<DbLinkDto> findAllDbLinks();


}
