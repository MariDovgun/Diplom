package by.maria.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private int id;
    private String title;
    private String page;
    private boolean useFreeTip;
    private boolean usePayTip;
    private boolean complete;
    private LocalDateTime startTime;

    public Task() {
    }

    public Task(int id, String title, String page, boolean useTip, boolean usePayTip, boolean complete) {
        this.id = id;
        this.title = title;
        this.page = page;
        this.useFreeTip = useTip;
        this.usePayTip = usePayTip;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isUseFreeTip() {
        return useFreeTip;
    }

    public void setUseFreeTip(boolean useFreeTip) {
        this.useFreeTip = useFreeTip;
    }

    public boolean isUsePayTip() {
        return usePayTip;
    }

    public void setUsePayTip(boolean usePayTip) {
        this.usePayTip = usePayTip;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id &&
                useFreeTip == task.useFreeTip &&
                usePayTip == task.usePayTip &&
                complete == task.complete &&
                Objects.equals(title, task.title) &&
                Objects.equals(page, task.page);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, page, useFreeTip, usePayTip, complete);
    }
}
