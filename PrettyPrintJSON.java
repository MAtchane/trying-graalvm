import java.io.*;
import java.util.stream.*;
import org.graalvm.polyglot.*;

/**
* A simple utility that pretty print JSON files using JavaScript.
*
* @see <a>https://www.graalvm.org/docs/getting-started/</a>
**/
public class PrettyPrintJSON {
    public static void main(String[] args) throws java.io.IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        
        try (Context context = Context.create("js")) {
            Value parse = context.eval("js", "JSON.parse");
            Value stringify = context.eval("js", "JSON.stringify");
            Value result = stringify.execute(parse.execute(input), null, 2);
            System.out.println(result.asString());
        }
    }
}