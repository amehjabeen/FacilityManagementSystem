package edu.luc.skhan.amehjabeen.model.Use;

import java.util.Date;

import edu.luc.skhan.amehjabeen.common.Constants;
import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public class Inspections implements IInspections {
	
	private Date inspectionDate;
	private String ProblemStatus = Constants.INSPECTION_PENDING;
	private IFacility facility;
	
	public Inspections(Date inspectionDate, String problemStatus,IFacility facility){
		this.setInspectionDate(inspectionDate);
		this.setProblemStatus(problemStatus);
		this.setFacility(facility);
	}

	@Override
	public void setFacility(IFacility facility) {
		this.facility = facility;
		
	}

	@Override
	public IFacility getFacility() {
		return facility;
	}

	@Override
	public Date getInspectionDate() {
		return inspectionDate;
	}

	@Override
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Override
	public String getProblemStatus() {
		return ProblemStatus;
	}

	@Override
	public void setProblemStatus(String problemStatus) {
		ProblemStatus = problemStatus;
	}
	
	
}
