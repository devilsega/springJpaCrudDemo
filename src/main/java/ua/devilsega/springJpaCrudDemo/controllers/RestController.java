package ua.devilsega.springJpaCrudDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.devilsega.springJpaCrudDemo.persistence.model.Company;
import ua.devilsega.springJpaCrudDemo.persistence.model.Developer;
import ua.devilsega.springJpaCrudDemo.services.CompanyCrud;
import ua.devilsega.springJpaCrudDemo.services.DeveloperCrud;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private CompanyCrud companyCrud;
    @Autowired
    private DeveloperCrud developerCrud;

    @RequestMapping(method= RequestMethod.GET, value="/api/get/{type}")
    public ResponseEntity get(
            @PathVariable(value = "type") String type,
            @RequestParam(value = "id") long id
    ){
        switch (type){
            case "company":
                Company company = companyCrud.getCompany(id);
                if (company!=null){
                    return  ResponseEntity.status(HttpStatus.OK).body(company);
                }
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");

            case "developer":{
                Developer developer = developerCrud.getDeveloper(id);
                if (developer!=null){
                    return  ResponseEntity.status(HttpStatus.OK).body(developer);
                }
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
            }
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/getall/{type}")
    public ResponseEntity getAll(
            @PathVariable(value = "type") String type
    ){
                switch (type){
                    case "company":
                        return ResponseEntity.status(HttpStatus.OK).body(companyCrud.getAll());
                    case "developer":
                        return ResponseEntity.status(HttpStatus.OK).body(developerCrud.getAll());
                    default:
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
                }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/set/company")
    public ResponseEntity setCompany(
            @RequestParam(value = "name") String name
    ){
                if(companyCrud.createNewCompany(name)){
                    return ResponseEntity.status(HttpStatus.OK).body("null");
                }
                else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
                }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/api/set/company/developer")
    public ResponseEntity setCompanyDevelopers(
            @RequestParam(value = "companyid") long companyId,
            @RequestParam(value = "developerid") long developerId
            ){
                if(companyCrud.setDeveloper(companyId,developerId)){
                    return ResponseEntity.status(HttpStatus.OK).body("null");
                }
                else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
                }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/set/developer")
    public ResponseEntity setDeveloper(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "salary") double salary,
            @RequestParam(value = "city") String city
    ){
            if(developerCrud.createNewDeveloper(name, salary, city)){
                return ResponseEntity.status(HttpStatus.OK).body("null");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
            }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/set/developer/company")
    public ResponseEntity setDeveloperCompanies(
            @RequestParam(value = "companyid") long companyId,
            @RequestParam(value = "developerid") long developerId
    ){
            if(developerCrud.setCompany(developerId,companyId)){
                return ResponseEntity.status(HttpStatus.OK).body("null");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
            }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/delete/{type}")
    public ResponseEntity delete(
            @PathVariable(value = "type") String type,
            @RequestParam(value = "id") long id
    ){
                switch (type){
                    case "company":{
                        if(companyCrud.deleteCompany(id))return ResponseEntity.status(HttpStatus.OK).body("null");
                        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
                    }
                    case "developer":{
                        if(developerCrud.deleteDeveloper(id))return ResponseEntity.status(HttpStatus.OK).body("null");
                        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
                    }
                    default: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
                }
    }
}
