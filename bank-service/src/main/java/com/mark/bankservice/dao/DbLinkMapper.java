package com.mark.bankservice.dao;

import com.mark.bankservice.dto.DbLinkDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2019/3/8
 */
@Component
public interface DbLinkMapper {
    @Select("select * from q_api_db_link")
    List<DbLinkDto> findAllDbLinks();


}
