package co.com.employee.validations;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumbersValidation {

    public static Boolean notNullAndNotEmpty(BigInteger... integers) {
        return Stream.of(integers)
                .anyMatch(Objects::isNull);
    }

    public static Boolean notNegativeOrZero(BigInteger... integers) {
        return Stream.of(integers)
                .anyMatch(integer ->
                        integer.compareTo(BigInteger.ZERO) == 0 ||
                                integer.compareTo(BigInteger.ZERO) < 0);
    }


}
