package com.co.employee.request;

import co.com.employee.actions.GetListEmployee;
import co.com.employee.models.Employee;
import com.co.employee.DAO.EmployeeDAO;
import com.co.employee.parsers.EmployeeParsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RequestToMassGLobalAPIAll implements GetListEmployee {

    @Value("${service.url}")
    private String serviceURL;

    @Override
    public Flux<Employee> getListEmployee() {
        return WebClient.create()
                .get()
                .uri(serviceURL)
                .retrieve()
                .bodyToFlux(EmployeeDAO.class)
                .map(EmployeeParsers::employeeDAOToEmployee);
    }
}
