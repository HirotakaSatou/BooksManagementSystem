package com.example.demo.form;

import com.example.demo.validation.Unique;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersForm {

	@NotBlank(message = "苗字を入力してください。")
	private String lastName;
	
	@NotBlank(message = "名前を入力してください。")
	private String firstName;
	
	@NotBlank(message = "メールアドレスを入力してください。")
	@Unique // メールアドレスの重複チェックに自作バリデーション「@Unique」を使用
	private String email;
	
	@NotBlank(message = "パスワードを入力してください。")
	private String password;
	
}
