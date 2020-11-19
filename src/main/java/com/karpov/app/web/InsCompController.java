package com.karpov.app.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.karpov.app.exception.RecordNotFoundException;
import com.karpov.app.model.InsCompEntity;
import com.karpov.app.service.InsCompService;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class InsCompController 
{
	@Autowired
	InsCompService service;

	@RequestMapping
	public String getAllCompany(Model model)                             //Вывести список всех компаний
	{
		List<InsCompEntity> list = service.getAllCompany();
		model.addAttribute("companies", list);                                   
		return "list-company";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})                      //Редактировать данные по id
	public String editCompanyById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
 	{
		if (id.isPresent()) {
			InsCompEntity entity = service.getCompanyById(id.get());
			model.addAttribute("company", entity);                                
		} else {
			model.addAttribute("company", new InsCompEntity());                    
		}
		return "add-edit-company";
	}
	
	@RequestMapping(path = "/delete/{id}")                                        //Удалить компанию по id
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteCompanyById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createCompany", method = RequestMethod.POST)            //Внести новую компанию
	public String createOrUpdateCompany(InsCompEntity company) 
	{
		service.createOrUpdateCompany(company);
		return "redirect:/";
	}
          
	@RequestMapping(path = {"/findCompany"})                                 //перейти на форму поиска
	public String findCompany(Model model) 
	{
                 model.addAttribute("company", new InsCompEntity()); 
		return "find-company";
	}
         
        @RequestMapping(path = { "/find/ogrn/","/find/ogrn/{ogrn}"})                       //поиск по ОГРН
	public String findCompanyByOgrn(Model model, @PathVariable("ogrn") Optional<String> ogrn) 
 	{
                List<InsCompEntity> list=new ArrayList<InsCompEntity>();
		if (ogrn.isPresent()) {                 
 			InsCompEntity entity = service.getCompanyByOgrn(ogrn.get());
			if (entity!=null){                                    
                            list.add(entity);                                 
                        }
		}                                                              
                model.addAttribute("companies", list); 
                return "list-company";
	}
        
        @RequestMapping(path = {  "/find/inn/","/find/inn/{inn}"})                          //Поиск по ИНН
	public String findCompanyByInn(Model model, @PathVariable("inn") Optional<String> inn) 
	{
                List<InsCompEntity> list=new ArrayList<InsCompEntity>();
		if (inn.isPresent()) {
			InsCompEntity entity = service.getCompanyByInn(inn.get());
	                if (entity!=null){    
                            list.add(entity);
                        }    
		} 
                model.addAttribute("companies", list); 
                return "list-company";
		
	}
        
        @RequestMapping(path={"/company","/company/{stroka}"})                       //Поиск подстроки в названии
	public String getAllCompanyByName(Model model, @PathVariable("stroka") Optional<String> stroka) 
	{
              List<InsCompEntity> list;
              if (stroka.isPresent()) {
 		 list = service.getAllCompanyByName(stroka.get().toUpperCase());
              } else{
                 list = new ArrayList<InsCompEntity>();
              } 
               model.addAttribute("companies", list);
               return "list-company";
	}
        
        
}
