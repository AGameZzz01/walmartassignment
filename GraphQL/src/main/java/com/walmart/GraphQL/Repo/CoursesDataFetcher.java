package com.walmart.GraphQL.Repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.walmart.GraphQL.Models.CourseDetails;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Repository
public class CoursesDataFetcher implements DataFetcher<List<CourseDetails>> {
	
	public List<CourseDetails> get(DataFetchingEnvironment dataFetchingEnviroment) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(1);
		courseDetails.setTitle("Math");
		courseDetails.setInfo("Learning how to use variables in math.");
		
		List<CourseDetails> courseDetailList = new ArrayList<>();
		courseDetailList.add(courseDetails);
		
		return courseDetailList;
	}

}
