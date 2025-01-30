package com.example.freshstart.models;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final HistoryManager instance = new HistoryManager();
    private List<History> historyList;

    private HistoryManager() {
        historyList = new ArrayList<>();
    }

    public static HistoryManager getInstance() {
        return instance;
    }

    public void addHistory(String tShirtColor, String imageUrl) {
        historyList.add(new History(tShirtColor, imageUrl));
    }

    public List<History> getHistoryList() {
        return new ArrayList<>(historyList);
    }
}


