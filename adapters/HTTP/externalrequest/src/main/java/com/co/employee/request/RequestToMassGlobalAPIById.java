package com.co.employee.request;

import co.com.employee.actions.GetEmployeeById;
import co.com.employee.models.Employee;
import com.co.employee.DAO.EmployeeDAO;
import com.co.employee.parsers.EmployeeParsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Service
public class RequestToMassGlobalAPIById implements GetEmployeeById {

    @Value("${service.url}")
    private String serviceURL;

    @Override
    public Mono<Employee> getEmployeeById(BigInteger id) {
        return WebClient
                .create()
                .get()
                .uri(serviceURL)
                .retrieve()
                .bodyToFlux(EmployeeDAO.class)
                .filter(employeeDAO -> employeeDAO.getId().equals(id))
                .next()
                .map(EmployeeParsers::employeeDAOToEmployee);
    }
}
