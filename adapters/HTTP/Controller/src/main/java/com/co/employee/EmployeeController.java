package com.co.employee;


import co.com.employee.models.Employee;
import co.com.employee.list.ListEmployee;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/employees")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final ListEmployee listEmployee;


    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<Employee>> getBYId(
            @PathVariable String id
    ) {

        try {
            BigInteger idRequest = new BigInteger(id);

            return this.listEmployee
                    .listByIdEmployee(idRequest)
                    .map(employee -> ResponseEntity
                            .ok()
                            .body(employee)
                    );

        } catch (Exception e) {
            return Mono.error(new RuntimeException("id must be a number"));
        }


    }

    @GetMapping()
    public Flux<Employee> getAll() {
        return this.listEmployee.listEmployee();
    }


}
