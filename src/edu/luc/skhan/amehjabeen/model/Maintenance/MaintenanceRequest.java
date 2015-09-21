package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.Date;

import edu.luc.skhan.amehjabeen.common.Constants;
import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public class MaintenanceRequest implements IMaintenanceRequest {
	
	private Date requestDate;
	private String inspectionStatus = Constants.INSPECTION_PENDING;
	private IFacility facility;
	
	
	public MaintenanceRequest(Date date,IFacility facility) {
		this.setRequestDate(date);
		this.setFacility(facility);		
	}

	@Override
	public String getInspectionStatus() {
		return inspectionStatus;
	}

	@Override
	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	@Override
	public Date getRequestDate() {
		return requestDate;
	}

	@Override
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public IFacility getFacility() {
		return facility;
	}

	@Override
	public void setFacility(IFacility facility) {
		this.facility = facility;
	}

}
