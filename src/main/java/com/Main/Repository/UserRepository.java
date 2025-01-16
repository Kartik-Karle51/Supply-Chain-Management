package com.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.Main.Model.AllUsers;

public interface UserRepository extends JpaRepository<AllUsers, Integer> {


	    UserDetails findByName(String name);

}
