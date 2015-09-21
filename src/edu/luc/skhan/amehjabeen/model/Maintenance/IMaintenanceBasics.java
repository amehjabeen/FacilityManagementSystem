package edu.luc.skhan.amehjabeen.model.Maintenance;

import java.util.ArrayList;

public interface IMaintenanceBasics {
	public double calcMaintenanceCostForFacility(IMaintenance maintenance);
	public ArrayList<IMaintenance> listMaintenance();
	public void scheduleMaintenance(IMaintenance maintenance);

}
