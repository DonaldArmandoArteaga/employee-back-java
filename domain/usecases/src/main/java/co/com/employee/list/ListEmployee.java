package co.com.employee.list;

import co.com.employee.actions.GetEmployeeById;
import co.com.employee.actions.GetListEmployee;
import co.com.employee.exceptions.NotFound;
import co.com.employee.exceptions.Required;
import co.com.employee.factories.EmployeeFactory;
import co.com.employee.models.ContractType;
import co.com.employee.models.Employee;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RequiredArgsConstructor
public class ListEmployee {

    private final GetEmployeeById getEmployeeById;
    private final EmployeeFactory employeeFactory;
    private final GetListEmployee getListEmployee;


    public Mono<Employee> listByIdEmployee(BigInteger id) {
        return this.getEmployeeById.getEmployeeById(id)
                .map(this::calculateAnnualSalary)
                .switchIfEmpty(Mono.error(new NotFound("employee not found, id = ".concat(id.toString()))));
    }

    public Flux<Employee> listEmployee() {
        return this.getListEmployee.getListEmployee().map(this::calculateAnnualSalary);
    }

    private Employee calculateAnnualSalary(Employee employee) {
        return employeeFactory.newEmployee(
                employee.getId(),
                employee.getName(),
                employee.getContractTypeName(),
                employee.getRoleName(),
                employee.getRoleDescription(),
                employee.getHourlySalary(),
                employee.getMonthlySalary(),
                annualSalaryFormula(
                        employee.getContractTypeName(),
                        employee.getHourlySalary(),
                        employee.getMonthlySalary()
                )
        );
    }

    private BigInteger annualSalaryFormula(
            String contractTypeName,
            BigInteger hourlySalary,
            BigInteger monthlySalary
    ) {

        if (contractTypeName.equals(ContractType.HOURLY_SALARY.getContractType())) {
            return hourlySalary
                    .multiply(BigInteger.valueOf(120))
                    .multiply(BigInteger.valueOf(12));
        }

        if (contractTypeName.equals(ContractType.MONTHLY_SALARY.getContractType())) {
            return monthlySalary
                    .multiply(BigInteger.valueOf(12));
        }

        throw new Required(Employee.CONTRACT_TYPE_NO_ENUM_VALUE);

    }

}
