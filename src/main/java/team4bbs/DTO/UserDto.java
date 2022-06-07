package team4bbs.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotEmpty(message = "비밀번호는 필수입력 항목입니다.")
    private String password;
    @NotEmpty(message = "전화번호를 입력해주세요")
    private String phone;
    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;
@Builder
public UserDto(String name, String password, String phone, String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
