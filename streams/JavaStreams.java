import java.lang.String;
import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;

public class JavaStreams{

	public static void main(String[] args) throws IOException{
		Stream.of("Ava", "Aneri", "Alberto")
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);

	}

}