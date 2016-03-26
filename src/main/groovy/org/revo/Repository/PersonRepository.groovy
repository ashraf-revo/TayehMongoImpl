package org.revo.Repository

import org.revo.Domain.Person
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by revo on 3/10/16.
 */
interface PersonRepository extends MongoRepository<Person, String> {
    Optional<Person> findByEmail(String email);

}
