package com.co.employee.parsers;

import co.com.employee.models.Employee;
import com.co.employee.DAO.EmployeeDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmployeeParsers {


    public static Employee employeeDAOToEmployee(EmployeeDAO employeeDAO) {
        return Employee
                .builder()
                .id(employeeDAO.getId())
                .name(employeeDAO.getName())
                .contractTypeName(employeeDAO.getContractTypeName())
                .roleName(employeeDAO.getRoleName())
                .roleDescription(employeeDAO.getRoleDescription())
                .hourlySalary(employeeDAO.getHourlySalary())
                .monthlySalary(employeeDAO.getMonthlySalary())
                .build();
    }

}
