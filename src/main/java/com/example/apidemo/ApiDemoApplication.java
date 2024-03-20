package com.example.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@CrossOrigin()
	@PostMapping(value = "/numeral-conv", consumes = "application/json", produces = "application/json")
	public String numeralConverter(@RequestBody Integer num) {

		if (num <= 0 || num >= 5000) {
			return "Cannot convert that number";
		}

		String numeral = "";
		String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] thousands = { "", "M", "MM", "MMM", "MMMM" };

		switch (num.toString().length()) {
			case 1:
				numeral = ones[Character.getNumericValue(num.toString().charAt(0))];
				break;
			case 2:
				numeral = tens[Character.getNumericValue(num.toString().charAt(0))];
				numeral += ones[Character.getNumericValue(num.toString().charAt(1))];
				break;
			case 3:
				numeral = hundreds[Character.getNumericValue(num.toString().charAt(0))];
				numeral += tens[Character.getNumericValue(num.toString().charAt(1))];
				numeral += ones[Character.getNumericValue(num.toString().charAt(2))];
				break;
			case 4:
				numeral = thousands[Character.getNumericValue(num.toString().charAt(0))];
				numeral += hundreds[Character.getNumericValue(num.toString().charAt(1))];
				numeral += tens[Character.getNumericValue(num.toString().charAt(2))];
				numeral += ones[Character.getNumericValue(num.toString().charAt(3))];
				break;
		}

		return numeral;

	}

}