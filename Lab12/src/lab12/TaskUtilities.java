package lab12;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TaskUtilities {
    /**
     * TODO find all reading tasks, and sort them by their creation date, ascending. We just want the titles.
     * <p>
     * Hint: sorted, Comparator.comparing, and Task::getCreatedOn is useful for sorting.
     * Collectors.toList() is useful for returning a list of strings.
     *
     * @param tasks the list of all tasks
     * @return the list of relevant tasks
     */
    public static List<String> getAllReadingTaskTitlesSortedAscendingOnCreationDate(List<Task> tasks) {
        return tasks.stream().filter(t->t.getType().equals(TaskType.READING)).sorted(comparing(Task::getCreatedOn)).map(Task::getTitle).collect(toList());
    }

    /**
     * TODO find all unique tags from all tasks
     * Note that each task could have multiple tags
     * <p>
     * Hint: flatMap, distinct
     *
     * @param tasks the list of all tasks
     * @return the list of relevant tasks
     */
    public static List<String> getAllDistinctTags(List<Task> tasks) {
        return tasks.stream().flatMap(t->t.getTags().stream()).distinct().collect(toList());
    }

    /**
     * TODO find all distinct task titles.
     * <p>
     * Hint: map, distinct
     *
     * @param tasks the list of all tasks
     * @return the list of relevant tasks
     */
    public static List<String> getAllDistinctTitles(List<Task> tasks) {
        return tasks.stream().map(Task::getTitle).distinct().collect(toList());
    }

    /**
     * TODO find the top n reading tasks sorted by creation date
     * <p>
     * Hint:  limit, filter, sorted, map
     *
     * @param tasks all tasks
     * @param n     the top n to retrieve
     * @return the top n reading tasks sorted by creation date
     */
    public static List<String> getTopN(List<Task> tasks, int n) {
        return tasks.stream().filter(t->t.getType().equals(TaskType.READING)).sorted(comparing(Task::getCreatedOn)).limit(n).map(Task::getTitle).collect(toList());
    }

    /**
     * TODO count all reading tasks
     *
     * @param tasks the list of all tasks
     * @return how many reading tasks there were
     */
    public static long countAllReadingTasks(List<Task> tasks) {
        return tasks.stream().filter(t->t.getType().equals(TaskType.READING)).count();
    }

    /**
     * TODO check if all reading tasks have the tag "books"
     * <p>
     * Hint: allMatch
     *
     * @param tasks the list of all tasks
     * @return if all reading tasks have the tag "books"
     */
    public static boolean doAllReadingTasksHaveBooksTag(List<Task> tasks) {
        return tasks.stream().filter(t->t.getType().equals(TaskType.READING)).allMatch(t->t.getTags().contains("books"));
    }

    /**
     * TODO join all the task titles into a string separated by comma and space
     * <p>
     * Hint: map, reduce, get
     *
     * @param tasks the list of all tasks
     * @return comma separated string of the task titles
     */
    public static String joinAllTaskTitles(List<Task> tasks) {
        return tasks.stream().map(Task::getTitle).reduce((a, b)->a+", "+b).get();
    }
}
