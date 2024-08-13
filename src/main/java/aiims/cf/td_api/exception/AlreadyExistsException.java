package aiims.cf.td_api.exception;

public class AlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	long fieldValue;
	
	String fields;
	
	public AlreadyExistsException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s already exists with %s : %s",resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	public AlreadyExistsException(String resourceName, String fieldName, String fields) {
		super(String.format("%s already exists with %s : %s",resourceName, fieldName, fields));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fields = fields;
	}


	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public long getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}


	public String getFields() {
		return fields;
	}


	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
}
