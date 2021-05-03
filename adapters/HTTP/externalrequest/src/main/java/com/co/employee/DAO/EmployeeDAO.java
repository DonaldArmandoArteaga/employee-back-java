package com.co.employee.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeDAO {

    private BigInteger id;
    private String name;
    private String contractTypeName;
    private BigInteger roleId;
    private String roleName;
    private String roleDescription;
    private BigInteger hourlySalary;
    private BigInteger monthlySalary;

}
