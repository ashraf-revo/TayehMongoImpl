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
class Child {
    @Id
    @JsonView(RevoView.ChildView.class)
    String id
    @JsonView(RevoView.ChildView.class)
    String name
    @JsonView(RevoView.ChildView.class)
    String image
    @JsonView(RevoView.ChildView.class)
    String moreInfo
    @JsonView(RevoView.ChildView.class)
    Point addresses
    @JsonView(RevoView.ChildView.class)
    Point currentPalce
    @JsonView(RevoView.ChildPerson.class)
    Person person
    @JsonView(RevoView.ChildView.class)
    boolean state
    @DBRef
    @JsonView(RevoView.ChildSuggested.class)
    Set<SuggestedChild> suggestedChild = new HashSet<>()
    @JsonView(RevoView.ChildView.class)
    boolean gender
    @JsonView(RevoView.ChildView.class)
    int age
    @JsonView(RevoView.ChildView.class)
    String uid
    @JsonView(RevoView.ChildView.class)
    boolean founded
    @JsonView(RevoView.ChildView.class)
    Date date = new Date()
}