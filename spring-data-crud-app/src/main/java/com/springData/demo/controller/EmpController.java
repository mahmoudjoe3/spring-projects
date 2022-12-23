package com.springData.demo.controller;

import com.springData.demo.model.dto.EmployeeDTO;
import com.springData.demo.service.EmpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmpController {
    private final EmpService empService;
    public EmpController(EmpService empService) {
        this.empService = empService;
    }
    @GetMapping("/get-all-emp")
    public List<EmployeeDTO> getEmployees(){
        return empService.getEmployees();
    }
    @GetMapping("/get-emp")
    public EmployeeDTO getEmployee(@RequestParam Long id){
        return empService.getEmployee(id);
    }
    @PostMapping("/post-emp")
    public EmployeeDTO setEmployee(@RequestBody EmployeeDTO employee){
        return empService.postEmployee(employee);
    }
    @PutMapping("/put-emp")
    public EmployeeDTO updateEmployee(@RequestParam Long id,@RequestBody EmployeeDTO employee){
        return empService.putEmployee(id,employee);
    }
    @DeleteMapping("/delete-emp")
    public boolean deleteEmployee(@RequestParam Long id){
        return empService.deleteEmployee(id);
    }
    @DeleteMapping("/delete-all-emp")
    public void deleteAllEmployees(){
        empService.clear();
    }
}
