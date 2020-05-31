package com.demo.service.idcard;

import com.demo.api.dto.Result;
import com.demo.api.service.IdcardService;
import org.springframework.stereotype.Service;

@Service
public class IdcardServiceImpl implements IdcardService {

    @Override
    public Result parseIdcard(String idcardNO) {
        return null;
    }

    @Override
    public String getAreaInfo(String areaCode) {
        return null;
    }

    @Override
    public String getBirthday(String dateNumSeries) {
        return null;
    }

    @Override
    public Boolean checkIdcard(String fullIdcardSeries) {
        return null;
    }

    @Override
    public String getGender(String idCardNumber) {
        return null;
    }

    @Override
    public Result generateIdcard() {
        return null;
    }
}
