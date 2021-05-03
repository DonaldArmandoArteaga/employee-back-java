package co.com.employee.actions;

import co.com.employee.models.Employee;
import reactor.core.publisher.Flux;

public interface GetListEmployee {

    Flux<Employee> getListEmployee();

}
