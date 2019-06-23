package com.geca.trackingboss.model.relative;

import android.os.Bundle;

import com.squareup.moshi.Json;

import java.util.List;

public class ListRelativeResponse {

    public static class RelativeResponse {
        @Json(name = "id")
        private int id;
        @Json(name = "isOnline")
        private boolean isOnline;
        @Json(name = "boss")
        private Boss boss;
        @Json(name = "user")
        private User user;

        public RelativeResponse(Bundle fromBundle) {
            id = fromBundle.getInt("id");
            isOnline = fromBundle.getBoolean("isOnline");
            boss = new Boss(fromBundle.getBundle("boss"));
            user = new User(fromBundle.getBundle("user"));
        }

        public int getId() {
            return id;
        }

        public boolean isOnline() {
            return isOnline;
        }

        public Boss getBoss() {
            return boss;
        }

        public User getUser() {
            return user;
        }

        public static class Boss {
            @Json(name = "id")
            private int id;

            public Boss(Bundle fromBundle) {
                id = fromBundle.getInt("id");
            }

            public Bundle toBundle() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                return bundle;
            }

            public int getId() {
                return id;
            }
        }

        public static class User {
            @Json(name = "id")
            private int id;
            @Json(name = "username")
            private String username;
            @Json(name = "firstname")
            private String firstname;
            @Json(name = "lastname")
            private String lastname;
            @Json(name = "dni")
            private String dni;
            @Json(name = "phone")
            private String phone;
            @Json(name = "address")
            private String address;
            @Json(name = "active")
            private boolean active;

            public User(Bundle fromBundle) {
                id = fromBundle.getInt("id");
                username = fromBundle.getString("username");
                firstname = fromBundle.getString("firstname");
                lastname = fromBundle.getString("lastname");
                dni = fromBundle.getString("dni");
                phone = fromBundle.getString("phone");
                address = fromBundle.getString("address");
                active = fromBundle.getBoolean("active");
            }

            public Bundle toBundle() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("username", username);
                bundle.putString("firstname", firstname);
                bundle.putString("lastname", lastname);
                bundle.putString("dni", dni);
                bundle.putString("phone", phone);
                bundle.putString("address", address);
                bundle.putBoolean("active", active);
                return bundle;
            }

            public int getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public String getFirstname() {
                return firstname;
            }

            public String getLastname() {
                return lastname;
            }

            public String getDni() {
                return dni;
            }

            public String getPhone() {
                return phone;
            }

            public String getAddress() {
                return address;
            }

            public boolean getActive() {
                return active;
            }
        }
    }

    @Json(name = "data")
    private List<RelativeResponse> data;
    @Json(name = "total")
    private int total;
    @Json(name = "pages")
    private int pages;
    @Json(name = "pageSize")
    private int pageSize;

    public List<RelativeResponse> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public int getPageSize() {
        return pageSize;
    }
}
