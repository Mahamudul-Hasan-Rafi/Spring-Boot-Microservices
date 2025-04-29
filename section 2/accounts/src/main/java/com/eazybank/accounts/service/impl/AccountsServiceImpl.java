package com.eazybank.accounts.service.impl;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Accounts;
import com.eazybank.accounts.entity.Customer;
import com.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomRepository;
import com.eazybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    public AccountsRepository accountsRepository;
    public CustomRepository customRepository;

    @Override
    public void createAccount(CustomerDto customerDto){
        Customer customer = CustomerMapper.mapToCustomerEntity(customerDto, new Customer());

        Optional<Customer> customerOptional = customRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customerDto.getMobileNumber());
        }

        customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDate.now());

        Customer savedcustomer = customRepository.save(customer);
        if (savedcustomer != null) {
            accountsRepository.save(createNewAccount(savedcustomer));
            //System.out.println("Customer created successfully with ID: " + savedcustomer.getCustomerId());
        } else {
            System.out.println("Failed to create customer.");
        }
    }

    private Accounts createNewAccount(Customer savedcustomer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(savedcustomer.getCustomerId());

        long randomAccountNumber = 1000000000L+ (long) (Math.random() * 9000000000L);
        accounts.setAccountNumber(randomAccountNumber);

        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);

        return accounts;
    }
}
