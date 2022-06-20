package com.tpe.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter   // lombok (kod kalabalığını önlüyor, getter ve setter ları otomatik ekliyor, dependency kısmına lombok jar dosyaları eklendı .)
@Setter   // lombok
@AllArgsConstructor  //lombok
@NoArgsConstructor  //lombok  
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // id değişkene primitive tip vermememizin sebebi : int vermiş olsaydık ve değeri 
	// set etmediğimizde int in defaultun değeri "0" oldugu için "0" set edecekti, ben bunu istemediğim 
	// için non-primitive tip olan Long atadım, böylelikle ,deger set edilmediğinde null ataması yapılacak.
	
	@NotNull(message="firs name con not be null")   // bu değere null girilemez. dependency de eklenen 
														//validation (pom.xml eklenen) jar dosyası sayesinde eklenebiliyor
	@NotBlank(message="FirstNAme can not be white space ")  // (validation sayesinde (pom.xml eklenen)) boşluk girilemez, 
															//parametre olarak hata mesajı yazılabiliyor
	@Size(min=1, max =100, message="First Name '${validatedValue}' must be between {min and {max} characters long") // belirli size da 
	// olmasını sart kosuyor
	// ${validatedValue} ---> bu değere girilen değeri getirir
	@Column(nullable = false, length = 100)
	private String firstName;
	
	@Column(nullable = false, length = 100)
	private String lastName;
	
	private Integer grade;
	
	@Column(nullable = false, length = 100, unique = true)
	@Email(message="Provide valid email") // validatiton dependency sayesinde (pom.xml eklenen) ...
	private String email;
	
	@Column(length = 14)
	@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number") 
	// bu patternler internetten bulunabiliyor --->  (555)-555-5555  gibi
	private String phoneNumber;
	
	
	private LocalDateTime createDate = LocalDateTime.now();

	
	
	
	
	
	
	
	
	
	
	
	
}