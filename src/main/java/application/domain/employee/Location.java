package application.domain.employee;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false, unique = true)
    private Long locationId;

    @Column(length = 25)
    private String streetAddress;

    @Column(name = "postalCode")
    private int postalCode;

    private String city;

    /**
     * @OneToMany
     * Many Departments can be in same location
     * We keep these departments as a collection type
     * ex: in targovy there are so many bank departments in same location
     */

    /**
     * @cascade
     * if i change location, connected values will be changed automatically
     * ex: if location city is baku, departments are software,
     * tester, network.
     * if i change this location, we show how much departments will change.
     * cascade type can be: all, merge and so on. it shows effect level
     * of the updates.
     * if i delete this location, connected departments
     * will be deleted(cascade.all).
     */

    /**
     * @mappedBy
     * we give priority to the specific class
     * "location" - in Department we have Location type of location
     * Reason: first-class is department. and in department
     * location foreign key added.
     */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Department> departments;


    public Location() {
    }

    public Location(String streetAddress, int postalCode, String city) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
    }


    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId);
    }
}
