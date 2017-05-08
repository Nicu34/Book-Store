package ro.bookstore.common.utils;

import java.util.Stack;

/**
 * Created by nicu on 3/21/2017.
 */
public abstract class SalesIdGenerator {
    private static Long id = 0L;

    private static Stack<Long> freeFileDescriptors = new Stack<>();

    public static Long getId() {
        return freeFileDescriptors.isEmpty() ? id++ : freeFileDescriptors.pop();
    }

    public static void freeId(Long id) {
        if (id < SalesIdGenerator.id) {
            freeFileDescriptors.push(id);
        }
    }
}
