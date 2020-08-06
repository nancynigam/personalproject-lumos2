package com.naadworks.lumos.view;

import com.naadworks.lumos.entry.TopicEntry;
import com.naadworks.lumos.util.Status;

import java.util.List;

public class TopicView {

    private Status status;

    private List<TopicEntry> data;

    public TopicView() {
    }

    public TopicView(Status status, List<TopicEntry> data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<TopicEntry> getData() {
        return data;
    }

    public void setData(List<TopicEntry> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TopicView{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
