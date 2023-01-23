package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.Call;

public interface CallRepository extends JpaRepository<Call, Integer> {

    

}
