/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.westminster.campus.exceptions;

public class ErrorMessage {
    private String message;
    private int status;

    public ErrorMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() { return message; }
    public int getStatus() { return status; }
}