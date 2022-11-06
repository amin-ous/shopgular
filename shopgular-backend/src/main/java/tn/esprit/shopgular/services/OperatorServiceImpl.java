package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class OperatorServiceImpl implements OperatorServiceInt {

	@Autowired
	OperatorRepository operatorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Operator addOperator(Operator operator) {
		operator.setCurrentPassword(passwordEncoder.encode(operator.getCurrentPassword()));
		operatorRepository.save(operator);
		return operator;
	}

	@Override
	public List<Operator> getAllOperators() {
		return operatorRepository.findAll();
	}

	@Override
	public Operator getOperator(Long operatorId) {
		return operatorRepository.findById(operatorId).orElse(null);
	}

	@Override
	public Operator updateOperator(Operator operator) {
		Operator targetedOperator = getOperator(operator.getId());
		if (passwordEncoder.matches(operator.getOldPassword(), targetedOperator.getCurrentPassword())) {
			targetedOperator.setSurname(Optional.ofNullable(operator.getSurname()).orElse(targetedOperator.getSurname()));
			targetedOperator.setPrename(Optional.ofNullable(operator.getPrename()).orElse(targetedOperator.getPrename()));
			if (operator.getCurrentPassword() != null) {
				targetedOperator.setOldPassword(targetedOperator.getCurrentPassword());
				targetedOperator.setCurrentPassword(passwordEncoder.encode(operator.getCurrentPassword()));
			}
			operatorRepository.save(targetedOperator);
		}
		return targetedOperator;
	}

	@Override
	public void deleteOperator(Long operatorId) {
		operatorRepository.deleteById(operatorId);
	}

}
