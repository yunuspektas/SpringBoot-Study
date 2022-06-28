package com.tpe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;

@Repository

// CRUDRepository  --> crud operasyonları için kullanılıyor
// JpaRepository   --> hem crud hem de PagingAndSortingRepository barındırıyor
// PagingAndSortingRepository ---> Entityler üzerinde Paging(Server side paging ?) ve Sorting yapmamıza yarıyor
public interface StudentRepository extends JpaRepository<Student, Long> {
	// normalde bu metod içine repository meyodlarının eklenmesi gerekiyor , update, delete , create gibi ama 
	//yukardaki "extends JpaRepository" den dolayı gerek kalmıyor
	
	@Query("SELECT s from Student s WHERE s.grade=:pGrade")
	List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
	
	@Query(value="SELECT * from Student s WHERE s.grade=:pGrade",nativeQuery = true)
	List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);
	
	@Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")
	Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
	
	
	
	//aşağıda isimlendrime yaparken kural : findBy yazdıktan sonra devamında filed ismini giriyoruz : findByLastName
	List<Student> findByLastName(String lastName);
	
	Boolean existsByEmail(String email) throws ConflictException ;
	
	
	

}
