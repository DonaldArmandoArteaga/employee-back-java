package co.com.employee.actions;


import co.com.employee.models.Employee;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface GetEmployeeById {

    Mono<Employee> getEmployeeById(BigInteger id);

}
