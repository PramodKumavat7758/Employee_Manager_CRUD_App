package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	/*private List<Employee> theEmployees;

	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
*/

	private EmployeeService employeeService;
	public EmployeeController(EmployeeService theEmployeeService){
		employeeService
				= theEmployeeService;

	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		//Create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get all from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

		// Getting employee
		Employee theEmployee = employeeService.findById(theId);

		//set employee in the model
		theModel.addAttribute("employee",theEmployee);

		//send over the form
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		//delete the employee
		employeeService.deleteById(theId);
		//redirect to main page
		return "redirect:/employees/list";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//Save employee
		employeeService.save(theEmployee);

		//use a redirect to prevent duplicate
		return "redirect:/employees/list";

	}
}









