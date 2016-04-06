import org.gradle.api.Named;
import org.gradle.model.Managed;

@Managed
interface Person extends Named {

    String getId()
    void setId(String s)

    String getFirstName()
    void setFirstName(String s)

    String getLastName()
    void setLastName(String s)

    Address getAddress()
}
