package com.tpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;

@Service 
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	

	@Autowired 
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> findStudents(String lastName) {
		
		return studentRepository.findByLastName(lastName);
	} 

	@Override
	public Student findStudent(Long id) throws ResourceNotFoundException {

		return studentRepository.findById(id).
		orElseThrow(()-> new ResourceNotFoundException("Student not found with id : " + id));
		// orElseThrow --> optional işlemdir, Java da nullPointer exeptiondan kaçmak için optional kullanılır
	}

	@Override
	public void createStudent(Student student) {
		
		//	create öncesi email bilgisi unique olması gerektiği için , 
		//	önceki kayıtlar da aynı email var mı kpntrol ediliyor
		if(studentRepository.existsByEmail(student.getEmail())) {
			throw new ConflictException("Email already exist ! ");
		}
		
		studentRepository.save(student);
		
		
	}

	@Override
	public void updateStudent(Long id, StudentDTO studentDTO) {
		
		 Boolean emailExist= studentRepository.existsByEmail(studentDTO.getEmail());
	        Student student= findStudent(id);
	        
	        if(emailExist&&!studentDTO.getEmail().equals(student.getEmail())) {
	            throw new ConflictException("Email already exist");
	        }
	        
	        //Student updateStudent=new Student();
	        
	        student.setFirstName(studentDTO.getFirstName());
	        student.setLastName(studentDTO.getLastName());
	        student.setGrade(studentDTO.getGrade());
	        student.setEmail(studentDTO.getEmail());
	        student.setPhoneNumber(studentDTO.getPhoneNumber());
	        studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = findStudent(id) ;  // varsa student dönecek yoksa findStudent metodunun 
											//içine eklediğimiz exeption fırlatacak
		studentRepository.delete(student);
	}

	@Override
	public Page<Student> getAllWithPage(Pageable pageable) {
		
		return studentRepository.findAll(pageable);
	}

	@Override
	public List<Student> findAllEqualsGrade(Integer grade) {
		
		return studentRepository.findAllEqualsGrade(grade);
	}

}
