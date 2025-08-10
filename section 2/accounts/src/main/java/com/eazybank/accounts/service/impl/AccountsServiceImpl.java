package com.eazybank.accounts.service.impl;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dto.AccountsDto;
import com.eazybank.accounts.dto.CustomerAccountDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Accounts;
import com.eazybank.accounts.entity.Customer;
import com.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountsMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomRepository;
import com.eazybank.accounts.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    public AccountsRepository accountsRepository;
    public CustomRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

    @Override
    @Transactional
    public void createAccount(CustomerDto customerDto){
        try {
            customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                    .ifPresent(customer -> {
                        throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists.");
                    });

            Customer savedCustomer = customerRepository.save(
                    CustomerMapper.mapToCustomerEntity(customerDto, new Customer())
            );

            Accounts newAccount = createNewAccount(savedCustomer);
            accountsRepository.save(newAccount);
        } catch (Exception e) {
            log.error("Failed to create account for customer: {}", e.getMessage());
            throw new RuntimeException("Failed to create account for customer: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerAccountDto fetchAccount(String mobileNumber) {

        List<Accounts> accounts =  accountsRepository.findByCustomerMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Account", "customerId", String.valueOf(mobileNumber))
        );

        CustomerAccountDto customerAccountDto = new CustomerAccountDto();

        for(Accounts accounts1: accounts) {

            customerAccountDto.setName(accounts1.getCustomer().getName());
            customerAccountDto.setEmail(accounts1.getCustomer().getEmail());
            customerAccountDto.setMobileNumber(accounts1.getCustomer().getMobileNumber());

            customerAccountDto.setAccountsDto(AccountsMapper.mapToAccountsDTO(accounts1, new AccountsDto()));


        }

        return customerAccountDto;
    }

    @Override
    public boolean updateAccount(CustomerAccountDto customerAccountDto) {
        AccountsDto accountsDto = customerAccountDto.getAccountsDto();


        if(accountsDto!=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account", "accountNumber", String.valueOf(accountsDto.getAccountNumber()))
            );

            Accounts accs = AccountsMapper.mapToAccountsEntity(accountsDto,accounts);

            Accounts updatedAccounts = accountsRepository.save(accs);

            Customer customer = customerRepository.findById(updatedAccounts.getCustomer().getCustomerId()).orElseThrow(
                    ()->new ResourceNotFoundException("Customer", "customerId", String.valueOf(updatedAccounts.getCustomer().getCustomerId()))
            );

            if(customer!=null){
                customer.setName(customerAccountDto.getName());
                customer.setEmail(customerAccountDto.getEmail());
                customer.setMobileNumber(customerAccountDto.getMobileNumber());

                customerRepository.save(customer);
            }

            return updatedAccounts != null;
        }

        return false;
    }

    @Transactional
    @Override
    public boolean deleteAccount(String mobileNumber) {
        try{
            Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
            );

            List<Accounts> accounts = accountsRepository.findByCustomerMobileNumber(mobileNumber).orElseThrow(
                    ()->new ResourceNotFoundException("Account", "customerId", String.valueOf(mobileNumber))
            );

            for(Accounts accounts1: accounts){
                accountsRepository.deleteById(accounts1.getAccountNumber());
            }

            customerRepository.deleteById(customer.getCustomerId());

            return true;
        }
        catch (Exception e) {
            log.error("Failed to delete account for mobile number {}: {}", mobileNumber, e.getMessage());
            throw new RuntimeException("Failed to delete account for mobile number " + mobileNumber + ": " + e.getMessage(), e);
        }

    }

    private Accounts createNewAccount(Customer savedcustomer) {
        Accounts accounts = new Accounts();
        accounts.setCustomer(savedcustomer);

        long randomAccountNumber = 1000000000L+ (long) (Math.random() * 9000000000L);
        accounts.setAccountNumber(randomAccountNumber);

        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);

        return accounts;
    }
}
