package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ユーザー登録時のユニークチェックを行う自作アノテーション
 */
@Documented
@Constraint(validatedBy = UniqueValidator.class) 
@Target(ElementType.FIELD) // 項目に対してバリデーションをかける場合はFIELDを選択
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

	String message() default "このメールアドレスはすでに登録済です。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
        Unique[] value();
    }
}
