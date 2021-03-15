package programmers.level2;

public class programmers_70129 {

	public static int LENGTH;

	public static void main(String[] args) {
		String s1 = "110010101001";
		System.out.println(solution(s1));
		String s2 = "01110";
		System.out.println(solution(s2));
		String s3 = "1111111";
		System.out.println(solution(s3));
	}

	public static int[] solution(String s) {
		int zeroCount = 0;
		int loopCount = 0;
		while (s.equals("1") == false) {
			int beforeLength = s.length();
			s = s.replaceAll("0", "");
			int afterLength = s.length();
			zeroCount += beforeLength - afterLength;
			loopCount++;
			s = Integer.toBinaryString(s.length());
		}
		System.out.println(loopCount + "," + zeroCount);
		int[] answer = {loopCount, zeroCount};
		return answer;
	}
}
