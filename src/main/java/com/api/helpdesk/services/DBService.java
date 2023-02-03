package com.api.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.enums.Priority;
import com.api.helpdesk.domains.enums.Status;
import com.api.helpdesk.repositories.CallRepository;
import com.api.helpdesk.repositories.ClientRepository;
import com.api.helpdesk.repositories.TechnicianRepository;

@Service
public class DBService {

  @Autowired
  private TechnicianRepository techRepo;
  @Autowired
  private ClientRepository clientRepo;
  @Autowired
  private CallRepository callRepo;
  @Autowired
  BCryptPasswordEncoder encoder;

  public void setTables() {
    Technician technician1 = new Technician(
        null, "Technician 1", "583.333.950-23", "t1@test.com", encoder.encode("1234Test!"));

    Technician technician2 = new Technician(
        null, "Technician 2", "253.009.590-65", "t2@test.com", encoder.encode("1234Test!"));

    Client client1 = new Client(
        null, "Client 1", "813.649.850-47", "c1@test.com", encoder.encode("1234Test!"));

    Call call1 = new Call(null, Priority.MEDIUM_PRIORITY, Status.IN_PROGRESS, "Chamado - 01",
        "Observations about the call", technician1, client1);

    techRepo.saveAll(Arrays.asList(technician1, technician2));
    clientRepo.saveAll(Arrays.asList(client1));
    callRepo.saveAll(Arrays.asList(call1));
  }

}
