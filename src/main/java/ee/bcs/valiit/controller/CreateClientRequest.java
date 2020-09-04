package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;

public class CreateClientRequest {
    private String firstName;
    private String lastName;
    private BigInteger clientId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }
}
