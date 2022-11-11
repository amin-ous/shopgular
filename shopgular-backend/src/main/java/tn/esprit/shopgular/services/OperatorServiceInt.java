package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;

public interface OperatorServiceInt {

	Operator addOperator(Operator operator);

	List<Operator> getAllOperators();

	Operator getOperator(Long operatorId);

	Operator updateOperator(Operator operator);

	void deleteOperator(Long operatorId);

}
