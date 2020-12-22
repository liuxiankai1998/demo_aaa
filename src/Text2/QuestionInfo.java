package Text2;

import java.sql.ResultSet;
import java.util.Scanner;

public class QuestionInfo {
	public void showMenu(){
		try {
			//1
			QuestionInfoDaoImpl q = new QuestionInfoDaoImpl();
			System.out.println("*************欢迎使用试题管理系统*****************************************************");
			System.out.print("请选择操作：1.列出所有试题 2.按科目查询 3.按题干模糊查询 4.添加试题 5.删除试题 6.退出系统 ：");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int choice1 = scan.nextInt();
			switch (choice1) {
			case 1:
				//1.列出所有试题
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
					System.out.println(id+"、"+qu);
					System.out.println("\t选项A："+a);
					System.out.println("\t选项B："+b);
					System.out.println("\t选项C："+c);
					System.out.println("\t选项D："+d);
					System.out.println("\t答案："+ans);
				}
				break;
			case 2:
				//2.按科目查询
				System.out.print("请输入想查询的科目(1代表动物、2代表食物)：");
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
					System.out.println(id+"、"+qu);
					System.out.println("\t选项A："+a);
					System.out.println("\t选项B："+b);
					System.out.println("\t选项C："+c);
					System.out.println("\t选项D："+d);
					System.out.println("\t答案："+ans);
				}
				break;
			case 3:
				// 3.按题干模糊查询
				System.out.print("请输入想查询的关键字:");
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
					System.out.println(id+"、"+qu);
					System.out.println("\t选项A："+a);
					System.out.println("\t选项B："+b);
					System.out.println("\t选项C："+c);
					System.out.println("\t选项D："+d);
					System.out.println("\t答案："+ans);
				}
				break;
			case 4:
				//4.添加试题
				String ssql = "SELECT MAX(questionId) FROM question_info;";
				ResultSet rrs = q.queryDB(ssql);
				int max=0;
				if (rrs.next()) {
					max = rrs.getInt("MAX(questionId)");
				}
				System.out.print("请输入科目:(1.动物 2.食物 )");
				int num1 = scan.nextInt();
				System.out.println("请输入新的题：");
				String strTG = scan.next();
				System.out.println("请输入新的选项A：");
				String strA = scan.next();
				System.out.println("请输入新的选项B：");
				String strB = scan.next();
				System.out.println("请输入新的选项C：");
				String strC = scan.next();
				System.out.println("请输入新的选项D：");
				String strD = scan.next();
				System.out.println("请输入新的答案：");
				String strANS = scan.next();
				System.out.println();
				String sql4 = "insert into question_info (questionId,question,optionA,optionB,optionC,optionD,answer,subject) values(?,?,?,?,?,?,?,?)";
				int rs3 = q.operateDB(sql4,(max+1),strTG,strA,strB,strC,strD,strANS,num1);
				if (rs3 == 1) {
					System.out.println("添加成功！");
				}else {
					System.out.println("添加失败！");
				}
				break;
			case 5:
				//5.删除试题
				lab:for (;;) {
					System.out.println("请输入需要删除的题目编号：");
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
						System.out.println("删除成功");
						break lab;//
					}else {
						System.out.println("对不起，没有要删除的编号，请重新选择");
					}
				}
				break;
			case 6:
				System.out.println("谢谢使用");
				System.exit(0);
				break;
			default:
				System.out.println("输入内容有误，请重新输入");
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
