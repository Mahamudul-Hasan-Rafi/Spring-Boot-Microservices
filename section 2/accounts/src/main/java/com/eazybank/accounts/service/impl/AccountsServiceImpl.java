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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
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

//        customer.setCreatedBy("Anonymous");
//        customer.setCreatedAt(LocalDate.now());

        Customer savedcustomer = customRepository.save(customer);
        if (savedcustomer != null) {
            accountsRepository.save(createNewAccount(savedcustomer));
            //System.out.println("Customer created successfully with ID: " + savedcustomer.getCustomerId());
        } else {
            System.out.println("Failed to create customer.");
        }
    }

    @Override
    public CustomerAccountDto fetchAccount(String mobileNumber) {
        Customer customer = customRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );

        CustomerAccountDto customerAccountDto = new CustomerAccountDto();

        customerAccountDto.setName(customer.getName());
        customerAccountDto.setEmail(customer.getEmail());
        customerAccountDto.setMobileNumber(customer.getMobileNumber());
        customerAccountDto.setAccountsDto(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDto()));

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

            Customer customer = customRepository.findById(updatedAccounts.getCustomerId()).orElseThrow(
                    ()->new ResourceNotFoundException("Customer", "customerId", String.valueOf(updatedAccounts.getCustomerId()))
            );

            if(customer!=null){
                customer.setName(customerAccountDto.getName());
                customer.setEmail(customerAccountDto.getEmail());
                customer.setMobileNumber(customerAccountDto.getMobileNumber());

                customRepository.save(customer);
            }

            return updatedAccounts != null;
        }

        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customRepository.deleteById(customer.getCustomerId());

        return true;
    }

    private Accounts createNewAccount(Customer savedcustomer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(savedcustomer.getCustomerId());

        long randomAccountNumber = 1000000000L+ (long) (Math.random() * 9000000000L);
        accounts.setAccountNumber(randomAccountNumber);

        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
//        accounts.setCreatedAt(savedcustomer.getCreatedAt());
//        accounts.setCreatedBy(savedcustomer.getCreatedBy());

        return accounts;
    }
}
