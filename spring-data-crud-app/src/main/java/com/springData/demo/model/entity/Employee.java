package com.springData.demo.model.entity;

import com.springData.demo.model.dto.EmployeeDTO;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employees")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "emp_first_name")
    private String firsName;
    @Column(name = "emp_last_name")
    private String lastName;
    @Column(name = "emp_salary")
    private Double salary;

    public static Employee getInstance(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId())
                .firsName(dto.getFirsName())
                .lastName(dto.getLastName())
                .salary(dto.getSalary())
                .build();
    }

    /**
     * @return {@code false} if any value is null, otherwise {@code false}
     */
    public boolean isPresent() {
        return this.firsName != null && this.lastName != null && this.salary != null;
    }
}
