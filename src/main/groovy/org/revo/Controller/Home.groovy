package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import groovy.util.logging.Log
import org.revo.Domain.Person
import org.revo.Repository.ChildRepository
import org.revo.Repository.PersonRepository
import org.revo.Repository.SuggestedChildRepository
import org.revo.Util.RevoView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

/**
 * Created by revo on 3/12/16.
 */

@RestController
@Log
class Home {
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

    @JsonView(RevoView.PersonView.class)
    @RequestMapping(value = "/person")
    def person() {
        personRepository.findAll()
    }

    @JsonView(RevoView.ChildView.class)
    @RequestMapping(value = "/child")
    def child() {
        childRepository.findAll()
    }

    @JsonView(RevoView.ChildAndItsPerson.class)
    @RequestMapping(value = "/childperson")
    def childPerson() {
        childRepository.findAll()
    }

    @JsonView(RevoView.SuggestedAndChildAndItsPerson.class)
    @RequestMapping(value = "/search")
    def search() {
        suggestedChildRepository.findAll()
    }

    @RequestMapping(value = "/play")
    String play() {

        return "redirect:" + entityLinks.linkFor(Person.class).toUri()
    }
    @Autowired
    EntityLinks entityLinks;
}