package org.revo;

/**
 * Created by revo on 3/10/16.
 */
class RevoView {
    interface PersonView {
    }

    interface ChildView {
    }

    interface SuggestedView {
    }

    interface ChildPerson {
    }

    interface PersonChild {
    }

    interface ChildSuggested {
    }

    interface ChildAndItsPerson extends ChildView, PersonView, ChildPerson {
    }

    interface SuggestedAndChildAndItsPerson extends ChildView, PersonView, ChildPerson, SuggestedView {
    }

    interface PersonAndItsChild extends ChildView, PersonView, PersonChild {
    }
}