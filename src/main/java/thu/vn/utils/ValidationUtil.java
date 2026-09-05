package thu.vn.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Tiện ích validate dùng Hibernate Validator (Jakarta Bean Validation).
 * Trả về Map<tênThuộcTính, thôngBáoLỗi> để hiển thị lại trên form.
 */
public final class ValidationUtil {

    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    private ValidationUtil() {
    }

    public static <T> Map<String, String> validate(T target) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ConstraintViolation<T> v : VALIDATOR.validate(target)) {
            // Giữ lỗi đầu tiên cho mỗi field
            errors.putIfAbsent(v.getPropertyPath().toString(), v.getMessage());
        }
        return errors;
    }

    /** Chuẩn hoá tham số form: null -> "", và trim khoảng trắng. */
    public static String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
