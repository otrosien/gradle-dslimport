import org.gradle.api.Task
import org.gradle.model.Managed;
import org.gradle.model.Model;
import org.gradle.model.ModelMap;
import org.gradle.model.Mutate;
import org.gradle.model.RuleSource;

class ConfigRules extends RuleSource {
    @Model
    void config(Config config) {}
    @Mutate
    void createHelloTask(ModelMap<Task> tasks, Config config) {
        tasks.create("hello")
    }
}

// you can inline the model here..

@Managed
interface Config {
    boolean isRandomizeIds()
    void setRandomizeIds(boolean randomizeIds)

    int getRandomCustomers()
    void setRandomCustomers(int randomCustomers)
}