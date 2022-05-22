package logic;

/**
 * StudentBean has attributes that matches most of the Student Survey Form fields. 
 */
public class StudentBean {
	private String studentID;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String telephone;
	private String email;
	private String url;
	private String InterestedArea;
	private String highestDiff;
	private String dos;
	
	public String getStudentID() {return this.studentID;}
	public void setStudentID(String str) {this.studentID = str;}
	
	public String getName() {return this.name;}
	public void setName(String str) {this.name = str;}
	
	public String getAddress() {return this.address;}
	public void setAddress(String str) {this.address = str;}
	
	public String getCity() {return this.city;}
	public void setCity(String str) {this.city = str;}
	
	public String getState() {return this.state;}
	public void setState(String str) {this.state = str;}
	
	public String getZipcode() {return this.zipcode;}
	public void setZipcode(String str) {this.zipcode = str;}
	
	public String getTelephone() {return this.telephone;}
	public void setTelephone(String str) {this.telephone = str;}
	
	public String getEmail() {return this.email;}
	public void setEmail(String str) {this.email = str;}
	
	public String getURL() {return this.url;}
	public void setURL(String str) {this.url = str;}
	
	public String getInterestedArea() {return this.InterestedArea;}
	public void setInterestedArea(String str) {this.InterestedArea = str;}
	
	public String gethighestDiff() {return this.highestDiff;}
	public void sethighestDiff(String str) {this.highestDiff = str;}
	
	public String getDOS() {return this.dos;}
	public void setDOS(String str) {this.dos = str;}
}
