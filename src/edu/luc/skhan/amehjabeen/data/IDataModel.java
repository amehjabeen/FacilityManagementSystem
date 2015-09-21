package edu.luc.skhan.amehjabeen.data;

import edu.luc.skhan.amehjabeen.model.Facility.IFacilityBasics;
import edu.luc.skhan.amehjabeen.model.Facility.IInformationBasics;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenanceBasics;
import edu.luc.skhan.amehjabeen.model.Maintenance.IMaintenanceRequestBasics;
import edu.luc.skhan.amehjabeen.model.Maintenance.IProblemBasics;
import edu.luc.skhan.amehjabeen.model.Use.IInspectionsBasic;
import edu.luc.skhan.amehjabeen.model.Use.IUseBasic;

public interface IDataModel extends IFacilityBasics,IInformationBasics,
									IProblemBasics,IMaintenanceBasics,
									IMaintenanceRequestBasics,IUseBasic,IInspectionsBasic {

}
