package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;

//classe auxiliar criada somente para trafegar a senha na inserção do usuário. Por isso não tem senha no UserDTO
//anotation criada por mim abaixo. código copiado aula validação e segurança ConstraintValidator customizado
@UserInsertValid
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
