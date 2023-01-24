package com.api.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.enums.Priority;
import com.api.helpdesk.domains.enums.Profile;
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

  public void setTables() {
    Technician technician1 = new Technician(
        null, "Francisco Stenico", "12345678900", "francisco@teste.com", "1234Teste!");
    technician1.addProfile(Profile.ADMIN);

    Client client1 = new Client(
        null, "Carolina Maronese", "98765432100", "carolina@teste.com", "1234Teste!");
    client1.addProfile(Profile.CLIENT);

    Call call1 = new Call(null, Priority.MEDIUM_PRIORITY, Status.IN_PROGRESS, "Chamado - 01",
        "Observações sobre o chamado", technician1, client1);

    techRepo.saveAll(Arrays.asList(technician1));
    clientRepo.saveAll(Arrays.asList(client1));
    callRepo.saveAll(Arrays.asList(call1));
  }

}
