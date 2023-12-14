package org.service.accountservice.exception;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String customer, String name, String mobileNumber) {
        super(String.format("%s is not found for the user %s : %s", customer, name, mobileNumber));
    }
}
