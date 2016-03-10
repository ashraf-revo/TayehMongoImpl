package org.revo

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Repository.ChildRepository
import org.revo.Repository.PersonRepository
import org.revo.Repository.SuggestedChildRepository
import org.revo.domain.Child
import org.revo.domain.Person
import org.revo.domain.SuggestedChild
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RevoMongoApplication {
    static void main(String[] args) {
        SpringApplication.run RevoMongoApplication, args

    }

//    @Bean
    CommandLineRunner commandLineRunner(MongoOperations mongoOperations,
                                        PersonRepository personRepository,
                                        ChildRepository childRepository,
                                        SuggestedChildRepository suggestedChildRepository) {
        { args ->
            mongoOperations.dropCollection(Child.class)
            mongoOperations.dropCollection(Person.class)
            mongoOperations.dropCollection(SuggestedChild.class)
            String p1 = "ashraf1"
            String p2 = "ashraf2"
            personRepository.save([
                    new Person(name: p1, email: p1, password: p1),
                    new Person(name: p2, email: p2, password: p2)
            ]).eachWithIndex { it, i ->
                String ci = it.name + "ChildView" + i
                it.children = [childRepository.save(new Child(name: ci, person: it, image: "https://www.facebook.com/" + ci))]
                personRepository.save(it)
            }

            List<Child> all = childRepository.findAll()
            all.collect { out ->
                Child child = all.find { it.id != out.id }
                SuggestedChild suggestedChild = suggestedChildRepository.save(new SuggestedChild(child: out, suggested: child, confidence: 90))
                out.suggestedChild = [suggestedChild]
                childRepository.save(out)
            }

        }
    }
}

@RestController
class Main {
    @Autowired
    PersonRepository personRepository
    @Autowired
    ChildRepository childRepository
    @Autowired
    SuggestedChildRepository suggestedChildRepository

    @JsonView(RevoView.PersonAndItsChild.class)
    @RequestMapping(value = "/")
    def index() {
        personRepository.findAll()
    }

    @JsonView(RevoView.ChildAndItsPerson.class)
    @RequestMapping(value = "/child")
    def child() {
        childRepository.findAll()
    }

    @JsonView(RevoView.SuggestedAndChildAndItsPerson.class)
    @RequestMapping(value = "/search")
    def search() {
        //56e1aff5ccf27680225017be
        //56e1aff5ccf27680225017bd
        suggestedChildRepository.findAll()
    }

}
