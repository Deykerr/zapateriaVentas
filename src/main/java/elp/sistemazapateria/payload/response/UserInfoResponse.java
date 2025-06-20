package elp.sistemazapateria.payload.response;

import java.util.List;

import lombok.Data;
@Data
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String accessToken;



}
