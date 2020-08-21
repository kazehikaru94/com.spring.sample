import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Test {

	public static void main(String[] args) {
		String s1 = "ど";
		String s2 = "ど";
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(s1); 
		 
		String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
		 
		System.out.println(s2.equals(utf8EncodedString));
		System.out.println("ど".equals("ど"));
	}

}
