package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities")		//	http://localhost:8080/services/webapi/activities
public class ActivityResource {
	 
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam("activityId") String activityId) {
		System.out.println(activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
	
	
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response update(Activity activity) {
		
		System.out.println(activity.getId());
		
		activity = activityRepository.update(activity);
		
		return Response.ok().entity(activity).build();
	}
	
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Activity createActivity(Activity activity) {
		
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	
	@POST
	@Path("Activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Activity createActivityParmas(MultivaluedMap<String, String> formParams) {
		
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	
	public List<Activity> getAllActivities() {
		System.out.println("this is method in getAllActivities");
		
		return activityRepository.findAllActivities();	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activityId}")	//	http://localhost:8080/services/webapi/activities/1234
							//	Here curly braces means activityId will take the parameter as its value. 
							//	Without curly braces, it should be same as the parameter. Do not act as a variable.
	
	public Response getActivity(@PathParam("activityId") String activityId) {
		
		if(activityId == null || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.findActivity(activityId); 
		
		if(activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(activity).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activityId}/user")	//	http://localhost:8080/services/webapi/activities/1234/user
	
	public User getActivityUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}
}
