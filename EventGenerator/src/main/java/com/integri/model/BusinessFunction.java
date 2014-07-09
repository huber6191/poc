package com.integri.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="BUSINESS_FUNCTION")
public class BusinessFunction   implements Serializable{
  

	private long entryId;
	
	private String entryName;
	
	private String entryDetails;
	
	private Date insertedDate;
	
	   
	@Id
	@Column(name="ENTRY_ID")
	@GeneratedValue(generator="hibseq")
	@GenericGenerator(name="hibseq", strategy = "seqhilo",
	    parameters = {
	        @Parameter(name="max_lo", value = "5"),
	        @Parameter(name="sequence", value="BUSINESS_FUNCTION_ENTRY_ID")
	    }
	)	 
	public long getEntryId() {
		return entryId;
	}
	
	 
 
	@Column(name="ENTRY_NAME")
	public String getEntryName() {
		return entryName;
	}
	
	
	@Column(name="ENTRY_DETAILS")
	public String getEntryDetails() {
		return entryDetails;
	}
	
	@Column(name="INSERTED_DATE")
	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setEntryDetails(String entryDetails) {
		this.entryDetails = entryDetails;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
 
	
	
	
}
