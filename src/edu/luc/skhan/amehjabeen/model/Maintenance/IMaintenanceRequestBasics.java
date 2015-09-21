package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.ArrayList;

public interface IMaintenanceRequestBasics {
	public void makeFacilityMaintenanceRequest(IMaintenanceRequest maintenanceRequest);
	public ArrayList<IMaintenanceRequest> listMaintenanceRequests();
}
