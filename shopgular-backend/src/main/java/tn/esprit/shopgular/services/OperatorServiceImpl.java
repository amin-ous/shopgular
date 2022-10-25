package tn.esprit.shopgular.services;

import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.repositories.*;

@Service
@Transactional
public class OperatorServiceImpl implements OperatorServiceInt {

	@Autowired
	OperatorRepository operatorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Operator addOperator(OperatorModel operatorModel) {
		operatorModel.setNewPassword(passwordEncoder.encode(operatorModel.getNewPassword()));
		Operator operator = new Operator(operatorModel.getName(), operatorModel.getPrename(), operatorModel.getNewPassword());
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
	public Operator updateOperator(OperatorModel operatorModel) {
		Operator operator = getOperator(operatorModel.getId());
		if (passwordEncoder.matches(operatorModel.getOldPassword(), operator.getPassword())) {
			operator.setName(Optional.ofNullable(operatorModel.getName()).orElse(operator.getName()));
			operator.setPrename(Optional.ofNullable(operatorModel.getPrename()).orElse(operator.getPrename()));
			operator.setPassword(Optional.ofNullable(passwordEncoder.encode(operatorModel.getNewPassword())).orElse(operator.getPassword()));
			operatorRepository.save(operator);
		}
		return operator;
	}

	@Override
	public void deleteOperator(Long operatorId) {
		operatorRepository.deleteById(operatorId);
	}

}
