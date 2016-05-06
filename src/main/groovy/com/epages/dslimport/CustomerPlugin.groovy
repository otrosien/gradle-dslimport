package com.epages.dslimport

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.model.Defaults;
import org.gradle.model.Each
import org.gradle.model.Finalize;
import org.gradle.model.Model
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.Path;
import org.gradle.model.RuleSource
import org.gradle.model.Rules
import org.gradle.model.Validate;

class CustomerPlugin extends RuleSource {

    @Model
    void customers(ModelMap<Customer> customers) {}

    @Rules
    void applyValidateRules(ValidateRules rules, @Each Customer customer)  {}

    @Rules
    void applyFinalizationRules(FinalizationRules rules, @Each Customer customer)  {}

    @Mutate
    void createCustomerGreetingTask(ModelMap<Task> tasks, final ModelMap<Customer> customers) {
        tasks.create("greetCustomers", CustomerGreetingTask) {
            it.customers = customers
        }
    }
}

abstract class FinalizationRules extends RuleSource {
    @Finalize
    void setFinalizationValues(Customer customer) {
        if (customer.id == null) {
            customer.id = String.valueOf(new UUID(customer.name.hash, 'Customer'.hash))
        }
    }
}

abstract class ValidateRules extends RuleSource {
    @Validate
    void validateLastNameIsNotNull(Customer customer) {
        assert customer.id != null
        assert UUID.fromString(customer.id) != null
        assert customer.lastName != null
    }
}

class CustomerGreetingTask extends DefaultTask {

    @Input
    ModelMap<Customer> customers

    @TaskAction
    def greetCustomers() {
        customers.values().each({ c ->
            println "Greetings " + c.fullName + " (" + c.id + ")"
            if(c.address != null && !c.address.empty) {
                println "  You're from: ${c.address.street} ${c.address.city}"
            }
        })
    }
}