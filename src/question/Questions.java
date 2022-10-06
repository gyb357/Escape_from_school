package question;

import main.Base;

public class Questions {
	Base base;
	
	// [Question][Answer]
	// Answer == 0: Question space
	// Answer == 1: Answer space
	String questionText[][] = new String[20][2];
	
	public Questions(Base base) {
		this.base = base;
		
		setQuestion();
	}
	
	public void setQuestion() {
		int i = 0;
		int X = 1;
		int Y = 1;
		// Math
		X++;
		questionText[i][0] = "이산수학: 00110101 + 00001101 = ?";
		questionText[i][1] = "01000010";
		i++;
		questionText[i][0] = "이산수학: 00110101의 1의 보수는?";
		questionText[i][1] = "01001010";
		i++;
		questionText[i][0] = "이산수학: 00110101의 2의 보수는?";
		questionText[i][1] = "01001011";
		i++;
		questionText[i][0] = "데이터 구조: 높이가 h인 포화 이진트리에서 노드의 개수는?";
		questionText[i][1] = "2^(h+1)-1";
		i++;
		questionText[i][0] = "데이터구조: 퀵 정렬의 최소수행시간은?";
		questionText[i][1] = "o(nlog2n)";
		
		// Nonsense
		i++;
		questionText[i][0] = "넌센스: 꽁꽁 얼어버린 오리는?";
		questionText[i][1] = "언덕";
		i++;
		questionText[i][0] = "넌센스: 노루가 다니는 길은?";
		questionText[i][1] = "노르웨이";
		i++;
		questionText[i][0] = "넌센스: 경찰서의 반대말은?";
		questionText[i][1] = "경찰앉아";
		i++;
		questionText[i][0] = "넌센스: 헤어나올 수 없는 곳은?";
		questionText[i][1] = "대머리";
		i++;
		questionText[i][0] = "넌센스: 술을 많이 마시면?";
		questionText[i][1] = "간이 해롭간";
		
		// Java
		i++;
		questionText[i][0] = "자바: 같은 패키지, 다른 패키지 모두 사용할 수 있는 접근 한정자는?";
		questionText[i][1] = "public";
		i++;
		questionText[i][0] = "자바: 다중 상속을 위해 사용해야 하는 키워드는?";
		questionText[i][1] = "implements";
		i++;
		questionText[i][0] = "자바: 스레드의 순위를 반환하는 메소드는?";
		questionText[i][1] = "getPriority";
		i++;
		questionText[i][0] = "자바: 대기상태에 있는 스레드를 모두 깨우는 메소드는?";
		questionText[i][1] = "notifyAll";
		i++;
		questionText[i][0] = "자바: 숫자를 0으로 나누면 발생하는 예외 키워드";
		questionText[i][1] = "ArithmeticException";
	}
	
	public String getQuestion(int i) {return questionText[i][0];}
	public String getAnswer(int i) {return questionText[i][1];}
}
