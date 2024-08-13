package aiims.cf.td_api.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Helpdesk {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String helpdeskName;
	private String contactNo;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHelpdeskName() {
		return helpdeskName;
	}
	public void setHelpdeskName(String helpdeskName) {
		this.helpdeskName = helpdeskName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	public Helpdesk(Long id, String helpdeskName, String contactNo, String status) {
		super();
		this.id = id;
		this.helpdeskName = helpdeskName;
		this.contactNo = contactNo;
		this.status = status;
	}
	public Helpdesk() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
