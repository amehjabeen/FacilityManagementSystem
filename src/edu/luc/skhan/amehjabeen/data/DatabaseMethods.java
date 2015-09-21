package edu.luc.skhan.amehjabeen.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


public class DatabaseMethods implements IDataModel{

	private static DatabaseMethods instance;

	public static DatabaseMethods getInstance(){
		if(instance == null){
			instance = new DatabaseMethods();
		}
		return instance;
	}

	public IFacility createFacilityObject(int id){
		IFacility facility = new Facility();
		try {
			Connection connection = DBHelper.getConnection();
			Statement statement = connection.createStatement();
			Information information = new Information();
			String sqlQuery = "select f.FacilityName,i.TotalCapacity,"
					+ "i.Description,i.Status from Information i,Facility f "
					+ " where i.Id=f.Id and f.Id = "+id;
			ResultSet rs = statement.executeQuery(sqlQuery);
			facility.setId(id);		
			while(rs.next()){
				facility.setName(rs.getString("FacilityName"));
				information.setCapacity(rs.getInt("TotalCapacity"));
				information.setDescription(rs.getString("Description"));
				information.setStatus(rs.getString("Status"));
			}
			facility.setFacilityInformation(information);
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facility;
	}

	/*--------------------------------Facility Module------------------------------*/
	@Override
	public IFacility addNewFacility(IFacility facility) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "insert into Facility (FacilityName) values ('"+facility.getName()+"')";
			statement.executeUpdate(sqlQuery);
			Statement statement2 = connection.createStatement();
			sqlQuery = "select last_insert_id() as list_id from Facility";
			ResultSet resultSet2 = statement2.executeQuery(sqlQuery);
			int id =0;
			while(resultSet2.next()){
				id=resultSet2.getInt("list_id");
			}
			facility.setId(id);
			resultSet2.close();
			statement2.close();
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facility;		
	}

