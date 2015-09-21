package edu.luc.skhan.amehjabeen.model.Facility;


import edu.luc.skhan.amehjabeen.data.DatabaseMethods;

public class Facility implements IFacility {
	private String name;
	private int Id=0;
	private IInformation iInformation;
	
	DatabaseMethods databaseMethods = DatabaseMethods.getInstance();
	
	public Facility(String name, IInformation iInformation) {
		this.setName(name);
		this.iInformation = iInformation;
	}
	
	public Facility() {
		// empty constructor
	}

	@Override
	public int getId() {
		return Id;
	}

	@Override
	public void setId(int id) {
		Id = id;
	}
	
	@Override
	public IInformation getFacilityInformation(){
		if (iInformation!=null){
			return iInformation;
		}
		else
			return null;
	}

	@Override
	public void setFacilityInformation(IInformation iInformation){
		this.iInformation = iInformation;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
}
