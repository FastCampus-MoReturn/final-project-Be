package com.fastcampus.finalprojectbe.openapi.repository;

import com.fastcampus.finalprojectbe.openapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

    Optional<Address> findByLegdongName(String address);

}
