package org.fp024.jpaquick.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class EmployeeSalaryData {
    private Long id;
    private Double salary;
    private Double commissionPct;
}
