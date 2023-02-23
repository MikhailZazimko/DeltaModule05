package com.javarush.spring07.db;

import com.javarush.spring07.Context;
import com.javarush.spring07.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@Context
public class CompanyRepositoryTest {

    @Autowired
    public CompanyRepository repository;

    @Test
    void getAllOrderedById() {
        Sort.TypedSort<Long> idSorter = Sort.sort(Customer.class).by(Customer::getId);
        List<Customer> allOrderedById = repository.getOrderedBy(idSorter);
        System.out.println(allOrderedById);
    }
}