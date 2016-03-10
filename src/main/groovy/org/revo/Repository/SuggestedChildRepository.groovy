package org.revo.Repository

import org.revo.domain.SuggestedChild
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by revo on 3/10/16.
 */
interface SuggestedChildRepository extends MongoRepository<SuggestedChild,String>{

}