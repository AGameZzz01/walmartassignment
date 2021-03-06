package com.walmart.GraphQL.Repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.walmart.GraphQL.Models.StudentDetails;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Repository
public class StudentsDataFetcher implements DataFetcher<List<StudentDetails>> {

	public List<StudentDetails> get(DataFetchingEnvironment dataFetchingEnviroment) {
		StudentDetails studentDetails = new StudentDetails();

		studentDetails.setId(1);
		studentDetails.setFullName("Austin");
		studentDetails.setCourseId(1);

		List<StudentDetails> studentDetailsList = new ArrayList<>();
		studentDetailsList.add(studentDetails);

		return studentDetailsList;
	}

}
