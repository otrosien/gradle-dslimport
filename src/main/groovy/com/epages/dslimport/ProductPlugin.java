package com.epages.dslimport;

import org.gradle.model.Model;
import org.gradle.model.ModelMap;
import org.gradle.model.RuleSource;

public class ProductPlugin extends RuleSource {

    @Model
    void products(ModelMap<Product> products) {}

}
