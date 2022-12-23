package com.springData.demo.model.dto;

import com.springData.demo.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firsName;
    private String lastName;
    private Double salary;

    public static EmployeeDTO getInstance(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .firsName(entity.getFirsName())
                .lastName("********")
                .salary(entity.getSalary())
                .build();
    }

    public static List<EmployeeDTO> getInstances(List<Employee> entities) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        entities.forEach(entity -> employeeDTOList.add(getInstance(entity)));
        return employeeDTOList;
    }


}