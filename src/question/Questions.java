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
		questionText[i][0] = "�̻����: 00110101 + 00001101 = ?";
		questionText[i][1] = "01000010";
		i++;
		questionText[i][0] = "�̻����: 00110101�� 1�� ������?";
		questionText[i][1] = "01001010";
		i++;
		questionText[i][0] = "�̻����: 00110101�� 2�� ������?";
		questionText[i][1] = "01001011";
		i++;
		questionText[i][0] = "������ ����: ���̰� h�� ��ȭ ����Ʈ������ ����� ������?";
		questionText[i][1] = "2^(h+1)-1";
		i++;
		questionText[i][0] = "�����ͱ���: �� ������ �ּҼ���ð���?";
		questionText[i][1] = "o(nlog2n)";
		
		// Nonsense
		i++;
		questionText[i][0] = "�ͼ���: �ǲ� ������ ������?";
		questionText[i][1] = "���";
		i++;
		questionText[i][0] = "�ͼ���: ��簡 �ٴϴ� ����?";
		questionText[i][1] = "�븣����";
		i++;
		questionText[i][0] = "�ͼ���: �������� �ݴ븻��?";
		questionText[i][1] = "�����ɾ�";
		i++;
		questionText[i][0] = "�ͼ���: ���� �� ���� ����?";
		questionText[i][1] = "��Ӹ�";
		i++;
		questionText[i][0] = "�ͼ���: ���� ���� ���ø�?";
		questionText[i][1] = "���� �طӰ�";
		
		// Java
		i++;
		questionText[i][0] = "�ڹ�: ���� ��Ű��, �ٸ� ��Ű�� ��� ����� �� �ִ� ���� �����ڴ�?";
		questionText[i][1] = "public";
		i++;
		questionText[i][0] = "�ڹ�: ���� ����� ���� ����ؾ� �ϴ� Ű�����?";
		questionText[i][1] = "implements";
		i++;
		questionText[i][0] = "�ڹ�: �������� ������ ��ȯ�ϴ� �޼ҵ��?";
		questionText[i][1] = "getPriority";
		i++;
		questionText[i][0] = "�ڹ�: �����¿� �ִ� �����带 ��� ����� �޼ҵ��?";
		questionText[i][1] = "notifyAll";
		i++;
		questionText[i][0] = "�ڹ�: ���ڸ� 0���� ������ �߻��ϴ� ���� Ű����";
		questionText[i][1] = "ArithmeticException";
	}
	
	public String getQuestion(int i) {return questionText[i][0];}
	public String getAnswer(int i) {return questionText[i][1];}
}
