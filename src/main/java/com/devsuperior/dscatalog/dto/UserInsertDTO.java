package com.devsuperior.dscatalog.dto;


//classe auxiliar criada somente para trafegar a senha na inserção do usuário. Por isso não tem senha no UserDTO
public class UserInsertDTO extends UserDTO{

	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserInsertDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
