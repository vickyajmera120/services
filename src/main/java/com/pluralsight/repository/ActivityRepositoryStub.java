package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	
	@Override
	public List<Activity> findByConstraint(ActivitySearch search) {
		
		System.out.println(search.getDurationTo());
		System.out.println(search.getSearchType());
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1= new Activity();
		Activity activity2= new Activity();
		
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		activities.add(activity1);
		
		activity2.setId("5678");
		activity2.setDescription("Running");
		activity2.setDuration(90);
		activities.add(activity2);
		
		return activities;
	}
	
	@Override
	public List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo) {
		//Select * from activities where description in (?,?,?) and duration > durationFrom and duration < durationTo
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1= new Activity();
		Activity activity2= new Activity();
		
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		activities.add(activity1);
		
		activity2.setId("5678");
		activity2.setDescription("Running");
		activity2.setDuration(90);
		activities.add(activity2);
		
		return activities;
	}
	
	@Override
	public void delete(String activityId) {
		//Delete from activity where activityId = ?
		
	}
	
	
	@Override
	public Activity update(Activity activity) {
		//search the database to see if we have an activity with that ID already
		//select * from Activity where id =?
		//if result-size == 0
		//insert into the Activity table
		//else
		//update the activity table
		
		return activity;
		
		
	}
	
	@Override
	public void create(Activity activity) {

		//Should issue a Insert statement to the db
	}
	
	@Override
	public List<Activity> findAllActivities() {
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		
		activities.add(activity2);
		
		return activities;
		
	}
	
	@Override
	public Activity findActivity(String activityId) {
	
		Activity activity = new Activity();
		
		activity.setDescription("Swimming");
		activity.setDuration(55);
		activity.setId("1234");
		
		User user = new User();
		user.setId("5678");
		user.setName("Vicky");
		
		activity.setUser(user);
		
		return activity;
	}

}
