package edu.luc.skhan.amehjabeen.model.Maintenance;

import edu.luc.skhan.amehjabeen.common.Constants;
import edu.luc.skhan.amehjabeen.data.DatabaseMethods;
import edu.luc.skhan.amehjabeen.model.Facility.IFacility;

public class Problem implements IProblem {
	
	private String typeOfProblem;
	private String maintenanceRequestStatus = Constants.MAINTENANCE_NOT_REQUESTED;
	private IFacility facility;
	
	DatabaseMethods databaseMethods = DatabaseMethods.getInstance();
	
	public Problem(String typeOfProblem, String maintenanceRequestStatus,IFacility facility) {
		this.setTypeOfProblem(typeOfProblem);
		this.setMaintenanceRequestStatus(maintenanceRequestStatus);
		this.setFacility(facility);		
	}

	
	@Override
	public String getTypeOfProblem() {
		return typeOfProblem;
	}

	@Override
	public void setTypeOfProblem(String typeOfProblem) {
		this.typeOfProblem = typeOfProblem;
	}

	@Override
	public String getMaintenanceRequestStatus() {
		return maintenanceRequestStatus;
	}

	@Override
	public void setMaintenanceRequestStatus(String maintenanceRequestStatus) {
		this.maintenanceRequestStatus = maintenanceRequestStatus;
	}

	@Override
	public void setFacility(IFacility facility) {
		this.facility=facility;		
	}

	@Override
	public IFacility getFacility() {
		return facility;
	}

}
