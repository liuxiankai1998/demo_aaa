package Text2;

import java.sql.ResultSet;

public interface QuestionInfoDao {
	public abstract int operateDB(String sql,Object...obs);
	//��ѯ���ݿ�
	public abstract ResultSet queryDB(String sql,Object...obs);
}
