package com.example.asm_android_networking.Login;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private Data data;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("accessToken")
        private String accessToken;
        @SerializedName("objU")
        private objU objU;

        public Data.objU getObjU() {
            return objU;
        }

        public String getAccessToken() {
            return accessToken;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "accessToken='" + accessToken + '\'' +
                    '}';
        }

        public class objU {
            @SerializedName("_id")
            private String _id;
            private String fullname;
            private String username;
            private String password;
            private String email;
            private String img;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Override
            public String toString() {
                return "objU{" +
                        "_id='" + _id + '\'' +
                        ", fullname='" + fullname + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", email='" + email + '\'' +
                        ", img='" + img + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
