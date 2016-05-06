package com.epages.dslimport

import org.fluttercode.datafactory.impl.DataFactory
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.model.Defaults;
import org.gradle.model.Each
import org.gradle.model.Mutate
import org.gradle.model.RuleSource

class RandomCustomerPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.pluginManager.apply(CustomerPlugin)
    }

    static class RandomCustomerRuleSource extends RuleSource {

        private static final DataFactory dataFactory = new DataFactory()

        @Defaults
        void applyRandomData(@Each Customer customer) {
            customer.firstName = dataFactory.firstName
            customer.lastName = dataFactory.lastName
            customer.emailAddress = dataFactory.emailAddress
            customer.address {
                street = dataFactory.address
                city = dataFactory.city
            }
        }
    }
}