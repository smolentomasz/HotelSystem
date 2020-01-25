package com.hotelsystem.hotelsystem.web.data_models;

public enum UserType {
    ADMIN {
        @Override
        public String toString() {
            return "ADMIN";
        }
    },
    STANDARD {
        @Override
        public String toString() {
            return "STANDARD";
        }
    }
}
