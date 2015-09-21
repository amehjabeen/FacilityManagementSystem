package edu.luc.skhan.amehjabeen.model.classes;

import java.util.ArrayList;
import java.util.Date;

import edu.luc.skhan.amehjabeen.data.DatabaseMethods;
import edu.luc.skhan.amehjabeen.data.IDataModel;
import edu.luc.skhan.amehjabeen.model.Facility.IFacility;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenance;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenanceRequest;
import edu.luc.skhan.amehjabeen.model.Maintenance.IProblem;
import edu.luc.skhan.amehjabeen.model.Use.IInspections;
import edu.luc.skhan.amehjabeen.model.Use.IUse;
import edu.luc.skhan.amehjabeen.model.Use.Inspections;

public class Manager implements IDataModel{
	
	DatabaseMethods databaseMethods = DatabaseMethods.getInstance();

	@Override
	public IFacility addNewFacility(IFacility facility) {
		IFacility newFacility;
		newFacility = databaseMethods.addNewFacility(facility);
		newFacility.getFacilityInformation().addFacilityDetail(newFacility);
		return newFacility;
	}

	@Override
	public boolean removeFaciltiy(IFacility facility) {
		databaseMethods.removeFacilityDetail(facility);
		return databaseMethods.removeFaciltiy(facility);		
	}

	@Override
	public ArrayList<IFacility> listFacilities() {
		return databaseMethods.listFacilities();
	}
	

	@Override
	public void makeFacilityMaintenanceRequest(IMaintenanceRequest maintenanceRequest) {
		databaseMethods.makeFacilityMaintenanceRequest(maintenanceRequest);		
	}

	@Override
	public ArrayList<IMaintenanceRequest> listMaintenanceRequests() {
		return databaseMethods.listMaintenanceRequests();
	}
	

	@Override
	public ArrayList<IMaintenance> listMaintenance() {
		return databaseMethods.listMaintenance();
	}

	@Override
	public void scheduleMaintenance(IMaintenance maintenance) {
		databaseMethods.scheduleMaintenance(maintenance);	
	}

	@Override
	public double calcMaintenanceCostForFacility(IMaintenance maintenance) {
		return databaseMethods.calcMaintenanceCostForFacility(maintenance);				
	}

	@Override
	public ArrayList<IProblem> listFacilityProblems(IFacility facility) {
		return databaseMethods.listFacilityProblems(facility);
	}
	
	@Override
	public double calcProblemRateForFacility(IFacility facility) {
		return databaseMethods.calcProblemRateForFacility(facility);
	}

	@Override
	public void addFacilityProblem(IProblem problem) {
		databaseMethods.addFacilityProblem(problem);
		
	}

	@Override
	public void addFacilityDetail(IFacility facility) {
		databaseMethods.addFacilityDetail(facility);
		
	}

	@Override
	public void removeFacilityDetail(IFacility facility) {
		databaseMethods.removeFacilityDetail(facility);
		
	}

	@Override
	public int requestAvailableCapacity(IFacility facility) {
		return databaseMethods.requestAvailableCapacity(facility);
	}

	@Override
	public boolean isInUseDuringInterval(IFacility facility,Date startTime, Date endTime) {
		return databaseMethods.isInUseDuringInterval(facility, startTime, endTime);
	}

	@Override
	public void assignFacilityToUse(IUse use) {
		databaseMethods.assignFacilityToUse(use);
		
	}

	@Override
	public void vacateFacility(IFacility facility) {
		databaseMethods.vacateFacility(facility);
		
	}

	@Override
	public ArrayList<Inspections> listInspections() {
		return databaseMethods.listInspections();
	}

	@Override
	public ArrayList<IUse> listActualUsage(IFacility facility) {
		return databaseMethods.listActualUsage(facility);
	}

	@Override
	public double calcUsageRate(IFacility facility) {
		return databaseMethods.calcUsageRate(facility);
	}

	@Override
	public void addInspections(IInspections inspections) {
		databaseMethods.addInspections(inspections);
		
	}

	@Override
	public void changeIsInUseStatus(IUse use) {
		databaseMethods.changeIsInUseStatus(use);
		
	}
	
}
