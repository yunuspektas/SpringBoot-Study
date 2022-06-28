package com.tpe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;

@Repository

// CRUDRepository  --> crud operasyonları için kullanılıyor
// JpaRepository   --> hem crud hem de PagingAndSortingRepository barındırıyor
// PagingAndSortingRepository ---> Entityler üzerinde Paging(Server side paging ?) ve Sorting yapmamıza yarıyor
public interface StudentRepository extends JpaRepository<Student, Long> {
	// normalde bu metod içine repository meyodlarının eklenmesi gerekiyor , update, delete , create gibi ama 
	//yukardaki "extends JpaRepository" den dolayı gerek kalmıyor
	
	//aşağıda isimlendrime yaparken kural : findBy yazdıktan sonra devamında filed ismini giriyoruz : findByLastName
	List<Student> findByLastName(String lastName);
	
	Boolean existsByEmail(String email) throws ConflictException ;
	
	
	@Query("SELECT s from Student s WHERE s.grade=:pGrade")
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);

}
