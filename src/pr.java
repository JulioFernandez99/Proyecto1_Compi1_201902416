import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pr {

	public static void main(String[] args) {
			 Pattern pat = Pattern.compile("[a-zA-Z]{5,10}");
		     Matcher mat = pat.matcher("amamamamam");                                                                           
		     if (mat.matches()) {
		         System.out.println("SI");
		     } else {
		         System.out.println("NO");                                                                                
		     }

	}

}
