package com.example.legalfabbacktest;

class ParentJson implements Comparable<ParentJson> {
    public int id = 0;
    public String sender = "";
    public String receiver = "";
    public int totalAmount = 0;
    public int paidAmount = 0;
    ParentJson() {
        // no-args constructor
    }

    @Override
    public int compareTo(ParentJson other) {
        return this.id - other.id;
    }
}
