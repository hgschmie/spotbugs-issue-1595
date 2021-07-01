package spotbugs;

import javax.annotation.CheckForNull;

public class Issue {

    @CheckForNull
    public static FooI getFoo() {
        return new Foo();
    }

    public static void test1(String... args) throws Exception {
        try (FooI foo = getFoo()) {
            try (BarI bar = foo.getBar()) {
                String result = bar.getResult();
            }
        }
    }

    public static void test2(String... args) throws Exception {
        try (FooI foo = getFoo()) {
            try (BarI bar = foo.getBar()) {
                if (bar != null) {
                    String result = bar.getResult();
                }
            }
        }
    }

    public interface FooI extends AutoCloseable {

        public BarI getBar();
    }

    public interface BarI extends AutoCloseable {

        public String getResult();
    }

    public static final class Foo implements FooI {

        @CheckForNull
        public BarI getBar() {
            return new Bar();
        }

        @Override
        public void close() throws Exception {

        }
    }

    public static final class Bar implements BarI {

        public String getResult() {
            return "";
        }

        @Override
        public void close() throws Exception {

        }
    }
}
