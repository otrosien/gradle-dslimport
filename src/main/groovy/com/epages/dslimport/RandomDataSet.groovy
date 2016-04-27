package com.epages.dslimport
import org.gradle.model.ModelMap;

class RandomDataSet implements DataSet {

    String name

    int randomCustomers = 0

    @Override
    public ModelMap<Customer> getCustomers() {
        // return ModelMap.of()
    }

}
