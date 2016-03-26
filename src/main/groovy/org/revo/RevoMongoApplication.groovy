package org.revo

import org.revo.Repository.ChildRepository
import org.revo.Repository.PersonRepository
import org.revo.Repository.SuggestedChildRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.hateoas.config.EnableEntityLinks

import static org.revo.Util.InitData.fun1Test

@EnableMongoAuditing
@EnableEntityLinks
@SpringBootApplication
class RevoMongoApplication {
    static void main(String[] args) {
        SpringApplication.run RevoMongoApplication, args

    }

    @Bean
    CommandLineRunner commandLineRunner(MongoOperations mongoOperations,
                                        PersonRepository personRepository,
                                        ChildRepository childRepository,
                                        SuggestedChildRepository suggestedChildRepository) {
        { args ->
            fun1Test(mongoOperations, personRepository, childRepository, suggestedChildRepository)
        }
    }
}