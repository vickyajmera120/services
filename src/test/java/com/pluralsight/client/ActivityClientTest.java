package com.pluralsight.client;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.ActivitySearchType;

public class ActivityClientTest {
	
	@Test
	public void testSearchObject() {
		ActivitySearchClient client = new ActivitySearchClient();
		
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("Biking");
		searchValues.add("Cycling");
		
		
		ActivitySearch search = new ActivitySearch();
		
		search.setDescriptions(searchValues);
		search.setDurationFrom(30);
		search.setDurationTo(55);
		search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);
		
		
		List<Activity> activities = client.search(search);
		
		System.out.println(activities);
		
		assertNotNull(activities);
	}
	
	
	@Test
	public void testSearch() {
		ActivitySearchClient client = new ActivitySearchClient();
		
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		
		searchValues.add("Swimming");
		searchValues.add("Running");
		
		String secondParam = "durationFrom";
		String thirdParam = "durationTo";
		
		int durationFrom = 55;
		int durationTo = 90;
		
		
		List<Activity> activities = client.search(param, searchValues, secondParam, durationFrom, thirdParam, durationTo);
		
		System.out.println(activities);
		
		assertNotNull(activities);
	}
	
	@Test
	public void testDelete() {
		ActivityClient client = new ActivityClient();
		
		client.delete("8765");
	}
	
	@Test
	public void testPut() {
		Activity activity = new Activity();
		activity.setDescription("Swimming");
		activity.setDuration(55);
		
		ActivityClient client = new ActivityClient();
		
		activity = client.update(activity);
		
		assertNotNull(activity);
	}
	
	
	@Test
	public void testCreate() {
		ActivityClient client = new ActivityClient();
		
		Activity activity = new Activity();
		activity.setDescription("Swimming");
		activity.setDuration(55);
		
		activity = client.create(activity);
		
		assertNotNull(activity);		
	}
	
	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient();
		
		Activity activity = client.get("1234");
		
		System.out.println(activity);
		
		assertNotNull(activity);
		
	}
	
	@Test
	public void testGetList() {
		ActivityClient client = new ActivityClient();
		
		List<Activity> activities = client.get();
		
		System.out.println(activities);
		
		assertNotNull(activities);
		
	}
	
	@Test(expected=RuntimeException.class)
	
	public void testGetWithBadRequest() {
		ActivityClient client = new ActivityClient();
		
		client.get("123");
	}
}
