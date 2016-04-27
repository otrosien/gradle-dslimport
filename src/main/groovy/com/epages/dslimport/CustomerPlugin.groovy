package com.epages.dslimport

import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.model.Defaults;
import org.gradle.model.Each
import org.gradle.model.Model
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.Path;
import org.gradle.model.RuleSource
import org.gradle.model.Rules
import org.gradle.model.Validate;

class CustomerPlugin extends RuleSource {

    @Rules
    void applyDefaultRules(DefaultRules rules, @Each Customer customer)  {}

    @Rules
    void applyValidateRules(ValidateRules rules, @Each Customer customer)  {}

    @Mutate
    void createCustomerImportTask(ModelMap<Task> tasks, ModelMap<DataSet> dataSet) {
        dataSet.each { final DataSet d ->
            tasks.create("import${d.name.capitalize()}Customers", ImportCustomerTask) { task ->
                task.customers = d.customers
            }
        }
    }
}

abstract class DefaultRules extends RuleSource {

    @Defaults
    void setDefaults(Customer customer) {
        customer.id = String.valueOf(new UUID(customer.name.hash, 'Customer'.hash))
        customer.firstName = customer.name
    }
}

abstract class ValidateRules extends RuleSource {
    @Validate
    void validateLastNameIsNotNull(Customer customer) {
        assert customer.lastName != null
    }
}

class ImportCustomerTask extends DefaultTask {
    @Input
    ModelMap<Customer> customers

    @TaskAction
    def importCustomers() {
        customers.values().each({ c ->
            println "${c.firstName} ${c.lastName}"
        })
    }
}