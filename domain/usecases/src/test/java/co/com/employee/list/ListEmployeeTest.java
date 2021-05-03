package co.com.employee.list;

import co.com.employee.actions.GetEmployeeById;
import co.com.employee.actions.GetListEmployee;
import co.com.employee.exceptions.NotFound;
import co.com.employee.exceptions.Required;
import co.com.employee.factories.EmployeeFactory;
import co.com.employee.models.ContractType;
import co.com.employee.models.Employee;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigInteger;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ListEmployeeTest {


    @Test
    public void listByIdEmployeeWhenHoulrySalaryInContractType() {
        //Arrange

        Mono<Employee> employeeWithoutAnnualSalary = Mono.just(
                Employee
                        .builder()
                        .id(BigInteger.ONE)
                        .name("any name")
                        .roleName("any role")
                        .roleDescription(null)
                        .contractTypeName(ContractType.HOURLY_SALARY.getContractType())
                        .hourlySalary(BigInteger.valueOf(60000))
                        .monthlySalary(BigInteger.valueOf(80000))
                        .build()
        );

        Employee employeeWithAnnualSalary = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.HOURLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .annualSalary(BigInteger.valueOf(86400000))
                .build();

        GetEmployeeById getEmployeeById = mock(GetEmployeeById.class);
        EmployeeFactory employeeFactory = new EmployeeFactory();
        GetListEmployee getListEmployee = mock(GetListEmployee.class);

        when(getEmployeeById.getEmployeeById(BigInteger.ONE)).thenReturn(employeeWithoutAnnualSalary);

        ListEmployee listEmployee = new ListEmployee(getEmployeeById, employeeFactory, getListEmployee);


        //act

        Mono<Employee> response = listEmployee.listByIdEmployee(BigInteger.ONE);

        //assert

        StepVerifier.create(response).expectNext(employeeWithAnnualSalary).verifyComplete();


    }


    @Test
    public void listByIdEmployeeWhenMonthlySalaryInContractType() {
        //Arrange

        Mono<Employee> employeeWithoutAnnualSalary = Mono.just(
                Employee
                        .builder()
                        .id(BigInteger.ONE)
                        .name("any name")
                        .roleName("any role")
                        .roleDescription(null)
                        .contractTypeName(ContractType.MONTHLY_SALARY.getContractType())
                        .hourlySalary(BigInteger.valueOf(60000))
                        .monthlySalary(BigInteger.valueOf(80000))
                        .build()
        );

        Employee employeeWithAnnualSalary = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.MONTHLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .annualSalary(BigInteger.valueOf(960000))
                .build();

        GetEmployeeById getEmployeeById = mock(GetEmployeeById.class);
        EmployeeFactory employeeFactory = new EmployeeFactory();
        GetListEmployee getListEmployee = mock(GetListEmployee.class);

        when(getEmployeeById.getEmployeeById(BigInteger.ONE)).thenReturn(employeeWithoutAnnualSalary);

        ListEmployee listEmployee = new ListEmployee(getEmployeeById, employeeFactory, getListEmployee);


        //act

        Mono<Employee> response = listEmployee.listByIdEmployee(BigInteger.ONE);

        //assert

        StepVerifier.create(response).expectNext(employeeWithAnnualSalary).verifyComplete();


    }

    @Test
    public void listByIdEmployeeWhenIsEmpty() {
        //Arrange



        GetEmployeeById getEmployeeById = mock(GetEmployeeById.class);
        EmployeeFactory employeeFactory = new EmployeeFactory();
        GetListEmployee getListEmployee = mock(GetListEmployee.class);

        when(getEmployeeById.getEmployeeById(BigInteger.ZERO)).thenReturn(Mono.empty());

        ListEmployee listEmployee = new ListEmployee(getEmployeeById, employeeFactory, getListEmployee);


        //act

        Mono<Employee> response = listEmployee.listByIdEmployee(BigInteger.ZERO);

        //assert

        StepVerifier.create(response).verifyError(NotFound.class);
        StepVerifier.create(response).verifyErrorMessage("employee not found, id = 0");


    }


    @Test
    public void listEmployee() {
        //Arrange

        Employee employeeWithoutAnnualSalaryMonthly = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.MONTHLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .build();

        Employee employeeWithoutAnnualSalaryHourly = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.HOURLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .build();


        Employee employeeWithAnnualSalaryMonthly = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.MONTHLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .annualSalary(BigInteger.valueOf(960000))
                .build();

        Employee employeeWithAnnualSalaryHourly = Employee
                .builder()
                .id(BigInteger.ONE)
                .name("any name")
                .roleName("any role")
                .roleDescription(null)
                .contractTypeName(ContractType.HOURLY_SALARY.getContractType())
                .hourlySalary(BigInteger.valueOf(60000))
                .monthlySalary(BigInteger.valueOf(80000))
                .annualSalary(BigInteger.valueOf(86400000))
                .build();


        GetEmployeeById getEmployeeById = mock(GetEmployeeById.class);
        EmployeeFactory employeeFactory = new EmployeeFactory();
        GetListEmployee getListEmployee = mock(GetListEmployee.class);

        when(getListEmployee.getListEmployee())
                .thenReturn(Flux.just(employeeWithoutAnnualSalaryHourly, employeeWithoutAnnualSalaryMonthly));

        ListEmployee listEmployee = new ListEmployee(getEmployeeById, employeeFactory, getListEmployee);


        //act

        Flux<Employee> response = listEmployee.listEmployee();

        //assert

        StepVerifier
                .create(response)
                .expectNext(employeeWithAnnualSalaryHourly)
                .expectNext(employeeWithAnnualSalaryMonthly)
                .verifyComplete();


    }


}
