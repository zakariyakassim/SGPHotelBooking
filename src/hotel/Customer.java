/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.regex.Pattern;

/**
 *
 * @author Amelia
 */
public class Customer {

    int customerId;
    String forename;
    String surname;
    String postCode;
    String phoneNo;
    String bankCardNo;

    public Customer(String forename, String surname, String postCode, String phoneNo, String bankCardNo) {
//        this.ID = ???
        this.forename = forename;
        this.surname = surname;
        this.postCode = validatePostCode(postCode);
        this.phoneNo = validatePhoneNo(phoneNo);
        this.bankCardNo = validateBankCardNo(bankCardNo);
    }

    private String validatePostCode(String value) {
        String validPostCodeRegex = "(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]]"
                + "[A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKSTUW])|([A-Z-[QVX]]"
                + "[A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})";
        return validateByRegex(value, validPostCodeRegex, "Invalid Post Code: " + value);
    }

    private String validatePhoneNo(String value) {
        String validPhoneNoRegex = "^(([(]?(\\d{2,4})[)]?)|(\\d{2,4})|([+1-9]+\\d{1,2}))?"
                + "[-\\s]?(\\d{2,3})?[-\\s]?((\\d{7,8})|(\\d{3,4}[-\\s]\\d{3,4}))$";
        return validateByRegex(value, validPhoneNoRegex, "Invalid Phone Number: " + value);
    }

    private String validateBankCardNo(String value) {
        String validBankCardNoRegex = "^[0-9]{4}\\ [0-9]{4}\\ [0-9]{4}\\ [0-9]{4}$";
        return validateByRegex(value, validBankCardNoRegex, "Invalid Bank Card Number: " + value);
    }

    private String validateByRegex(String value, String regex, String error) {
        if (!Pattern.compile(regex).matcher(value).matches()) {
            throw new IllegalArgumentException(error);
        }
        return value;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }
}
