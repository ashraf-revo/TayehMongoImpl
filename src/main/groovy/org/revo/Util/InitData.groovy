package org.revo.Util

import org.revo.Domain.Child
import org.revo.Domain.Person
import org.revo.Domain.SuggestedChild
import org.revo.Repository.ChildRepository
import org.revo.Repository.PersonRepository
import org.revo.Repository.SuggestedChildRepository
import org.springframework.data.mongodb.core.MongoOperations

/**
 * Created by revo on 3/12/16.
 */
class InitData {
    static void fun1Test(MongoOperations mongoOperations,
                         PersonRepository personRepository,
                         ChildRepository childRepository,
                         SuggestedChildRepository suggestedChildRepository) {
        mongoOperations.dropCollection(Child.class)
        mongoOperations.dropCollection(Person.class)
        mongoOperations.dropCollection(SuggestedChild.class)
        String p1 = "ashraf1"
        String p2 = "ashraf2"


        personRepository.save([
                new Person(name: p1, email: p1 + "@gmail.com", password: p1),
                new Person(name: p2, email: p2 + "@gmail.com", password: p2)
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
