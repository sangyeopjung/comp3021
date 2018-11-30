package lab12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskUtilitiesTest {
    List<Task> tasks;
    Task task1, task2, task3, task4, task4clone, task5;

    @BeforeEach
    void setUp() {
        task1 = new Task("Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");
        task2 = new Task("Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("java8").addTag("reading").addTag("books");
        task3 = new Task("Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");
        task4 = new Task("Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2014, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");
        task4clone = new Task("Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2016, Month.JULY, 7)).addTag("blogging").addTag("writing").addTag("streams");
        task5 = new Task("Read Domain Driven Design book", TaskType.READING, LocalDate.of(2013, Month.JULY, 5)).addTag("ddd").addTag("books").addTag("reading");
        tasks = Arrays.asList(task1, task2, task3, task4, task5);
    }

    @Test
    void getAllDistinctTitles() {
        assertEquals(5, TaskUtilities.getAllDistinctTitles(tasks).size());
    }

    @Test
    void getTopN() {
        var result = TaskUtilities.getTopN(tasks, 3);
        var expected = Stream.of(task5, task1, task2).map(Task::getTitle).collect(Collectors.toList());
        assertEquals(expected.size(), result.size());
        for (int k = 0; k < expected.size(); k++) {
            Assertions.assertEquals(expected.get(k), result.get(k));
        }
    }

    @Test
    void countAllReadingTasks() {
        assertEquals(3, TaskUtilities.countAllReadingTasks(tasks));
    }

    @Test
    void doAllReadingTasksHaveBooksTag() {
        assertTrue(TaskUtilities.doAllReadingTasksHaveBooksTag(tasks));
    }

    @Test
    void joinAllTaskTitles() {
        assertEquals("Read Version Control with Git book, Read Java 8 Lambdas book, " +
                        "Write a mobile application to store my tasks, " +
                        "Write a blog on Java 8 Streams, Read Domain Driven Design book",
                TaskUtilities.joinAllTaskTitles(tasks));
    }

    @Test
    void getAllDistinctTags() {
        assertEquals(10, TaskUtilities.getAllDistinctTags(tasks).size());
    }

    @Test
    void getAllReadingTaskTitlesSortedAscendingOnCreationDate() {
        var expected = Arrays.asList("Read Domain Driven Design book",
                "Read Version Control with Git book", "Read Java 8 Lambdas book");
        var result = TaskUtilities.getAllReadingTaskTitlesSortedAscendingOnCreationDate(tasks);
        assertEquals(expected.size(), result.size());
        for (int k = 0; k < expected.size(); k++) {
            assertEquals(expected.get(k), result.get(k));
        }
    }
}