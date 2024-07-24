package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	UserRepository repository;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		//variavel para pegar os artibutos da URL como por exemplo o 2 do http://localhost/users/2		
		var uriVars = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userID = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		//primeiro teste. Testar se o email ja existe no banco e é de outro usuário
		User user = repository.findByEmail(dto.getEmail());
		if (user != null && userID != user.getId()) {
		   list.add(new FieldMessage("email", "Email existente para usuário " + dto.getFirstName() + ", " + dto.getEmail()));	
		}
		
		//inserir no bean validation a lista de erros para exibir no json. Contrario ao ResourceExceptionHandler public ResponseEntity<ValidationError> validaCampos(Met......		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}