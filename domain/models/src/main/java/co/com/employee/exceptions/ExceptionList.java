package co.com.employee.exceptions;

import java.util.List;

public class ExceptionList extends RuntimeException {

    public ExceptionList(List<String> mensajes) {
        super(String.join("/n", mensajes));
    }
}
