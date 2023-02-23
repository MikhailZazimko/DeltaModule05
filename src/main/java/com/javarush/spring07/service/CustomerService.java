package com.javarush.spring07.service;

import com.javarush.spring07.db.CompanyRepository;
import com.javarush.spring07.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CompanyRepository customerRepo;

    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    public void update(Customer customer) {
        customerRepo.save(customer);
    }

}
