package tn.esprit.shopgular.controllers;

import io.swagger.annotations.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.format.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.shopgular.entities.*;
import tn.esprit.shopgular.models.*;
import tn.esprit.shopgular.services.*;

@RestController
@RequestMapping("/regulation")
@Api(tags = "Regulation Management")
@CrossOrigin(origins = "http://localhost:4200")
public class RegulationRestController {

	@Autowired
	RegulationServiceInt regulationServiceInt;

	@ResponseBody
	@PostMapping("/add-regulation")
	public Regulation addRegulation(@RequestBody RegulationModel regulationModel) {
		Regulation regulation = new Regulation(regulationModel.getAmountPaid(), regulationModel.getAmountRemaining());
		return regulationServiceInt.addRegulation(regulation);
	}

	@ResponseBody
	@GetMapping("/get-all-regulations")
	public List<Regulation> getAllRegulations() {
		return regulationServiceInt.getAllRegulations();
	}

	@ResponseBody
	@GetMapping("/get-regulation/{regulation-id}")
	public Regulation getRegulation(@PathVariable("regulation-id") Long regulationId) {
		return regulationServiceInt.getRegulation(regulationId);
	}

	@ResponseBody
	@GetMapping("/get-invoice-regulations/{invoice-id}")
	public List<Regulation> getInvoiceRegulations(@PathVariable("invoice-id") Long invoiceId) {
		return regulationServiceInt.getInvoiceRegulations(invoiceId);
	}

	@ResponseBody
	@GetMapping("/get-periodic-income/{start-date}/{end-date}")
	public double getPeriodicIncomes(@PathVariable(name = "start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
		@PathVariable(name = "end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return regulationServiceInt.getPeriodicIncomes(startDate, endDate);
	}

	@ResponseBody
	@PutMapping("/update-regulation")
	public Regulation updateRegulation(@RequestBody RegulationModel regulationModel) {
		Regulation regulation = new Regulation(regulationModel.getId(), regulationModel.getAmountPaid(), regulationModel.getAmountRemaining());
		return regulationServiceInt.updateRegulation(regulation);
	}

	@ResponseBody
	@DeleteMapping("/delete-regulation/{invoice-id}")
	public void deleteRegulation(@PathVariable("invoice-id") Long invoiceId) {
		regulationServiceInt.deleteRegulation(invoiceId);
	}

}
