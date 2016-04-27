package com.epages.dslimport
import org.gradle.api.Named;
import org.gradle.model.Managed
import org.gradle.model.ModelMap;
import org.gradle.model.ModelSet;

@Managed
interface DataSet extends Named {

    ModelMap<Customer> getCustomers()

}