	@Override
	public boolean removeFaciltiy(IFacility facility) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "delete from Facility where FacilityName='"+facility.getName()+"'";
			boolean resultSet = statement.execute(sqlQuery);
			if(!resultSet){
				System.out.println("Facility deletion was successful!");
			}
			statement.close();
			DBHelper.closeConnection(connection);
			return true;

		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}

	}

	@Override
	public ArrayList<IFacility> listFacilities() {
		ArrayList<IFacility> facilities = new ArrayList<IFacility>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select Id from Facility";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				IFacility facility = createFacilityObject(resultSet.getInt("Id"));
				facilities.add(facility);
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facilities;
	}

	@Override
	public void addFacilityDetail(IFacility facility) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			int capacity = facility.getFacilityInformation().getCapacity();
			String sqlQuery = "insert into Information (AvailableCapacity,TotalCapacity,"
					+ "Description,Status,Id)"
					+ " values ("+capacity+","+capacity+",'"+facility.getFacilityInformation().
					getDescription()+"','"+facility.getFacilityInformation().getStatus()+"',"+
					facility.getId()+")";
			statement.execute(sqlQuery);
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void removeFacilityDetail(IFacility facility) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "delete from Information where Id="+facility.getId();
			boolean resultSet = statement.execute(sqlQuery);
			if(!resultSet){
				System.out.println("Information deletion was successful!");
			}
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int requestAvailableCapacity(IFacility facility) {
		int capacity =0;
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select AvailableCapacity from Information where Id="+facility.getId();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				capacity = resultSet.getInt("AvailableCapacity");
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return capacity;
	}

	/*--------------------------------Maintenance Module------------------------------*/
	@Override
	public ArrayList<IProblem> listFacilityProblems(IFacility facility) {
		ArrayList<IProblem> list = new ArrayList<IProblem>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select * from Problem where Id="+facility.getId();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				IProblem problem = new Problem(resultSet.getString("ProblemType"),
						resultSet.getString("Status"),facility);
				list.add(problem);				
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<IMaintenance> listMaintenance() {
		ArrayList<IMaintenance> list = new ArrayList<IMaintenance>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select * from Maintenance";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				int id=resultSet.getInt("Id");
				IFacility facility = createFacilityObject(id);
				IMaintenance maintenance = new Maintenance(resultSet.getDouble("Cost"),
						(resultSet.getDate("StartTime")),
						(resultSet.getDate("EndTime")),facility);
				list.add(maintenance);				
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void scheduleMaintenance(IMaintenance maintenance) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			Statement statement2 = connection.createStatement();
			String sqlQuery = "delete from MaintenanceRequest where Id="+
					maintenance.getFacility().getId();
			String sqlQuery2 = "insert into Maintenance(Cost,Id,StartTime,EndTime) values("+
					maintenance.getCost()+","+maintenance.getFacility().getId()+",'"+					
					sdf.format(maintenance.getStartTime())+"','"+
					sdf.format(maintenance.getEndTime())+"')";
			statement.executeUpdate(sqlQuery);
			System.out.println("Deleted request number: "+maintenance.getFacility().getId());
			statement2.executeUpdate(sqlQuery2);
			statement.close();			
			statement2.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double calcMaintenanceCostForFacility(IMaintenance maintenance) {
		return (Constants.DEFAULT_COST+ (listFacilityProblems(maintenance.getFacility()).size()*
				maintenance.getCost()));
	}

	@Override
	public void makeFacilityMaintenanceRequest(IMaintenanceRequest maintenanceRequest) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "insert into MaintenanceRequest(Date,Id,Status) values('"+
					sdf.format(maintenanceRequest.getRequestDate())+"',"+
					maintenanceRequest.getFacility().getId()
					+",'"+maintenanceRequest.getInspectionStatus()+"')";
			statement.executeUpdate(sqlQuery);		
			statement.close();
			DBHelper.closeConnection(connection);
			System.out.println("Maintenance Request made!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<IMaintenanceRequest> listMaintenanceRequests() {
		ArrayList<IMaintenanceRequest> list = new ArrayList<IMaintenanceRequest>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select * from MaintenanceRequest";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				int id=resultSet.getInt("Id");
				IFacility facility = createFacilityObject(id);
				IMaintenanceRequest maintenanceRequest = new MaintenanceRequest(
						(resultSet.getDate("Date")),facility);
				list.add(maintenanceRequest);				
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addFacilityProblem(IProblem problem) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "insert into Problem(ProblemType,Status,Id) values('"+
					problem.getTypeOfProblem()+"','"+
					problem.getMaintenanceRequestStatus()+"','"+
					problem.getFacility().getId()+"')";
			statement.executeUpdate(sqlQuery);		
			statement.close();
			DBHelper.closeConnection(connection);
			System.out.println("Problem added!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double calcProblemRateForFacility(IFacility facility) {
		int numberOfTimesUsed = listActualUsage(facility).size();	
		return  listFacilityProblems(facility).size()/numberOfTimesUsed;
	}


	/*--------------------------------Use Module------------------------------*/
	@Override
	public boolean isInUseDuringInterval(IFacility facility, Date startTime, Date endTime) {
		ArrayList<IUse> list = listActualUsage(facility);
		for(IUse use:list){
			if(startTime.getTime() <= use.getEndTime().getTime() && use.getStartTime().getTime() <= endTime.getTime()){
				return true;
			}

		}
		return false;
	}


	@Override
	public void assignFacilityToUse(IUse use) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "insert into useLog (startTime, endTime, isInUse, Id) values('"+
					sdf.format(use.getStartTime())+"','"+
					sdf.format(use.getEndTime())+"',"+use.isInUse()+","+ use.getFacility().getId()+")";

			statement.executeUpdate(sqlQuery);		
			statement.close();
			DBHelper.closeConnection(connection);
			System.out.println("Use added!");

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void vacateFacility(IFacility facility) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "delete from Use where Id="+facility.getId();
			boolean resultSet = statement.execute(sqlQuery);
			if(!resultSet){
				System.out.println("Facility vacation was successful!");
			}
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Inspections> listInspections() {
		ArrayList<Inspections> list = new ArrayList<Inspections>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select * from Insections";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){
				int id=resultSet.getInt("Id");
				IFacility facility = createFacilityObject(id);
				Inspections inspections = new Inspections(resultSet.getDate("Date"),
						resultSet.getString("ProblemStatus"),facility);

				list.add(inspections);				
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<IUse> listActualUsage(IFacility facility) {
		ArrayList<IUse> list = new ArrayList<IUse>();
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "select * from UseLog where Id="+facility.getId();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()){

				IUse use = new Use(facility,resultSet.getDate("StartTime"),
						resultSet.getDate("EndTime"),resultSet.getBoolean("IsInUse"));
				list.add(use);				
			}
			resultSet.close();			
			statement.close();
			DBHelper.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public double calcUsageRate(IFacility facility) {
		double usageRate = 0;
		ArrayList<IUse> list = listActualUsage(facility);
		long totalTime = 0;
		for(IUse use:list){
			totalTime += (use.getEndTime().getTime() - use.getStartTime().getTime());
		}
		//total use time in seconds
		totalTime /= 1000;
		//number of times the facility was used
		int n = list.size();
		usageRate = totalTime/n;
		return usageRate;
	}

	@Override
	public void addInspections(IInspections inspections) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "insert into Inspections(ProblemStatus,Id,Date) values('"+
					inspections.getProblemStatus()+"',"+
					inspections.getFacility().getId()+",'"+sdf.format(inspections.getInspectionDate())+"')";
			statement.executeUpdate(sqlQuery);		
			statement.close();
			DBHelper.closeConnection(connection);
			System.out.println("Inspection added!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void changeIsInUseStatus(IUse use) {
		try {
			Connection connection =  DBHelper.getConnection();
			Statement statement =connection.createStatement();
			String sqlQuery = "update UseLog set isInUse = "+!(use.isInUse())+" where Id= "+use.getFacility().getId();
			statement.executeUpdate(sqlQuery);		
			statement.close();
			DBHelper.closeConnection(connection);
			System.out.println("Inspection added!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
