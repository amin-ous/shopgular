package tn.esprit.shopgular.services;

import java.util.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;

public interface OperatorServiceInt {

	Operator addOperator(OperatorModel operatorModel);

	List<Operator> getAllOperators();

	Operator getOperator(Long operatorId);

	Operator updateOperator(OperatorModel operatorModel);

	void deleteOperator(Long operatorId);

}
