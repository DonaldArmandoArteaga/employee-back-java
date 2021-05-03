package com.co.dominio.empleado;

import co.com.employee.actions.GetEmployeeById;
import co.com.employee.actions.GetListEmployee;
import co.com.employee.factories.EmployeeFactory;
import co.com.employee.list.ListEmployee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration {

    @Bean
    public ListEmployee listEmployee(
            GetEmployeeById getEmployeeById,
            EmployeeFactory employeeFactory,
            GetListEmployee getListEmployee
    ) {
        return new ListEmployee(getEmployeeById, employeeFactory, getListEmployee);
    }


    @Bean
    public EmployeeFactory employeeFactory() {
        return new EmployeeFactory();
    }


}
