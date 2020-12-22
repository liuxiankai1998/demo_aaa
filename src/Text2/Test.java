package Text2;

public class Test {
	public static void main(String[] args) {
		QuestionInfo que = new QuestionInfo();
		try {
			que.showMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
