package com.optum.bankingapi.configurations;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlTypeConfiguration {

    @Bean
    public GraphQLScalarType getLong(){
        return  ExtendedScalars.GraphQLLong;
    }

}
