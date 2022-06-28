package com.tpe.dto;

import java.time.LocalDateTime;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.tpe.domain.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok (kod kalabalığını önlüyor, getter ve setter ları otomatik ekliyor,
		// dependency kısmına lombok jar dosyaları eklendı .)
@Setter // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok

public class StudentDTO {

	private Long id;
	@NotNull(message = "firs name con not be null")
	@NotBlank(message = "FirstNAme can not be white space ")
	@Size(min = 1, max = 100, message = "First Name '${validatedValue}' must be between {min and {max} characters long")

	private String firstName;

	@NotNull(message = "last name con not be null")
	@NotBlank(message = "LastName can not be white space ")
	@Size(min = 1, max = 100, message = "Last Name '${validatedValue}' must be between {min and {max} characters long")
	private String lastName;

	private Integer grade;

	@Email(message = "Provide valid email") // validatiton dependency sayesinde (pom.xml eklenen) ...
	private String email;

	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
	// bu patternler internetten bulunabiliyor ---> (555)-555-5555 gibi
	private String phoneNumber;

	private LocalDateTime createDate = LocalDateTime.now();

	public StudentDTO(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.grade = student.getGrade();
		this.email = student.getEmail();
		this.phoneNumber = student.getPhoneNumber();
		this.createDate = student.getCreateDate();
	}
	
	
	
	
	
	

}