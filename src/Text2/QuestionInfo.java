package Text2;

import java.sql.ResultSet;
import java.util.Scanner;

public class QuestionInfo {
	public void showMenu(){
		try {
			//1
			QuestionInfoDaoImpl q = new QuestionInfoDaoImpl();
			System.out.println("*************��ӭʹ���������ϵͳ*****************************************************");
			System.out.print("��ѡ�������1.�г��������� 2.����Ŀ��ѯ 3.�����ģ����ѯ 4.������� 5.ɾ������ 6.�˳�ϵͳ ��");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int choice1 = scan.nextInt();
			switch (choice1) {
			case 1:
				//1.�г���������
				String sql1 = "select questionId,question,optionA,optionB,optionC,optionD,answer from question_info";
				ResultSet rs = q.queryDB(sql1);
				while (rs.next()) {
					int id = rs.getInt("questionId");
					Object qu = rs.getObject("question");
					Object a = rs.getObject("optionA");
					Object b = rs.getObject("optionB");
					Object c = rs.getObject("optionC");
					Object d = rs.getObject("optionD");
					Object ans = rs.getObject("answer");
					System.out.println(id+"��"+qu);
					System.out.println("\tѡ��A��"+a);
					System.out.println("\tѡ��B��"+b);
					System.out.println("\tѡ��C��"+c);
					System.out.println("\tѡ��D��"+d);
					System.out.println("\t�𰸣�"+ans);
				}
				break;
			case 2:
				//2.����Ŀ��ѯ
				System.out.print("���������ѯ�Ŀ�Ŀ(1�����2����ʳ��)��");
				int num = scan.nextInt();
				String sql2 = "select questionId,question,optionA,optionB,optionC,optionD,answer,subject from question_info where subject = ?";
				ResultSet rs1 = q.queryDB(sql2,num);
				while (rs1.next()) {
					int id = rs1.getInt("questionId");
					Object qu = rs1.getObject("question");
					Object a = rs1.getObject("optionA");
					Object b = rs1.getObject("optionB");
					Object c = rs1.getObject("optionC");
					Object d = rs1.getObject("optionD");
					Object ans = rs1.getObject("answer");
					System.out.println(id+"��"+qu);
					System.out.println("\tѡ��A��"+a);
					System.out.println("\tѡ��B��"+b);
					System.out.println("\tѡ��C��"+c);
					System.out.println("\tѡ��D��"+d);
					System.out.println("\t�𰸣�"+ans);
				}
				break;
			case 3:
				// 3.�����ģ����ѯ
				System.out.print("���������ѯ�Ĺؼ���:");
				String str = scan.next();
				String sql3 = "select * from question_info where  question like concat('%',?,'%')";
				ResultSet rs2 = q.queryDB(sql3,str);
				while (rs2.next()) {
					int id = rs2.getInt("questionId");
					Object qu = rs2.getObject("question");
					Object a = rs2.getObject("optionA");
					Object b = rs2.getObject("optionB");
					Object c = rs2.getObject("optionC");
					Object d = rs2.getObject("optionD");
					Object ans = rs2.getObject("answer");
					System.out.println(id+"��"+qu);
					System.out.println("\tѡ��A��"+a);
					System.out.println("\tѡ��B��"+b);
					System.out.println("\tѡ��C��"+c);
					System.out.println("\tѡ��D��"+d);
					System.out.println("\t�𰸣�"+ans);
				}
				break;
			case 4:
				//4.�������
				String ssql = "SELECT MAX(questionId) FROM question_info;";
				ResultSet rrs = q.queryDB(ssql);
				int max=0;
				if (rrs.next()) {
					max = rrs.getInt("MAX(questionId)");
				}
				System.out.print("�������Ŀ:(1.���� 2.ʳ�� )");
				int num1 = scan.nextInt();
				System.out.println("�������µ��⣺");
				String strTG = scan.next();
				System.out.println("�������µ�ѡ��A��");
				String strA = scan.next();
				System.out.println("�������µ�ѡ��B��");
				String strB = scan.next();
				System.out.println("�������µ�ѡ��C��");
				String strC = scan.next();
				System.out.println("�������µ�ѡ��D��");
				String strD = scan.next();
				System.out.println("�������µĴ𰸣�");
				String strANS = scan.next();
				System.out.println();
				String sql4 = "insert into question_info (questionId,question,optionA,optionB,optionC,optionD,answer,subject) values(?,?,?,?,?,?,?,?)";
				int rs3 = q.operateDB(sql4,(max+1),strTG,strA,strB,strC,strD,strANS,num1);
				if (rs3 == 1) {
					System.out.println("��ӳɹ���");
				}else {
					System.out.println("���ʧ�ܣ�");
				}
				break;
			case 5:
				//5.ɾ������
				lab:for (;;) {
					System.out.println("��������Ҫɾ������Ŀ��ţ�");
					int num2 = scan.nextInt();
					String sql5 = "select DISTINCT(questionId) from question_info";
					ResultSet rrs1 = q.queryDB(sql5);
					boolean flag = false;
					while (rrs1.next()) {
						int id = rrs1.getInt("questionId");
						if (id==num2) {
							flag = true;
						}
					}
					if (flag) {
						String sql6 = "delete from question_info where questionId = ?";
						@SuppressWarnings("unused")
						int rs4 = q.operateDB(sql6,num2);
						System.out.println("ɾ���ɹ�");
						break lab;//
					}else {
						System.out.println("�Բ���û��Ҫɾ���ı�ţ�������ѡ��");
					}
				}
				break;
			case 6:
				System.out.println("ллʹ��");
				System.exit(0);
				break;
			default:
				System.out.println("����������������������");
				break;
			}
			if (choice1 != 6) {
				showMenu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
