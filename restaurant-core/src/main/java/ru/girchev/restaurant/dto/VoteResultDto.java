package ru.girchev.restaurant.dto;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 23.11.15.
 */
public class VoteResultDto {

    private boolean voted;

    private String message;

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
