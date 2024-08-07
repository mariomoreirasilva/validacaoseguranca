package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	@Autowired
	UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		//primeiro teste. Testar se o email ja existe no banco
		User user = repository.findByEmail(dto.getEmail());
		if (user != null) {
		   list.add(new FieldMessage("email", "Email ja existe!!  " + dto.getEmail()));	
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