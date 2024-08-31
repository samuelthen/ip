package bong.command;

public class CommandResult {
    private final String feedbackToUser;
    private final boolean isModified;

    public CommandResult(String feedbackToUser, boolean isModified) {
        this.feedbackToUser = feedbackToUser;
        this.isModified = isModified;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isModified() {
        return isModified;
    }
}