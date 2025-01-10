package com.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Main.Model.AllUsers;

public interface UserRepository extends JpaRepository<AllUsers, Integer> {

	 @Query("SELECT u FROM AllUsers u WHERE u.name = :name")
	    AllUsers findUserByName(@Param("name") String name);

}
