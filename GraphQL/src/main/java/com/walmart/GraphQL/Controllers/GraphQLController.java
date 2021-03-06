package com.walmart.GraphQL.Controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.walmart.GraphQL.Services.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class GraphQLController {
	
	@Autowired
	GraphQLService graphqlService;
	
	@RequestMapping(value="/studentsData")
	public String getPreAuthDecisionData(@RequestBody String query) throws JSONException, IOException {
		ExecutionResult execute = graphqlService.initiateGraphQL().execute(query);
		Map<String, String> obj = (Map<String, String>) execute.getData();
		JSONObject jsonObject = new JSONObject(obj);
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/courseData")
	public String getDecisionData(@RequestBody String query) throws JSONException, IOException {
		ExecutionResult execute = graphqlService.initiateGraphQL().execute(query);
		Map<String, String> obj = (Map<String, String>) execute.getData();
		JSONObject jsonObject = new JSONObject(obj);
		return jsonObject.toString();
	}

}
