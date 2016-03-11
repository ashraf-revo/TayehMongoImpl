package org.revo.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonView
import org.revo.RevoView
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by revo on 3/10/16.
 */
@Document
class SuggestedChild {
    @Id
    @JsonView(RevoView.SuggestedView.class)
    String id
    @JsonView(RevoView.SuggestedView.class)
    int confidence
    @DBRef
    @JsonView(RevoView.SuggestedView.class)
    @JsonBackReference
    Child suggested
    @DBRef
    @JsonView(RevoView.SuggestedView.class)
    @JsonBackReference
    Child child
}