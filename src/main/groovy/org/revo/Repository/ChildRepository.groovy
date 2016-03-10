package org.revo.Repository

import org.revo.domain.Child
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by revo on 3/10/16.
 */
interface ChildRepository extends MongoRepository<Child, String> {

}