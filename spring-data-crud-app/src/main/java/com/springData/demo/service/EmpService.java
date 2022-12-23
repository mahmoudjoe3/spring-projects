package com.springData.demo.service;

import com.springData.demo.model.dto.EmployeeDTO;
import com.springData.demo.model.entity.Employee;
import com.springData.demo.repository.EmpRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {
    private final EmpRepo empRepo;

    public EmpService(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    public EmployeeDTO getEmployee(Long id){
        Optional<Employee> employee = empRepo.findById(id);
        if(employee.isPresent()){
            return EmployeeDTO.getInstance(employee.get());
        }else return null;
    }
    public List<EmployeeDTO> getEmployees(){
        return EmployeeDTO.getInstances(empRepo.findAll());
    }
    public EmployeeDTO postEmployee(EmployeeDTO employeeDto){
        return EmployeeDTO.getInstance(empRepo.save(Employee.getInstance(employeeDto)));
    }

    /**
     * save the employee if it is not exist and update it if not
     * @Return {@code new object} if update or add new one and {@code newEmployeeDto} if it is not exist and not completed data*/
    public EmployeeDTO putEmployee(Long id, EmployeeDTO newEmployeeDto) {
        Optional<Employee> employee = empRepo.findById(id);
        if(employee.isPresent()){
            //employee id not null and exist in database
            if(newEmployeeDto.getFirsName()!=null)
                employee.get().setFirsName(newEmployeeDto.getFirsName());
            if(newEmployeeDto.getLastName()!=null)
                employee.get().setLastName(newEmployeeDto.getLastName());
            if(newEmployeeDto.getSalary()!=null)
                employee.get().setSalary(newEmployeeDto.getSalary());
            return EmployeeDTO.getInstance(empRepo.save(employee.get()));
        }else if(Employee.getInstance(newEmployeeDto).isPresent()){
            return EmployeeDTO.getInstance(empRepo.save(Employee.getInstance(newEmployeeDto)));
        }
        return newEmployeeDto;
    }

    public boolean deleteEmployee(Long id) {
        if(empRepo.findById(id).isPresent()) {
            empRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public void clear() {
        empRepo.deleteAll();
    }
}
