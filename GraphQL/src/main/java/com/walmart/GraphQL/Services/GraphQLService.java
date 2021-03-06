package com.walmart.GraphQL.Services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.walmart.GraphQL.Repo.CoursesDataFetcher;
import com.walmart.GraphQL.Repo.StudentsDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	private GraphQL graphql;
	
	@Autowired
	StudentsDataFetcher studentsFetcher;
	
	@Autowired
	CoursesDataFetcher coursesFetcher;
	
	@Value("schema.graphql")
	private ClassPathResource classPathLoader;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		InputStream st = classPathLoader.getInputStream();
		Reader targetReader = new InputStreamReader(st);
		
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(targetReader);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		
		GraphQLSchema graphqlSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		graphql = GraphQL.newGraphQL(graphqlSchema).build();
	}
	
	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring->typeWiring
						.dataFetcher("students", studentsFetcher))
				.type("Query", typeWiring->typeWiring
						.dataFetcher("courses", coursesFetcher))
				.build();
	}
	
	public GraphQL initiateGraphQL() { return graphql; }

}
