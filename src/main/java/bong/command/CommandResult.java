package bong.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isModified;

    /**
     * Constructs a {@code CommandResult} with the specified feedback and modification status.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param isModified Indicates whether the command has modified the task list.
     */
    public CommandResult(String feedbackToUser, boolean isModified) {
        this.feedbackToUser = feedbackToUser;
        this.isModified = isModified;
    }

    /**
     * Returns the feedback message to be shown to the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Indicates whether the command has modified the task list.
     *
     * @return {@code true} if the task list has been modified, {@code false} otherwise.
     */
    public boolean isModified() {
        return isModified;
    }
}