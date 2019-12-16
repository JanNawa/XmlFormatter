package assignment_5.datamodel;

/**
 * This class included the attributes for the address.
 * @author Jan
 */
public class Address {
    // define all the information related to address
    private String streetAddress;
    private String city;
    private String postalCode;
    private String country;

    // constructor for the address
    public Address(String streetAddress, String city, String postalCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getter for the streetAddress
    public String getStreetAddress() {
        return streetAddress;
    }

    // Getter for the city
    public String getCity() {
        return city;
    }

    // Getter for the postalCode
    public String getPostalCode() {
        return postalCode;
    }

    // Getter for the country
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "\nAddress{" + "streetAddress=" + streetAddress + ", city=" + city + ", postalCode=" + postalCode + ", country=" + country + '}' + "\n";
    } 
}