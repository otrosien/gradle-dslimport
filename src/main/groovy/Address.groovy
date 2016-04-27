import org.gradle.model.Managed

@Managed
interface Address {
    String getStreet()
    void setStreet(String street)

    String getCity()
    void setCity(String city)
}