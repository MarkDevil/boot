package com.mark.bank.service;

import com.mark.bank.dto.DbLinkDto;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2019-04-26
 */
public interface IIpQueryService {

    String queryIpAddress();

    void isIpAddress(boolean flag);

    List<DbLinkDto> findIpAddressByPage(int currentPage, int pageSize);
}
