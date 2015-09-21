package edu.luc.skhan.amehjabeen.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import edu.luc.skhan.amehjabeen.common.Constants;
import edu.luc.skhan.amehjabeen.model.Facility.Facility;
import edu.luc.skhan.amehjabeen.model.Facility.IFacility;
import edu.luc.skhan.amehjabeen.model.Facility.Information;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenance;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenanceRequest;
import edu.luc.skhan.amehjabeen.model.Maintenance.IProblem;
import edu.luc.skhan.amehjabeen.model.Maintenance.Maintenance;
import edu.luc.skhan.amehjabeen.model.Maintenance.MaintenanceRequest;
import edu.luc.skhan.amehjabeen.model.Maintenance.Problem;
import edu.luc.skhan.amehjabeen.model.Use.IInspections;
import edu.luc.skhan.amehjabeen.model.Use.IUse;
import edu.luc.skhan.amehjabeen.model.Use.Inspections;
import edu.luc.skhan.amehjabeen.model.Use.Use;
import edu.luc.skhan.amehjabeen.model.classes.Manager;

public class View {
	public static void main(String args[]){

		Manager manager = new Manager();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		System.out.println(" Adding the first facility....");
		Information information = new Information();
		information.setCapacity(10);
		information.setStatus(Constants.STATUS_AVAILABLE);
		information.setDescription("Hello World");
		
		Facility facility = new Facility("Science Hall",information);
		facility.setFacilityInformation(information);
		facility = (Facility) manager.addNewFacility(facility);
		System.out.println(" Facility added!");
	
		Information information2 = (Information) facility.getFacilityInformation();
		System.out.println("Printing the first facility's information: \n"
				+"Facility Name: "+facility.getName()+"\n"
				+"Total Capacity: "+information2.getCapacity()+"\n"
				+"Available Capacity: "+information2.requestAvailableCapacity(facility)+"\n"
				+"Description: "+information2.getDescription()+"\n"
				+"Status: "+information2.getStatus()+"\n");

		System.out.println(" Adding a second facility....");
		Information information3 = new Information();
		information3.setCapacity(21);
		information3.setStatus(Constants.STATUS_AVAILABLE);
		information3.setDescription("This is our second facility");

		Facility facility2 = new Facility("Math Hall",information3);
		facility2.setFacilityInformation(information3);
		facility2 = (Facility) manager.addNewFacility(facility2);
		System.out.println("Facility added!");
	
		Information information4 = (Information) facility2.getFacilityInformation();
		System.out.println("Printing the second facility's information: \n"
				+"Facility Name: "+facility2.getName()+"\n"
				+"Total Capacity: "+information4.getCapacity()+"\n"
				+"Available Capacity: "+information4.requestAvailableCapacity(facility2)+"\n"
				+"Description: "+information4.getDescription()+"\n"
				+"Status: "+information4.getStatus()+"\n");

		System.out.println("Listing all facilities....");	
		ArrayList<IFacility> list = new ArrayList<IFacility>();
		list = manager.listFacilities();
		if(!list.isEmpty()){
			for(IFacility f:list){
				System.out.println(f.getId()+". "+f.getName());
			}
		}

		System.out.println(" Removing the fist facility....");
		boolean b=manager.removeFaciltiy(facility);
		if(!b){
			System.out.println("The facility could not be removed!");
		}

		System.out.println("Listing all facilities again....");	
		ArrayList<IFacility> list2 = new ArrayList<IFacility>();
		list2 = manager.listFacilities();
		if(!list2.isEmpty()){
			for(IFacility f:list2){
				System.out.println(f.getId()+". "+f.getName());
			}
		}

		System.out.println("Request capacity for the second facility....");	
		int aCapacity = facility2.getFacilityInformation().requestAvailableCapacity(facility2);
		System.out.println("Available Capacity in "+facility2.getName()+" is: "+aCapacity);


		System.out.println("Adding a third facility....");	
		Information information5 = new Information();
		information5.setCapacity(14);
		information5.setStatus(Constants.STATUS_AVAILABLE);
		information5.setDescription("My third facility");

		Facility facility3 = new Facility("Lecture Hall",information);
		facility3.setFacilityInformation(information5);
		facility3 = (Facility) manager.addNewFacility(facility3);
		System.out.println("Facility added!");	
		
		/*-------------------------Maintenance Module-----------------------------------*/
		
		System.out.println("\n Making a maintenance request...");	
		Date requestDate = new Date();
		MaintenanceRequest maintenanceRequest = new MaintenanceRequest(requestDate, facility2);
		manager.makeFacilityMaintenanceRequest(maintenanceRequest);
		
		
		System.out.println("\n List all maintenance requests with their name, id and date...");	
		ArrayList<IMaintenanceRequest> list3 = new ArrayList<IMaintenanceRequest>();
		list3 = manager.listMaintenanceRequests();
		if(!list3.isEmpty()){
			for(IMaintenanceRequest m:list3){
				System.out.println(m.getFacility().getId()+". "+m.getFacility().getName()
						+" Date: "+m.getRequestDate());
			}
		}
		
		System.out.println("\n Scheduling the maintenance request...");	
		System.out.print("\n Enter cost of maintenance: ");
		double cost=0;
		try {
			cost = Double.parseDouble(br.readLine());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
		System.out.print("\nStart Date of maintenance in format --> yyyy-MM-dd HH:mm:ss");
		String input;
		Date startdate = null;
		Date enddate = null;
		try {
			input = br.readLine();
			startdate = sdf.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.print("\nAn estimate of end Date in format --> yyyy-MM-dd HH:mm:ss");
		try {
			input = br.readLine();
			enddate= sdf.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
				
		Maintenance maintenance = new Maintenance(cost, startdate, enddate, facility2);
		manager.scheduleMaintenance(maintenance);
		
		System.out.println("\n List all maintenance requests with their name, id and date...");	
		ArrayList<IMaintenanceRequest> list4 = new ArrayList<IMaintenanceRequest>();
		list4 = manager.listMaintenanceRequests();
		if(!list4.isEmpty()){
			for(IMaintenanceRequest m:list4){
				System.out.println(m.getFacility().getId()+". "+m.getFacility().getName()
						+" Date: "+m.getRequestDate());
			}
		}else{
			System.out.println("List is empty!");
		}
		
		System.out.println("\n List all scheduled maintenance with their name, id , start date, end date, cost...");	
		ArrayList<IMaintenance> list5 = new ArrayList<IMaintenance>();
		list5 = manager.listMaintenance();
		if(!list5.isEmpty()){
			for(IMaintenance m:list5){
				System.out.println(m.getFacility().getId()+". "+m.getFacility().getName()
						+" Start Date: "+m.getStartTime()+" End Date: "+m.getEndTime()
						+" Cost: "+m.getCost() );
			}
		}
		
		System.out.println("\n Calculating DownTime.....");	
		System.out.println(" Downtime: "+maintenance.calcDownTimeForFacility());	
			
		
		System.out.println("\n Calculating Maintenance Cost...");	
		double cost2 = manager.calcMaintenanceCostForFacility(maintenance);
		System.out.println(" Cost: "+cost2);	
		if(cost2 == Constants.DEFAULT_COST){
			System.out.println("Only base charges applied since facility does not have any problems!");
		}
		
		
		System.out.println("\n Adding a problem to the facility 2...");	
		System.out.print("Enter the type of problem: ");
		String type = null;
		try {
			type = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String maintenanceRequestStatus = Constants.MAINTENANCE_NOT_REQUESTED;
		Problem problem = new Problem(type,maintenanceRequestStatus,facility2);
		manager.addFacilityProblem(problem);
		
		System.out.println("\n Adding another problem to the facility 2...");	
		System.out.print("Enter the type of problem: ");
		String type2 = null;
		try {
			type2 = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String maintenanceRequestStatus2 = Constants.MAINTENANCE_NOT_REQUESTED;
		Problem problem2 = new Problem(type2,maintenanceRequestStatus2,facility2);
		manager.addFacilityProblem(problem2);
				
		System.out.println("\n Listing problems for faclity 2...");
		ArrayList<IProblem> list6 = new ArrayList<IProblem>();
		list6= manager.listFacilityProblems(facility2);
		if(!list6.isEmpty()){
			for(IProblem p:list6){
				System.out.println(p.getFacility().getId()+". "+p.getFacility().getName()
						+" Problem Type: "+p.getTypeOfProblem());
			}
		}else{
			System.out.println("List is empty!");
		}
		
		System.out.println("\n Calculating Maintenance Cost for facility 2...");
		double cost3 = manager.calcMaintenanceCostForFacility(maintenance);
		System.out.println(" Cost: "+cost3);	
		if(cost3 == Constants.DEFAULT_COST){
			System.out.println("Only base charges applied since facility does not have any problems!");
		}
		
		
		/*----------------------------------------USe---------------------------------------------*/
		
		System.out.print("\nStart Date of use in format --> yyyy-MM-dd HH:mm:ss");
		String input7;
		Date startdate1 = null;
		Date enddate1 = null;
		try {
			input7 = br.readLine();
			startdate1 = sdf.parse(input7);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.print("\nAn estimate of end Date in format --> yyyy-MM-dd HH:mm:ss");
		try {
			input7 = br.readLine();
			enddate1= sdf.parse(input7);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		IUse use = new Use(facility2,startdate1,enddate1,true);
		
		manager.assignFacilityToUse(use);
		System.out.println("\n Listing use for faclity 2...");
		ArrayList<IUse> list7 = new ArrayList<IUse>();
		list7= manager.listActualUsage(facility2);
		if(!list7.isEmpty()){
			for(IUse p:list7){
				System.out.println(p.getFacility().getId()+". "+p.getFacility().getName()
						+" Start Time: "+p.getStartTime()+" EndTime: "+p.getEndTime()+ " Is in use: "+p.isInUse());
			}
		}else{
			System.out.println("List is empty!");
		}
		
		System.out.println("Is in use: "+manager.isInUseDuringInterval(facility2, startdate1, enddate1));
		
		System.out.println("Usage: "+manager.calcUsageRate(facility2));
		
		manager.vacateFacility(facility2);
		
		System.out.println("Add inspections");
		IInspections inspection = new Inspections(startdate,null,facility3);
		manager.addInspections(inspection);
				
		System.out.println("\n Listing inspections for faclity 2...");
		ArrayList<Inspections> list9 = new ArrayList<Inspections>();
		list9= manager.listInspections();
		if(!list9.isEmpty()){
			for(IInspections p:list9){
				System.out.println(p.getFacility().getId()+". "+p.getFacility().getName()
						+" Date: "+p.getInspectionDate());
			}
		}else{
			System.out.println("List is empty!");
		}
		
	}
 
 
}
