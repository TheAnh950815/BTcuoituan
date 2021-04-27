package controller;

import model.Employee;
import model.EmployeeForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.EmployeeImpl;


import java.io.File;
import java.io.IOException;
import java.util.List;



@RequestMapping("/")
@Controller
public class EmployeeController {
    private final EmployeeImpl iEmployee = new EmployeeImpl();


    @GetMapping("")
    public String index(Model model) {
        List<Employee> employees = iEmployee.findAll();
        model.addAttribute("employees", employees);
        return "/view";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String fileUpload;

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute EmployeeForm employeeForm) {
        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Employee employee = new Employee(employeeForm.getId(), employeeForm.getName(),
                employeeForm.getDate(), fileName, employeeForm.isGender());
        iEmployee.save(employee);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employeeForm", employeeForm);
        modelAndView.addObject("message", "Created new employee successfully !");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getFormEdit(@PathVariable Long id) {
        Employee employee = EmployeeImpl.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("editForm", employee);
        return modelAndView;
    }

    @PostMapping("/editCustomer")
    public String editCustomer(@ModelAttribute("editForm") EmployeeForm employee, Model model) {
//        if (employee.getName() == null || employee.getName().trim().equals("") && employee.getDate().trim().equals("")) {
//            model.addAttribute("status", "invalid  try again");
//            return "/edit";
//        }

//        iEmployee.update(employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
       iEmployee.delete(id);
        return "redirect:/";
    }
}