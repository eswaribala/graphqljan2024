package com.optum.bankingdgs.dataloaders;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.optum.bankingdgs.models.Customer;
import com.optum.bankingdgs.repositories.CustomerRepository;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name="customers")
public class CustomerDataLoader implements BatchLoader<Long, Customer> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CompletionStage<List<Customer>> load(List<Long> list) {
        return CompletableFuture.supplyAsync(() -> this.customerRepository.findAllById(list));
    }
}
