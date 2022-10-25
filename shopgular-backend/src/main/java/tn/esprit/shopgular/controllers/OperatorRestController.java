package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/operator")
@Api(tags = "Operator Management")
@CrossOrigin(origins = "http://localhost:4200")
public class OperatorRestController {

	@Autowired
	OperatorServiceInt operatorServiceInt;

	@ResponseBody
	@PostMapping("/add-operator")
	public Operator addOperator(@RequestBody OperatorModel operatorModel) {
		return operatorServiceInt.addOperator(operatorModel);
	}

	@ResponseBody
	@GetMapping("/get-all-operators")
	public List<Operator> getOperators() {
		return operatorServiceInt.getAllOperators();
	}

	@ResponseBody
	@GetMapping("/get-operator/{operator-id}")
	public Operator getOperator(@PathVariable("operator-id") Long operatorId) {
		return operatorServiceInt.getOperator(operatorId);
	}

	@ResponseBody
	@PutMapping("/update-operator")
	public Operator updateOperator(@RequestBody OperatorModel operatorModel) {
		return operatorServiceInt.updateOperator(operatorModel);
	}

	@ResponseBody
	@DeleteMapping("/delete-operator/{operator-id}")
	public void deleteOperator(@PathVariable("operator-id") Long operatorId) {
		operatorServiceInt.deleteOperator(operatorId);
	}

}
