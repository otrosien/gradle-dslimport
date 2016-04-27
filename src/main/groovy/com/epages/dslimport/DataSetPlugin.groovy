package com.epages.dslimport
import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction;
import org.gradle.model.Model;
import org.gradle.model.ModelMap
import org.gradle.model.ModelSet
import org.gradle.model.Mutate;
import org.gradle.model.RuleSource;

class DataSetPlugin extends RuleSource {
    @Model
    void dataSets(ModelMap<DataSet> dataSets) {}

}
