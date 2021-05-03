package co.com.employee.factories;

import co.com.employee.models.Employee;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
public class EmployeeFactory {

    public Employee newEmployee(
            BigInteger id,
            String name,
            String contractTypeName,
            String roleName,
            String roleDescription,
            BigInteger hourlySalary,
            BigInteger monthlySalary,
            BigInteger annualSalary
    ) {

        return Employee
                .builder()
                .id(id)
                .name(name)
                .contractTypeName(contractTypeName)
                .roleName(roleName)
                .roleDescription(roleDescription)
                .hourlySalary(hourlySalary)
                .monthlySalary(monthlySalary)
                .annualSalary(annualSalary)
                .build();

    }


}
