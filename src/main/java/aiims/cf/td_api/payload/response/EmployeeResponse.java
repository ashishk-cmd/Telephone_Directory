package aiims.cf.td_api.payload.response;

public class EmployeeResponse {

	private String employeeId;
	private String name;
	private String mobileNumber;
	private String emailAddress;
	private String designation;
	private String department;
	private String employeeGroup;
	private String establishment;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmployeeGroup() {
		return employeeGroup;
	}
	public void setEmployeeGroup(String employeeGroup) {
		this.employeeGroup = employeeGroup;
	}
	public String getEstablishment() {
		return establishment;
	}
	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}
	public EmployeeResponse(String employeeId, String name, String designation, String department, String mobileNumber,
			String emailAddress, String employeeGroup, String establishment) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.designation = designation;
		this.department = department;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.employeeGroup = employeeGroup;
		this.establishment = establishment;
	}
	public EmployeeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
