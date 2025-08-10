package com.eazybank.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditOverride(forClass=BaseEntity.class, isAudited=false)
@AuditTable(value = "accounts_audit")
@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    @Column(name = "account_number")
    @Id
    @Setter(AccessLevel.NONE)
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer cannot be empty")
    private Customer customer;

    public void setAccountNumber(Long accountNumber) {
        if (this.accountNumber == null) {
            this.accountNumber=accountNumber;
        }

    }
}
