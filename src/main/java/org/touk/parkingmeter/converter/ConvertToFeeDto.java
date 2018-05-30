package org.touk.parkingmeter.converter;

import org.springframework.stereotype.Component;
import org.touk.parkingmeter.domain.Currency;
import org.touk.parkingmeter.dto.FeeDto;

import java.math.BigDecimal;

@Component
public class ConvertToFeeDto {

    public FeeDto convertToFeeDto(Currency currency, BigDecimal fee) {

        FeeDto feeDto = new FeeDto();
        feeDto.setCurrency(currency);
        feeDto.setFee(fee);

        return feeDto;
    }
}
