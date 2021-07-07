package sikulix;

import java.util.List;

import org.sikuli.script.Match;
import org.sikuli.script.OCR;

public class Main {

	public static void main(String[] args) {
		OCR.globalOptions().dataPath("/Users/qiuguozhong/eclipse-workspace/sikulix/src/main/resources/tessdata");
		OCR.globalOptions().language("chi_sim");
		List<Match> results = OCR.readLines("/Users/qiuguozhong/eclipse-workspace/sikulix/src/main/resources/test.png");

		for (Match result : results) {
			System.out.println(result.getText());
		}
	}

}
