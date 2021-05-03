package co.com.employee.validations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringsValidation {

    public static Boolean notNullAndNotEmpty(String... strings) {
        return Stream.of(strings)
                .anyMatch(string -> Objects.isNull(string) || string.isEmpty());
    }


}
