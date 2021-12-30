package soulland.project.response;

public class JwtTokenResponse {

	private String token;
	private String refreshToken;
    private String typeToken;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getTypeToken() {
		return typeToken;
	}
	public void setTypeToken(String typeToken) {
		this.typeToken = typeToken;
	}
	public JwtTokenResponse(String token, String refreshToken, String typeToken) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.typeToken = typeToken;
	}
  
}