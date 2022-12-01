package com.hcmute.ecom.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Nhat Phi
 * @since 2022-12-01
 * */
public interface SaleFiguresDAO {
    BigDecimal getTotalIncomeInDate(LocalDate date);
    BigDecimal getTotalIncomeInYear(int year);

    BigDecimal getTotalExpendituresInDate(LocalDate date);
    BigDecimal getTotalExpendituresInYear(int year);
}
