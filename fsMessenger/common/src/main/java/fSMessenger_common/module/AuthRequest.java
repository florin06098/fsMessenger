package fSMessenger_common.module;

import java.io.Serializable;

public class AuthRequest implements Serializable {
//    private User user;
    private final String username;
    private final String password;
    private final String last_name;
    private final String first_name;
    private final int age;
    private final String email;
    private final AuthType authTypeOperation;

    public AuthRequest(Builder builder) {
        this.username = builder.userName;
        this.password = builder.password;
        this.last_name = builder.last_name;
        this.first_name = builder.first_name;
        this.age = builder.age;
        this.email = builder.email;
        this.authTypeOperation = builder.authTypeOperation;
    }

    public static class Builder{
        private final String userName;
        private final String password;
        private final AuthType authTypeOperation;
        private String last_name;
        private String first_name;
        private int age;
        private String email;

        public Builder(String userName, String password, AuthType authTypeOperation) {
            this.userName = userName;
            this.password = password;
            this.authTypeOperation = authTypeOperation;
        }

        public Builder withLastName(String last_name){
            this.last_name = last_name;
            return this;
        }

        public Builder withFirstName(String first_name){
            this.first_name = first_name;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withAge(int age){
            this.age = age;
            return this;
        }

        public AuthRequest build(){
            return new AuthRequest(this);
        }

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public AuthType getAuthTypeOperation() {
        return authTypeOperation;
    }
}
