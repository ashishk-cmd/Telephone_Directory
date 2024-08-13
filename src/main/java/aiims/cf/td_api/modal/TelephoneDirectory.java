package aiims.cf.td_api.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class TelephoneDirectory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telephone_directory_seq")
	@SequenceGenerator(name = "telephone_directory_seq", sequenceName = "telephone_directory_seq", allocationSize = 1, initialValue = 3000)
	private Long id;
    
    private String fname;
    private String mname;
    private String lname;
    private String fullName;
    private String design;
    private String dpt;
    private String level1;
    private String level2;
    private String intOfficial1;
    private String intOfficial2;
    private String intOfficial3;
    private String dirOfficial1;
    private String dirOfficial2;
    private String dirOfficial3;
    private String dirOfficial4;
    private String fax1;
    private String fax2;
    private String mobOfficial1;
    private String mobOfficial2;
    private String mobOfficial3;
    private String mobOfficial4;
    private String mobOfficial5;
    private String offAddress;
    private String intResid1;
    private String intResid2;
    private String dirResid1;
    private String dirResid2;
    private String dirResid3;
    private String mobPersonal1;
    private String mobPersonal2;
    private String residence;
    private String email1;
    private String email2;
    @ManyToOne
    private SubCategory subCategory;
    private String forUser;
    private String status = "ACTIVE";
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getIntOfficial1() {
		return intOfficial1;
	}
	public void setIntOfficial1(String intOfficial1) {
		this.intOfficial1 = intOfficial1;
	}
	public String getIntOfficial2() {
		return intOfficial2;
	}
	public void setIntOfficial2(String intOfficial2) {
		this.intOfficial2 = intOfficial2;
	}
	public String getIntOfficial3() {
		return intOfficial3;
	}
	public void setIntOfficial3(String intOfficial3) {
		this.intOfficial3 = intOfficial3;
	}
	public String getDirOfficial1() {
		return dirOfficial1;
	}
	public void setDirOfficial1(String dirOfficial1) {
		this.dirOfficial1 = dirOfficial1;
	}
	public String getDirOfficial2() {
		return dirOfficial2;
	}
	public void setDirOfficial2(String dirOfficial2) {
		this.dirOfficial2 = dirOfficial2;
	}
	public String getDirOfficial3() {
		return dirOfficial3;
	}
	public void setDirOfficial3(String dirOfficial3) {
		this.dirOfficial3 = dirOfficial3;
	}
	public String getDirOfficial4() {
		return dirOfficial4;
	}
	public void setDirOfficial4(String dirOfficial4) {
		this.dirOfficial4 = dirOfficial4;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getMobOfficial1() {
		return mobOfficial1;
	}
	public void setMobOfficial1(String mobOfficial1) {
		this.mobOfficial1 = mobOfficial1;
	}
	public String getMobOfficial2() {
		return mobOfficial2;
	}
	public void setMobOfficial2(String mobOfficial2) {
		this.mobOfficial2 = mobOfficial2;
	}
	public String getMobOfficial3() {
		return mobOfficial3;
	}
	public void setMobOfficial3(String mobOfficial3) {
		this.mobOfficial3 = mobOfficial3;
	}
	public String getMobOfficial4() {
		return mobOfficial4;
	}
	public void setMobOfficial4(String mobOfficial4) {
		this.mobOfficial4 = mobOfficial4;
	}
	public String getMobOfficial5() {
		return mobOfficial5;
	}
	public void setMobOfficial5(String mobOfficial5) {
		this.mobOfficial5 = mobOfficial5;
	}
	public String getOffAddress() {
		return offAddress;
	}
	public void setOffAddress(String offAddress) {
		this.offAddress = offAddress;
	}
	public String getIntResid1() {
		return intResid1;
	}
	public void setIntResid1(String intResid1) {
		this.intResid1 = intResid1;
	}
	public String getIntResid2() {
		return intResid2;
	}
	public void setIntResid2(String intResid2) {
		this.intResid2 = intResid2;
	}
	public String getDirResid1() {
		return dirResid1;
	}
	public void setDirResid1(String dirResid1) {
		this.dirResid1 = dirResid1;
	}
	public String getDirResid2() {
		return dirResid2;
	}
	public void setDirResid2(String dirResid2) {
		this.dirResid2 = dirResid2;
	}
	public String getDirResid3() {
		return dirResid3;
	}
	public void setDirResid3(String dirResid3) {
		this.dirResid3 = dirResid3;
	}
	public String getMobPersonal1() {
		return mobPersonal1;
	}
	public void setMobPersonal1(String mobPersonal1) {
		this.mobPersonal1 = mobPersonal1;
	}
	public String getMobPersonal2() {
		return mobPersonal2;
	}
	public void setMobPersonal2(String mobPersonal2) {
		this.mobPersonal2 = mobPersonal2;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public String getForUser() {
		return forUser;
	}
	public void setForUser(String forUser) {
		this.forUser = forUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public TelephoneDirectory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TelephoneDirectory(Long id, String fname, String mname, String lname, String fullName, String design,
			String dpt, String level1, String level2, String intOfficial1, String intOfficial2, String intOfficial3,
			String dirOfficial1, String dirOfficial2, String dirOfficial3, String dirOfficial4, String fax1,
			String fax2, String mobOfficial1, String mobOfficial2, String mobOfficial3, String mobOfficial4,
			String mobOfficial5, String offAddress, String intResid1, String intResid2, String dirResid1,
			String dirResid2, String dirResid3, String mobPersonal1, String mobPersonal2, String residence,
			String email1, String email2, SubCategory subCategory, String forUser, String status) {
		super();
		this.id = id;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.fullName = fullName;
		this.design = design;
		this.dpt = dpt;
		this.level1 = level1;
		this.level2 = level2;
		this.intOfficial1 = intOfficial1;
		this.intOfficial2 = intOfficial2;
		this.intOfficial3 = intOfficial3;
		this.dirOfficial1 = dirOfficial1;
		this.dirOfficial2 = dirOfficial2;
		this.dirOfficial3 = dirOfficial3;
		this.dirOfficial4 = dirOfficial4;
		this.fax1 = fax1;
		this.fax2 = fax2;
		this.mobOfficial1 = mobOfficial1;
		this.mobOfficial2 = mobOfficial2;
		this.mobOfficial3 = mobOfficial3;
		this.mobOfficial4 = mobOfficial4;
		this.mobOfficial5 = mobOfficial5;
		this.offAddress = offAddress;
		this.intResid1 = intResid1;
		this.intResid2 = intResid2;
		this.dirResid1 = dirResid1;
		this.dirResid2 = dirResid2;
		this.dirResid3 = dirResid3;
		this.mobPersonal1 = mobPersonal1;
		this.mobPersonal2 = mobPersonal2;
		this.residence = residence;
		this.email1 = email1;
		this.email2 = email2;
		this.subCategory = subCategory;
		this.forUser = forUser;
		this.status = status;
	}
	
	
    
    

    
}
