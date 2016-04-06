import org.gradle.api.Task
import org.gradle.model.Defaults;
import org.gradle.model.Each
import org.gradle.model.Model
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.RuleSource
import org.gradle.model.Rules
import org.gradle.model.Validate;

class PeopleRules extends RuleSource {
    @Model
    void people(ModelMap<Person> people) {}
    @Rules
    void applyDefaultRules(DefaultRules rules, @Each Person person)  {}
    @Rules
    void applyValidateRules(ValidateRules rules, @Each Person person)  {}
    @Mutate
    void createHelloTask(ModelMap<Task> tasks, ModelMap<Person> people) {
        people.each { p ->
            tasks.create("hello${p.name}") {
                doLast {
                    println """
                        Hello $p.firstName $p.lastName! 
                            You're Customer $p.id"""
                }
            }
            tasks.hello.dependsOn("hello${p.name}")
        }
    }
}

abstract class DefaultRules extends RuleSource {
    @Defaults
    void setDefaults(Person person) {
        person.id = "${new UUID(person.name.hash, 0)}"
        person.firstName = person.name
    }
}

abstract class ValidateRules extends RuleSource {
    @Validate
    void validateLastNameIsNotNull(Person person) {
        assert person.lastName != null
    }
}