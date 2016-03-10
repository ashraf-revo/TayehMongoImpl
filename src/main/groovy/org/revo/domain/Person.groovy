package org.revo.domain

import com.fasterxml.jackson.annotation.JsonView
import org.revo.RevoView
import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by revo on 3/10/16.
 */
@Document
class Person {
    @Id
    @JsonView(RevoView.PersonView.class)
    String id
    @JsonView(RevoView.PersonView.class)
    String name
    @JsonView(RevoView.PersonView.class)
    String email
    @JsonView(RevoView.PersonView.class)
    String password
    @JsonView(RevoView.PersonView.class)
    String phone
    @JsonView(RevoView.PersonView.class)
    String image
    @JsonView(RevoView.PersonView.class)
    String moreInfo
    @JsonView(RevoView.PersonView.class)
    Point addresses
    @JsonView(RevoView.PersonView.class)
    Point location
    @DBRef
    @JsonView(RevoView.PersonChild.class)
    Set<Child> children
    @JsonView(RevoView.PersonView.class)
    String queueName
    @JsonView(RevoView.PersonView.class)
    boolean enabled = false
}