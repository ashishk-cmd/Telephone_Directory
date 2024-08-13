package aiims.cf.td_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.cf.td_api.modal.Helpdesk;
import aiims.cf.td_api.service.PublicService;



@RestController
@RequestMapping("/public")
@CrossOrigin("*")
public class PublicController {

	@Autowired
	private PublicService publicservice;

	@GetMapping("/get-allhelpdesk-numbers")
	public ResponseEntity<Object> getAllHelpdeskNumbers() {
		System.out.println("dhbibgvu hiohiohoigved hvi dai");
	    try {
	        List<Helpdesk> hepdesknumbers = this.publicservice.getAllHelpdeskNumbers();
	        if (hepdesknumbers != null && !hepdesknumbers.isEmpty()) {
	            return ResponseEntity.ok(hepdesknumbers);              
	        } else {
	            return ResponseEntity.notFound().build();  // Return 404 Not Found
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<Object>(e, HttpStatus.EXPECTATION_FAILED);
	    }
	}
}