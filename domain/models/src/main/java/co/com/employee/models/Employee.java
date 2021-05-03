package co.com.employee.models;

import co.com.employee.exceptions.ExceptionList;
import co.com.employee.validations.NumbersValidation;
import co.com.employee.validations.StringsValidation;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class Employee {

    private static final String ID_REQUIRED = "Id is required";
    private static final String NAME_REQUIRED = "Name is required";
    private static final String CONTRACT_TYPE_NAME_REQUIRED = "Contract type name is required";
    private static final String ROLE_NAME_REQUIRED = " Role name is required";
    private static final String HOURLY_SALARY_REQUIRED = "Hourly salary is required";
    private static final String MONTHLY_SALARY_REQUIRED = "Monthly salary is required";

    private static final String ID_NOT_NEGATIVE = "Id must be positive";
    private static final String HOURLY_SALARY_NOT_NEGATIVE = "Hourly salary must be positive";
    private static final String MONTHLY_SALARY_NOT_NEGATIVE = "Monthly salary must be positive";

    public static final String CONTRACT_TYPE_NO_ENUM_VALUE = "Error when none of the allowed values arrive";


    private final BigInteger id;
    private final String name;
    private final String contractTypeName;
    private final String roleName;
    private final String roleDescription;
    private final BigInteger hourlySalary;
    private final BigInteger monthlySalary;
    private final BigInteger annualSalary;


    @Builder(toBuilder = true)
    public Employee(
            BigInteger id,
            String name,
            String contractTypeName,
            String roleName,
            String roleDescription,
            BigInteger hourlySalary,
            BigInteger monthlySalary,
            BigInteger annualSalary
    ) {

        List<String> exceptionsList = new <String>ArrayList();


        if (StringsValidation.notNullAndNotEmpty(name)) {
            exceptionsList.add(NAME_REQUIRED);
        }

        if (StringsValidation.notNullAndNotEmpty(contractTypeName)) {
            exceptionsList.add(CONTRACT_TYPE_NAME_REQUIRED);
        }

        if (StringsValidation.notNullAndNotEmpty(roleName)) {
            exceptionsList.add(ROLE_NAME_REQUIRED);
        }

        if (NumbersValidation.notNullAndNotEmpty(id)) {
            exceptionsList.add(ID_REQUIRED);
        }

        if (NumbersValidation.notNullAndNotEmpty(hourlySalary)) {
            exceptionsList.add(HOURLY_SALARY_REQUIRED);
        }

        if (NumbersValidation.notNullAndNotEmpty(monthlySalary)) {
            exceptionsList.add(MONTHLY_SALARY_REQUIRED);
        }

        if (NumbersValidation.notNegativeOrZero(id)) {
            exceptionsList.add(ID_NOT_NEGATIVE);
        }

        if (NumbersValidation.notNegativeOrZero(hourlySalary)) {
            exceptionsList.add(HOURLY_SALARY_NOT_NEGATIVE);
        }

        if (NumbersValidation.notNegativeOrZero(monthlySalary)) {
            exceptionsList.add(MONTHLY_SALARY_NOT_NEGATIVE);
        }

        if (!(contractTypeName.equals(ContractType.HOURLY_SALARY.getContractType())
                || contractTypeName.equals(ContractType.MONTHLY_SALARY.getContractType()))
        ) {
            exceptionsList.add(CONTRACT_TYPE_NO_ENUM_VALUE);
        }

        if (!exceptionsList.isEmpty()) {
            throw new ExceptionList(exceptionsList);
        }

        this.id = id;
        this.name = name;
        this.contractTypeName = contractTypeName;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.hourlySalary = hourlySalary;
        this.monthlySalary = monthlySalary;
        this.annualSalary = annualSalary;
    }
}
