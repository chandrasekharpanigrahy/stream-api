package com.example.stream.api.service.bootstrap;


import com.example.stream.api.service.DealRepo;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackageClasses = DealRepo.class)
public class DealConfiguration extends AbstractR2dbcConfiguration {

    /*@Bean
    DealController controller(DealRepo repository){
        return new DealController(repository);
    }*/

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get("r2dbc:postgresql://postgres:test@localhost:5432/postgres");
        /*    return new PostgresqlConnectionFactory(
                    PostgresqlConnectionConfiguration.builder()
                        .host("localhost").database("postgres")
                        .port(5432)
                        .username("postgres")
                        .password("test")
                        .build());*/
        //return DatabaseClient.create(connFactory);
    }

    /*@Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {

        return DatabaseClient.create(connectionFactory);
    }*/

    /*@Bean
    R2dbcRepositoryFactory factory(DatabaseClient databaseClient) {
        var context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(databaseClient, context);
    }*/

}
