package org.touk.parkingmeter.dto;

import lombok.Data;
import org.touk.parkingmeter.domain.Currency;

import java.math.BigDecimal;

@Data
public class FeeDto {

    private Currency currency;
    private BigDecimal fee;
}

