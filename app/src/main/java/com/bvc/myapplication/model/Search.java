package com.bvc.myapplication.model;

import java.util.ArrayList;

public class Search {
    private ArrayList<Movie> Search;

    public ArrayList<Movie> getSearch() {
        return Search;
    }

    public void setSearch(ArrayList<Movie> search) {
        Search = search;
    }

    @Override
    public String toString() {
        return "Search{" +
                "Search=" + Search +
                '}';
    }
}
