package io.github.vort2014.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 22.09.2017.
 */
@Entity
@Table(name = "COMPANY")
public class CompanyEntity {

    @Id
    @Column(name = "companyId")
    private Long companyId;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "employeeSize")
    private Long employeeSize;
    @Column(name = "phone")
    private String phone;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEmployeeSize() {
        return employeeSize;
    }

    public void setEmployeeSize(Long employeeSize) {
        this.employeeSize = employeeSize;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
