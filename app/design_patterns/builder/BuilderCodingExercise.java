package design_patterns.builder;

import java.util.HashMap;
import java.util.Map;

public class BuilderCodingExercise {

  public static class CodeBuilder {

    private final String className;
    private final Map<String, String> attributes = new HashMap<>();

    public CodeBuilder(String className) {
      this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
      attributes.put(name, type);
      return this;
    }

    @Override
    public String toString() {
      StringBuilder sb =
          new StringBuilder()
              .append(
                  String.format(
                      "public class %s%s{%s",
                      className, System.lineSeparator(), System.lineSeparator()));
      attributes.forEach(
          (k, v) -> {
            sb.append(String.format("  public %s %s;%s", v, k, System.lineSeparator()));
          });
      sb.append("}");
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
    System.out.println(cb);
  }
}
