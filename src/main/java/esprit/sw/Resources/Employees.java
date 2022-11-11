package esprit.sw.Resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import esprit.sw.annotation.Secured;
import esprit.sw.entities.Employe;
import io.swagger.annotations.Api;

@Secured
@Path("employes")
@Api
public class Employees {
	
	public static List<Employe> listemp = new ArrayList<>();
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addEmploye (Employe e) {
		
		listemp.add(e);
		return "Add Saccessfull";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  List<Employe> displayEmployeesList () {
		return listemp;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employe e) {
		for (Employe emp : listemp) {
			if (emp.getCin() == e.getCin()) {
				listemp.set(listemp.indexOf(emp), e);
		return Response.status(Status.OK).entity("update successful").build();
			}
		}

		return Response.status(Status.NOT_FOUND).entity("update unsuccessful").build();
		
	}
	
	/*@PUT
	@Consumes(MediaType.APPLICATION_JSON)	

	public Response updateEmploye(Employe e) {
		int index= listemp.indexOf(e);
		if (index!=-1) {
			listemp.set(index, e);
			return Response.status(Status.OK).entity("update successful").build();
			
		}
		return Response.status(Status.NOT_FOUND).entity("update unsuccessful").build();
	
	}*/
	
	@GET
	@Path("{ci}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employe searchEmployee(@PathParam("ci")int ci) {
		Employe e = new Employe();
		for (Employe employe : listemp) {
			if (employe.getCin()==ci) {
				e=employe;
			}
				
		}
		return e;
	}
	
	
	@DELETE
	@Path("{x}")
	public boolean delete (@PathParam("x")int cin) {
		for (Employe employe : listemp) {
			if(employe.getCin()==cin) {
				listemp.remove(listemp.indexOf(employe));
				return true;
			}
		}
		return false;
	}

}
